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
        LOCK.lock();
        setBalance(getBalance().add(dep));
        LOCK.unlock();
    }

    public void withdraw(BigDecimal withdraw) {
        LOCK.lock();
        if (withdraw.compareTo(balance) == 1){
            throw new RuntimeException("Недостаточно средств!");
        }
        setBalance(getBalance().subtract(withdraw));
        LOCK.unlock();
    }

    public BigDecimal getBalance() {
        return this.balance;

    }

    public void setBalance(BigDecimal balance) {
        LOCK.lock();
        this.balance = balance;
        LOCK.unlock();
    }

    public UUID getUuid() {
        return uuid;
    }
}
