package org.objectLocks_andSychronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private double balance;
    private Lock balanceChangeLock;
    

    public BankAccount(){
        balance=0;
        balanceChangeLock = new ReentrantLock();

    }

    /*
    Deposits money into bank account
    @param amount are the amount to deposit
     */
    public synchronized void deposit(double amount){


            System.out.println("Depositing "+ amount);
            double newBalance = balance + amount;
            System.out.println(", new balance is "+ newBalance);
            balance = newBalance;

            notifyAll();

    }
    /*
    Withdraws money into bank account
    @param amount are the amount to withdraws
     */
    public synchronized void withdraw(double amount) throws InterruptedException{

            while(balance<amount){

                wait();
            }
            System.out.println("Withdrawing "+ amount);
            double newBalance = balance-amount;
            System.out.println(", new balance is "+newBalance);
            balance=newBalance;
    }
    /*
    Get the current balance of bank account
    @return the current balance
     */
    public double getBalance(){
        return balance;
    }


}
