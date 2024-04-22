package org.ConcurrentBank;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    public static final Lock LOCK = new ReentrantLock();

    private UUID uuid;

    private BigDecimal balance;


    public BankAccount(UUID uuid, BigDecimal sum) {
        this.uuid = uuid;
        this.balance = sum;
    }


    public synchronized void deposit(BigDecimal dep) {
        setBalance(getBalance().add(dep));
    }

    public synchronized void withdraw(BigDecimal withdraw) {
        if (withdraw.compareTo(balance) == 1){
            throw new RuntimeException("Недостаточно средств!");
        }
        setBalance(getBalance().subtract(withdraw));

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
