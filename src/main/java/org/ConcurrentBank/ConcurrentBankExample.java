package org.ConcurrentBank;

import java.math.BigDecimal;

public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(BigDecimal.valueOf(200));
        BankAccount account2 = bank.createAccount(BigDecimal.valueOf(0));

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, BigDecimal.valueOf(200)));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, BigDecimal.valueOf(100)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
        System.out.println("Total balance account1: " + bank.getAccountList().get(account1.getUuid()).getBalance());
        System.out.println("Total balance account2: " + bank.getAccountList().get(account2.getUuid()).getBalance());    }
}