package main.java.com.infrastructure;

import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import main.java.com.infrastructure.event.ImpressionMarksEvent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In memory queue used by ImpressionMarkService to enqueue document.
 * Worker of this queue dequeues document from it for processing.
 */
public class SharedQueue {
   private static AtomicInteger ticketId = new AtomicInteger();
   private static ConcurrentLinkedQueue<ImpressionMarksEvent> queue = new ConcurrentLinkedQueue<ImpressionMarksEvent>();

   public static Ticket enqueue(Document document) {
      final Ticket ticket = new Ticket(ticketId.incrementAndGet(), ProcessingStatus.IN_PROGRESS);
      return queue.add(new ImpressionMarksEvent(document, ticket)) ? ticket : null;
   }

   public static ImpressionMarksEvent dequeue() {
      return queue.poll();
   }
}
