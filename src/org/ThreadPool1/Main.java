package org.ThreadPool1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main  {
    private static final int MAX_THREADS = 4;
    /*  interleaved : xen kẽ
        Multithreads seem to run parallel, mean the two messages or threads are interleaved but in actually sometime some thread my jump ahead of another and break the order
        This mean even with the thread scheduler like Thread.sleep it still give NO GUARANTEE whether the threads running in same orders.
        If the sleep time is over but the current thread still running it job then the next thread will be started anyway.
     */
    public static void main(String[] args) {
        Runnable r1 = new MyThreadPools("t1 "+"Hello world");
        Runnable r2 = new MyThreadPools("t2 "+"Goodbye");

        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);
        pool.execute(r1);
        pool.execute(r2);

        //Check what happen when run directly call it method then call through threads
        //Result in run top lines first until finish loop then it run line bellow it until it done then repeat.
//        r1.run();
//        r2.run();

    }


}