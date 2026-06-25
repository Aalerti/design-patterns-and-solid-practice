package com.solid.liskov_substitution_principle.good;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<WithdrawableAccount> accounts = List.of(
                new DebitAccount("Alice", 1200),
                new CreditAccount("Bob", 500)
        );
        Account deposit = new DepositAccount("Charlie", 3000);

        ATM atm = new ATM();
        atm.withdrawFromAll(accounts, 200);

        System.out.println(deposit.getOwner() + " has deposit balance: " + deposit.getBalance());
    }
}

class ATM {
    void withdrawFromAll(List<WithdrawableAccount> withdrawables, double amount) {
        for (WithdrawableAccount withdrawable : withdrawables) {
            System.out.println("Trying to withdraw from " + withdrawable.getOwner() + " account");
            withdrawable.withdraw(amount);
            System.out.println("New balance: " + withdrawable.getBalance());
        }
    }
}


interface WithdrawableAccount {
    void withdraw(double amount);

    String getOwner();

    double getBalance();
}

abstract class Account {
    private final String owner;
    private double balance;

    Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void decreaseBalance(double amount) {
        balance -= amount;
    }

}

class DebitAccount extends Account implements WithdrawableAccount {
    DebitAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            throw new IllegalArgumentException("Not enough money");
        }

        decreaseBalance(amount);
    }

}

class CreditAccount extends Account implements WithdrawableAccount {
    CreditAccount(String owner, double balance) {
        super(owner, balance);
    }

    @Override
    public void withdraw(double amount) {
        decreaseBalance(amount);
    }
}

class DepositAccount extends Account {
    DepositAccount(String owner, double balance) {
        super(owner, balance);
    }

}
