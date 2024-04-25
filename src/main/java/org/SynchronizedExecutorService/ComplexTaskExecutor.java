package org.SynchronizedExecutorService;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ComplexTaskExecutor {

    private final int countComplexTask;

    public ComplexTaskExecutor(int i) {
        this.countComplexTask = i;
    }

    public void executeTasks(int numberOfTasks) {
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(5);
        ArrayList<ComplexTask> listComplex = addComplexTasks(cyclicBarrier);
        for (ComplexTask s : listComplex) {
            s.createTask(numberOfTasks);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);
        for (ComplexTask s : listComplex) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    s.run();
                }
            });

        }
        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executorService.shutdown();

    }


    private ArrayList<ComplexTask> addComplexTasks(CyclicBarrier cyclicBarrier) {
        ArrayList<ComplexTask> complexTasks = new ArrayList<>();
        for (int j = 0; j < this.countComplexTask; j++) {
            complexTasks.add(new ComplexTask(cyclicBarrier));
        }
        return complexTasks;
    }

}

