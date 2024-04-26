package org.StreamsHW.ForkJoinPool;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private int n;


    public FactorialTask(int n) {
        this.n = n;
    }


    @Override
    protected Long compute() {
        if (n <= 1) {
            return 1L;
        } else {
            RecursiveTask<Long> recursiveTask = new FactorialTask(n - 1);
            recursiveTask.fork();
            return n * recursiveTask.join();
        }
    }
}

