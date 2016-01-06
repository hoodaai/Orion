package main.java.com.domain.entities;

import main.java.com.domain.enums.ProcessingStatus;

/**
 * Represent acknowledgement of a document which is submitted to queue for impression mark
 * processing. It could be used to fetch processing status of document at any moment of time.
 */
public class Ticket {
   private long id;
   private ProcessingStatus processingStatus;

   public Ticket(long id, ProcessingStatus processingStatus) {
      this.id = id;
      this.processingStatus = processingStatus;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public ProcessingStatus getProcessingStatus() {
      return processingStatus;
   }

   public void setProcessingStatus(ProcessingStatus processingStatus) {
      this.processingStatus = processingStatus;
   }

   @Override
   public boolean equals(Object o) {
      if (o instanceof Ticket) {
         final Ticket ticket = (Ticket) o;
         if (id == ticket.id) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      int result = (int) (id ^ (id >>> 32));
      return result;
   }
}

