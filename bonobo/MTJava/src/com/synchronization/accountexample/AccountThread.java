package com.synchronization.accountexample;

/**
 */
public class AccountThread implements Runnable {
   private AccountService service;
   private Account account;
   private Double amount;
   private int delay;

   public AccountThread(Account account, Double amount, int delay) {
      this.account = account;
      this.amount = amount;
      this.delay = delay;
   }

   @Override
   public void run() {
      service.deposit(account, amount, delay);
   }

   public void setService(AccountService service) {
      this.service = service;
   }
}
