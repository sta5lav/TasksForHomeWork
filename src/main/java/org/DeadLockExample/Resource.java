package org.DeadLockExample;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {

    public final Lock lock = new ReentrantLock();

    private int count = (int) (Math.random() * 5);

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource resource)) return false;
        return count == resource.count && Objects.equals(lock, resource.lock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lock, count);
    }
}
