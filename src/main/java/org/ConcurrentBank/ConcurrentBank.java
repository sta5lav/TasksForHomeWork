package org.ConcurrentBank;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConcurrentBank {

    private Map<UUID, BankAccount> accountList = new HashMap<>();

    public  BankAccount createAccount(BigDecimal sum) {
        BankAccount bankAccount = new BankAccount(UUID.randomUUID(), sum);
        accountList.put(bankAccount.getUuid(), bankAccount);
        return bankAccount;
    }

    public void transfer(BankAccount a, BankAccount b, BigDecimal sum) {
        synchronized (a) {
            synchronized (b) {
                if (a.getBalance().compareTo(sum) == -1) {
                    throw new RuntimeException("Недостаточно средств");
                }
                a.withdraw(sum);
                b.deposit(sum);
                System.out.println(Thread.currentThread());
            }
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
