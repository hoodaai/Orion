package com.synchronization.accountexample;

/**
 */
public class AccountTest {

   private static AccountService service;

   public static void main(String a[]) {
      Account acc = new Account();

      AccountThread at1 = new AccountThread(acc, 100.00, 3000);
      at1.setService(getInstance());
      Thread t1 = new Thread(at1, "thread1: ");
      t1.start();

      AccountThread at2 = new AccountThread(acc, 200.00, 0);
      at2.setService(getInstance());
      Thread t2 = new Thread(at2, "thread2: ");
      t2.start();

      try {
         Thread.sleep(4000);
      } catch(InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("Amount: "+acc.getBaseAmount());

   }

   private static AccountService getInstance() {
      if (service == null) {
         System.out.println("Creating new service...");
         service = new AccountService();
      }
      return service;
   }
}
