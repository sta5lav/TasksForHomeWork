package org.ConcurrentBank;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    public final Lock LOCK = new ReentrantLock();

    private UUID uuid;

    private BigDecimal balance;


    public BankAccount(UUID uuid, BigDecimal sum) {
        this.uuid = uuid;
        this.balance = sum;
    }


    public void deposit(BigDecimal dep) {
        try {
            LOCK.lock();
            setBalance(getBalance().add(dep));
        } finally {
            LOCK.unlock();
        }

    }

    public void withdraw(BigDecimal withdraw) {
        try {
            LOCK.lock();
            if (withdraw.compareTo(balance) == 1) {
                throw new RuntimeException("Недостаточно средств!");
            }
            setBalance(getBalance().subtract(withdraw));
        } finally {
            LOCK.unlock();
        }

    }

    public synchronized BigDecimal getBalance() {
        return this.balance;

    }

    public synchronized void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UUID getUuid() {
        return uuid;
    }
}
