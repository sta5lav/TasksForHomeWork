package org.DeadLockExample;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    private final Resource resourceA = new Resource();
    private final Resource resourceB = new Resource();

    public final Lock lock = new ReentrantLock();


    public void execute() {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                acquireResourcesAndWork(resourceA, resourceB, "Thread-1");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                acquireResourcesAndWork(resourceB, resourceA, "Thread-2");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void acquireResourcesAndWork(Resource firstResource, Resource secondResource, String threadName) {
        try {
            lock.lock();
            Lock firstLock = firstResource.getCount() < secondResource.getCount() ? firstResource.lock : secondResource.lock;
            Lock secondLock = !(firstLock == firstResource.lock) ? firstResource.lock : secondResource.lock;
            try {
                firstLock.lock();
                System.out.println(threadName + " locked " + firstResource);
                secondLock.lock();
                System.out.println(threadName + " locked " + secondResource);
                // Имитация работы с ресурсом
                Thread.sleep(100);
                try {
                    // Имитация работы с ресурсом
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    secondLock.unlock();
                    System.out.println(threadName + " unlocked " + secondResource);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                firstLock.unlock();
                System.out.println(threadName + " unlocked " + firstResource);
            }
        } finally {
            lock.unlock();
        }
    }


}
