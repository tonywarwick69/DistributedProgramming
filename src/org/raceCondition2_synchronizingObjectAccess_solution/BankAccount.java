package org.raceCondition2_synchronizingObjectAccess_solution;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private Lock balanceChangeLock;
    private Condition sufficientFunds;

    public BankAccount(){
        balance=0;
        balanceChangeLock = new ReentrantLock();
        sufficientFunds = balanceChangeLock.newCondition();
    }

    /*
    Deposits money into bank account
    @param amount are the amount to deposit
     */
    public void deposit(double amount){
        balanceChangeLock.lock();
        try{
            System.out.println("Depositing "+ amount);
            double newBalance = balance + amount;
            System.out.println(", new balance is "+ newBalance);
            balance = newBalance;
            //Signal method after await operation aka withdraw transaction has finished
            sufficientFunds.signalAll();
        } finally{
            balanceChangeLock.unlock();
        }
    }
    /*
    Withdraws money into bank account
    @param amount are the amount to withdraws
     */
    public void withdraw(double amount) throws InterruptedException{
        balanceChangeLock.lock();
        try{
            while(balance<amount){
                sufficientFunds.await();
            }
            System.out.println("Withdrawing "+ amount);
            double newBalance = balance-amount;
            System.out.println(", new balance is "+newBalance);
            balance=newBalance;

        } finally {
            balanceChangeLock.unlock();
        }
    }
    /*
    Get the current balance of bank account
    @return the current balance
     */
    public double getBalance(){
        return balance;
    }


}
