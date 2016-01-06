package com.synchronization.accountexample;

/**
 */
public class AccountService {
   public void deposit(Account aAccount, Double aSum, int delay) {
      /*synchronized(aAccount) {
         System.out.println(aAccount.toString()+ Thread.currentThread());
         Double oldAmmount = aAccount.getBaseAmount();
         randomWait(delay);
         Double newAmmount = oldAmmount + aSum;
         aAccount.setBaseAmount(newAmmount);
         System.out.println("Amount deposited: "+aAccount.getBaseAmount());
      }*/
      System.out.println(aAccount.toString()+ Thread.currentThread());
      Double oldAmmount = aAccount.getBaseAmount();
      try {
         Thread.sleep(delay);
      } catch(InterruptedException e) {
         e.printStackTrace();
      }
      Double newAmmount = oldAmmount + aSum;
      aAccount.setBaseAmount(newAmmount);
      System.out.println("Amount deposited: "+aAccount.getBaseAmount());


   }

   private void randomWait(int delay) {
      try {
         Thread.sleep(delay);
      } catch(InterruptedException e) {
         e.printStackTrace();
      }
   }
}
