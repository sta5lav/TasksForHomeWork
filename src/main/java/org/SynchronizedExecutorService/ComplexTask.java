package org.SynchronizedExecutorService;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable{

    private ArrayList<Task> tasks = new ArrayList<>();

    private CyclicBarrier cyclicBarrier;

    ComplexTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void createTask(int count) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tasks.add(new Task());
        }
        this.tasks = tasks;
    }

    @Override
    public void run() {
        for (Task s : tasks) {
            s.execute();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
