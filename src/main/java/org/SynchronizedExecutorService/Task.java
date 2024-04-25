package org.SynchronizedExecutorService;


public class Task{

    public synchronized void execute() {
        System.out.printf("%s is completed\n", this);
    }
}
