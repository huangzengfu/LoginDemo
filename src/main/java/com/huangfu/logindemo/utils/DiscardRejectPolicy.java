package com.huangfu.logindemo.utils;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HuangFu
 * @create 2022/11/13 15:58
 **/
public class DiscardRejectPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("discard one task!");
    }

}
