package com.example.administrator.googleplay;

import android.support.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/03
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class ThreadPoolUtils {
    private static final int COUNT = Runtime.getRuntime().availableProcessors();
    private static final int corePoolSize = COUNT;
    private static final int  maximumPoolSize = 2 * COUNT;
    private static final int keepAliveTime = 5;

    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable,"Thread :" +integer.getAndIncrement());
        }
    };

    public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(COUNT),threadFactory);

}
