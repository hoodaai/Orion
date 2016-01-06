package main.java.com.infrastructure.Worker;

import java.util.concurrent.TimeUnit;

public class QueueConsumer {

   private static ThreadPool pool;
   private static final int maximumThreadsAllowed = 10;
   private static final int minimumThreadsAllowed = 5;
   private static final long idleSeconds = 10;

   /**
    * Initiate thread pool, creates and add worker threads to the pool.
    */
   public void initiateThreadPool() {
      pool = new ThreadPool(minimumThreadsAllowed, maximumThreadsAllowed, idleSeconds);
      // starting point for worker threads.
      ImpressionMarksEventDispatcher eventDispatcher = new ImpressionMarksEventDispatcher();
      for (int count = 0; count < maximumThreadsAllowed; count++) {
         pool.addWorkItem(eventDispatcher);
      }
      // shutting down pool
      pool.shutdown();
      try {
         // To wait "forever"
         pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
      } catch(InterruptedException e) {
         System.out.println(e.getMessage());
      }
   }
}
