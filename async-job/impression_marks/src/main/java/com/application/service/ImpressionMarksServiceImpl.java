package main.java.com.application.service;

import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import main.java.com.infrastructure.SharedQueue;
import main.java.com.infrastructure.StaticStore;

/**
 * Implementation of {@link ImpressionMarksService} interface.
 */
public class ImpressionMarksServiceImpl implements ImpressionMarksService {

   @Override
   public Ticket createImpressionMark(Document document) {
      Ticket ticket = null;
      try {
         ticket = SharedQueue.enqueue(document);
      } catch(Exception e) {
         e.printStackTrace();
      }
      return ticket;
   }

   @Override
   public Document getProcessedDocument(Ticket ticket) {
      return StaticStore.store.get(ticket);
   }

   @Override
   public ProcessingStatus getDocumentProcessingStatus(Ticket ticket) {
      if (StaticStore.store.get(ticket) != null) {
         return ProcessingStatus.COMPLETE;
      }

      return ProcessingStatus.IN_PROGRESS;
   }
}
