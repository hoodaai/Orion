package main.java.com.infrastructure.event;

import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Ticket;

/**
 * Impression marks event. Contains document to be processed
 */
public class ImpressionMarksEvent {
   private Document document;
   private Ticket ticket;

   public ImpressionMarksEvent(Document document, Ticket ticket) {
      this.document = document;
      this.ticket = ticket;
   }

   public Document getDocument() {
      return document;
   }

   public void setDocument(Document document) {
      this.document = document;
   }

   public Ticket getTicket() {
      return ticket;
   }

   public void setTicket(Ticket ticket) {
      this.ticket = ticket;
   }
}
