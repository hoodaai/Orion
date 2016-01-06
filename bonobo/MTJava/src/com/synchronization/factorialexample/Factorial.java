package com.synchronization.factorialexample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Creates Factorial
 */
public class Factorial implements Runnable {
   private static long number;
   private static int hitCounter;
   private static AtomicInteger atomicHitCounter = new AtomicInteger();
   private static Object lock = new Object();

   public static long calculate() {
      atomicHitCounter.incrementAndGet();
      hitCounter = hitCounter + 1;
      long result = 1;
      while (number != 0) {
         if (number == 0 || number < 0) {
            break;
         }
         try {
            Thread.sleep(100);
         } catch(InterruptedException e) {
            e.printStackTrace();
         }
         result = result * number;
         //System.out.println("number="+number+" result="+result);
         number--;
      }

      return result;
   }

   @Override
   public void run() {

      synchronized(lock) {
         System.out.println(this.toString());
         System.out.println(lock.toString());
         try {
            Thread.sleep(1000);
         } catch(InterruptedException e) {
            e.printStackTrace();
         }
         int randomNumber = (int) ((Math.random() * 6) + 1);
         number = randomNumber;
         long result = calculate();
         StringBuilder sb = new StringBuilder("Factorial [").append(" number=")
                                                            .append(number)
                                                            .append(" randomNumber=")
                                                            .append(randomNumber)
                                                            .append(" result=")
                                                            .append(result)
                                                            .append(" currentThread=")
                                                            .append(Thread.currentThread())
                                                            .append(" activeCount=")
                                                            .append(Thread.activeCount())
                                                            .append(" hitCounter=")
                                                            .append(hitCounter)
                                                            .append(" atomicHitCounter=")
                                                            .append(atomicHitCounter)
                                                            .append(" ]");
         System.out.println(sb);
      }
   }
}
