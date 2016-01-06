package com.synchronization;

/**
 * In this class there are two threads, one is main thread and another is ReaderThread.
 * This program illustrates what can go wrong if two threads share data without synchronization
 * <p/>
 * There is no guarantee that operations in one thread will be performed in the order
 * given by the program, as long as the reordering is not detectable from within that
 * thread even if the reordering is apparent to other threads.
 * This may  seem  like  a  broken  design,  but  it  is  meant  to  allow  JVMs  to
 * take  full advantage of the  performance  of  modern  multiprocessor hardware.
 * For example, in the absence of synchronization, the Java Memory Model permits the
 * compiler to reorder operations and cache values in registers, and permits CPUs to
 * reorder operations and cache values in processor‐specific caches
 */
public class NoVisibility {
   private static boolean ready;
   private static int number;

   private static class ReaderThread extends Thread {
      public void run() {
         //could loop  forever because the value of ready might never become visible to the
         // reader thread.
         while (!ready) {
            Thread.yield();
         }
         System.out.println(number);
      }
   }

   /**
    * Executes in main thread. It writes shared data and starts ReaderThread.
    * There is no guarantee that the values ready and number written by the main thread will be
    * visible to the reader thread.
    * <p/>
    * In the absence of synchronization, the compiler, processor, and runtime can do some downright
    * weird things to the order in which operations appear to execute. Attempts to reason about the
    * order in which memory actions "must" happen in insufficiently synchronized multithreaded
    * programs will almost certainly be incorrect.
    */
   public static void main(String[] args) {
      new ReaderThread().start();
      number = 42;
      ready = true;
   }
}

