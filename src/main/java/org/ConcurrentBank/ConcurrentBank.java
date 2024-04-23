package org.ConcurrentBank;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;

public class ConcurrentBank {

    private Map<UUID, BankAccount> accountList = new HashMap<>();

    public BankAccount createAccount(BigDecimal sum) {
        BankAccount bankAccount = new BankAccount(UUID.randomUUID(), sum);
        accountList.put(bankAccount.getUuid(), bankAccount);
        return bankAccount;
    }

    public void transfer(BankAccount a, BankAccount b, BigDecimal sum) {
        Lock firstLock = (a.getUuid().compareTo(b.getUuid()) < 0) ? a.LOCK : b.LOCK;
        Lock secondLock = (firstLock == a.LOCK) ? b.LOCK : a.LOCK;

        try {
            firstLock.lock();
            secondLock.lock();
            if (a.getBalance().compareTo(sum) < 0) {
                throw new RuntimeException("Недостаточно средств");
            }
            a.withdraw(sum);
            b.deposit(sum);
            System.out.println(Thread.currentThread());
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }
    }

    public synchronized String getTotalBalance() {
        BigDecimal bigDecimal = BigDecimal.valueOf(0);
        for (Map.Entry<UUID, BankAccount> entry : accountList.entrySet()) {
            bigDecimal = bigDecimal.add(entry.getValue().getBalance());
        }
        return bigDecimal.toString();
    }


    public Map<UUID, BankAccount> getAccountList() {
        return this.accountList;
    }
}
