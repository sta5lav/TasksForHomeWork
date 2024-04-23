package org.SynchronizedExecutorService;


public class Task implements Runnable{

    private int randomNumber = (int) (Math.random() * 10);

    public void execute() {
        for (int i = 0; i < (Math.random() * 10_000); i++) {
            if (i % 2 == 0) {
                randomNumber = randomNumber + 2;
            } else randomNumber++;
        }
        System.out.println("Результирующее значение равно: " + randomNumber);
    }

    @Override
    public void run() {
        execute();
    }
}
