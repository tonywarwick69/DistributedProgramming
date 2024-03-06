package org.raceCondition2_synchronizingObjectAccess_solution;

public class BankAccountThreadRunner {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        final double amount = 100;
        final int REPETITIONS = 100;
        final int THREADS = 100;
        for (int i=1 ;i<=THREADS;i++){
            DepositRunnable d = new DepositRunnable(account,amount,REPETITIONS);
            WithdrawRunnable w = new WithdrawRunnable(account,amount,REPETITIONS);

            Thread dt = new Thread(d);
            Thread wt = new Thread(w);

            dt.start();
            wt.start();
        }
    }
}
