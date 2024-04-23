package org.SynchronizedExecutorService;

import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private  CyclicBarrier cyclicBarrier;

    private ComplexTask complexTask;

    public ComplexTaskExecutor(int i) {

    }

    public void executeTasks(int numberOfTasks) {
        int countElementsBarrier = 3;
        int i = 0;
        complexTask = new ComplexTask(numberOfTasks);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (Task s : complexTask.getTasks()) {
            executorService.execute(s);
        }
        cyclicBarrier = new CyclicBarrier(countElementsBarrier);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

}

