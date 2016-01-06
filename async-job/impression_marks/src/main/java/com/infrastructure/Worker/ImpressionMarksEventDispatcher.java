package main.java.com.infrastructure.Worker;

import main.java.com.infrastructure.SharedQueue;
import main.java.com.infrastructure.event.ImpressionMarksEvent;
import main.java.com.infrastructure.event.ImpressionMarksEventHandler;

/**
 * Thread which dequeues events from {@link SharedQueue} and calls handler for
 * event
 */
public class ImpressionMarksEventDispatcher implements Runnable {

   @Override
   public void run() {
      boolean isRunning = true;
      while (isRunning) {
         try {
            ImpressionMarksEvent event = SharedQueue.dequeue();
            if (event != null) {
               // Process the message
               processMessage(event);
            } else {
               isRunning = false;
            }
         } catch(Exception e) {
            System.out.println(e.getMessage());
         }
      }
   }

   /*
    * Helper method, which creates event handler and calls handleEvent method
    *
    * @param event
    */
   private void processMessage(ImpressionMarksEvent event) {
      try {
         ImpressionMarksEventHandler handler = new ImpressionMarksEventHandler();
         handler.handleEvent(event);
      } catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }
}