package com.huangfu.logindemo.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HuangFu
 * @create 2022/11/12 14:49
 * 手动实现自定义的线程池
 **/
public class MyThreadPoolExecutor extends ThreadPoolExecutor implements Executor {

    /**
     * 线程池名称
     */
//    private String threadPoolName;

    /**
     * 核心线程数
     */
    private Integer coreSize;

    /**
     * 最大线程数
     */
    private Integer maxSize;

    /**
     * 阻塞队列
     */
    private BlockingQueue<Runnable> taskQueue;

    /**
     * 拒绝策略
     */
    private DiscardRejectPolicy rejectPolicy;

    /**
     * 正在执行的线程数量
     */
    private AtomicInteger runningCount = new AtomicInteger(0);

    /**
     * 线程序列号，用于线程的命名
     */
    private AtomicInteger sequence = new AtomicInteger(0);

    public MyThreadPoolExecutor(Integer coreSize, Integer maxSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> taskQueue, RejectedExecutionHandler rejectPolicy) {
//        this.threadPoolName = threadPoolName;
        super(coreSize, maxSize, keepAliveTime, unit, taskQueue, rejectPolicy);
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = (DiscardRejectPolicy) rejectPolicy;
    }


    /**
     * 重写线程执行器Executor中的execute方法
     * 任务执行流程
     * 首先：如果运行的线程数小于核心线程数，直接创建一个新的线程执行任务
     * 其次：如果运行的线程数大于核心线程数，将当前任务添加的任务队列中
     * 然后：如果任务队列也满了的话，并且当前运行的线程数小于最大线程数，直接创建一个新的线程执行当前任务
     * 最后：如果运行的线程数已经等于最大线程数的话，就执行丢弃策略来处理新来的任务
     */
    @Override
    public void execute(Runnable task) {
        //获取正在执行的线程数量
        int count = runningCount.get();

        if (count < coreSize) {
            //执行创建新的线程
            addWorker(task, true);
            return;
        }

        //如果创建新的线程失败，就添加队列
        //offer方法添加成功返回true,否则false
        if (taskQueue.offer(task)) {

        } else {
            //添加队列失败，就创建一个非核心线程
            //如果创建非核心线程失败，就执行丢弃策略
            if (!addWorker(task, false)) {
                this.rejectPolicy.rejectedExecution(task, this);
            }
        }

    }

    /**
     * 创建新线程流程
     * 首先，根据core标识判断是创建核心线程还是非核心线程，这里需要定义一个runningCount变量记录正在执行的线程数量
     * 其次，runningCount变量要在并发环境下做加/减操作，要用到CAS方法，这里直接定义为AtomicInteger类型
     * 然后，创建的过程中先判断线程数量runningCount<coreSize(或者maxSize)(条件一)，然后对runningCount做CAS加一操作(条件二)，如果条件一
     * 执行失败，直接返回false；如果条件二失败，说明runningCount正在被其他线程修改，则进行等待并循环执行。
     * 最后，创建一个新的线程，并不断从任务队列中取任务。
     */
    public boolean addWorker(Runnable newTask, boolean core) {
        for (; ; ) {
            //获取正在执行的线程数
            int count = runningCount.get();

            //判断当前创建的是核心线程数还是非核心线程数
            int max = core ? coreSize : maxSize;

            if (count >= max) {
                return false;
            }

            if (runningCount.compareAndSet(count, count + 1)) {
                //定义线程名字
                String threadName = (core ? "core_" : "") + "name" + sequence.getAndIncrement();

                //创建线程并启动
                new Thread(() -> {

                    System.out.println("thread name: " + Thread.currentThread().getName());

                    Runnable task = newTask;

                    //执行任务，并不断从任务队列中获取任务，全部执行完之后将task置为空
                    while (task != null || (task = getTask()) != null) {
                        try {
                            task.run();
                        } finally {
                            task = null;
                        }
                    }

                }, threadName).start();

                break;
            }
        }

        return true;

    }

    /**
     * 从队列中获取任务,take()方法会一直阻塞直到获取任务，
     */
    public Runnable getTask() {
        try {
            return taskQueue.take();
        } catch (InterruptedException e) {
            //当前线程结束了，需要将runningCount减一
            runningCount.decrementAndGet();
            return null;
        }
    }

    public static void main(String[] args) {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(5, 10, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(15), new DiscardRejectPolicy());

        AtomicInteger num = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            try {
                myThreadPoolExecutor.execute(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("正在执行：" + System.currentTimeMillis() + ": " + num.incrementAndGet());
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                myThreadPoolExecutor.shutdown();
            }
        }

    }
}
