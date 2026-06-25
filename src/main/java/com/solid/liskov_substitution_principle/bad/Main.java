package com.solid.liskov_substitution_principle.bad;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> accounts = List.of(
                new DebitAccount("Alice", 1200),
                new CreditAccount("Bob", 500),
                new DepositAccount("Charlie", 3000)
        );

        ATM atm = new ATM();
        atm.withdrawFromAll(accounts, 200);
    }
}

class ATM {
    void withdrawFromAll(List<Account> accounts, double amount) {
        for (Account account : accounts) {
            System.out.println("Trying to withdraw from " + account.getOwner() + " account");
            account.withdraw(amount);
            System.out.println("New balance: " + account.getBalance());
        }
    }
}

abstract class Account {
    private final String owner;
    private double balance;

    Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    String getOwner() {
        return owner;
    }

    double getBalance() {
        return balance;
    }

    void decreaseBalance(double amount) {
        balance -= amount;
    }

    abstract void withdraw(double amount);
}

class DebitAccount extends Account {
    DebitAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    void withdraw(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Not enough money");
        }

        decreaseBalance(amount);
    }
}

class CreditAccount extends Account {
    CreditAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    void withdraw(double amount) {
        decreaseBalance(amount);
    }
}

class DepositAccount extends Account {
    DepositAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    void withdraw(double amount) {
        throw new UnsupportedOperationException("Deposit account does not support withdrawals before closing");
    }
}
