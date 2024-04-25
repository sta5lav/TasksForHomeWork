package org.SynchronizedExecutorService;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ComplexTaskExecutor {



    private ArrayList<ComplexTask> listComplex;

    public ComplexTaskExecutor(int i) {
        listComplex = addComplexTasks(i);
    }


    public void executeTasks(int numberOfTasks) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("Thread working finished"));
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
        /*for (ComplexTask s : listComplex) {
            for (int i = 0; i < numberOfTasks; i++) {
                int finalI = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        s.execute(finalI);
                    }
                });
            }

        }*/

        executorService.shutdown();



    }


    private ArrayList<ComplexTask> addComplexTasks(int i) {
        ArrayList<ComplexTask> complexTasks = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            complexTasks.add(new ComplexTask());
        }
        return complexTasks;
    }

}

