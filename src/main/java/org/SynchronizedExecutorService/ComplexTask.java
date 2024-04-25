package org.SynchronizedExecutorService;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable{

    private ArrayList<Task> tasks = new ArrayList<>();

    private CyclicBarrier cyclicBarrier;

    ComplexTask() {}

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void createTask(int count) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tasks.add(new Task());
        }
        this.cyclicBarrier = new CyclicBarrier(1, () -> System.out.println("Part is finished"));;
        this.tasks = tasks;
    }

    public void execute(int i) {
        tasks.get(i).execute();
        /*try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void run() {
        for (Task s : tasks) {
            s.execute();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }
}
