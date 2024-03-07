package org.objectLocks_andSychronized;

public class DepositRunnable implements Runnable {
    private static final int DELAY =1;
    private BankAccount account;
    private double amount;
    private int count;

    public DepositRunnable(BankAccount acc, double anAmount, int aCount){
        account = acc;
        amount = anAmount;
        count=aCount;

    }
    public void run() {

        try {
            for(int i = 1 ; i <= count ; i++){
                account.deposit(amount);
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
