package main.java.com.infrastructure.Worker;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread Pool which will contain threads
 */
public class ThreadPool extends ThreadPoolExecutor {

   private int maxThreads;

   public ThreadPool(int minThreads, int maxThreads, long idleSeconds) {
      super(minThreads, Integer.MAX_VALUE, idleSeconds, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

      this.maxThreads = maxThreads;
   }

   public void addWorkItem(Runnable item) {
      while (getActiveCount() >= maxThreads) {
         try {
            Thread.sleep(25);
         } catch(InterruptedException e) {
            System.out.println(e.getMessage());
         }
      }
      execute(item);
   }
}
