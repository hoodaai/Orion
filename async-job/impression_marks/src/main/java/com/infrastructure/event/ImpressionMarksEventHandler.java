package main.java.com.infrastructure.event;

import main.java.com.domain.entities.Book;
import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Journal;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import main.java.com.infrastructure.StaticStore;

/**
 *
 */
public class ImpressionMarksEventHandler {

   public void handleEvent(ImpressionMarksEvent event) {
      Document document = event.getDocument();
      Ticket ticket = event.getTicket();

      if (document instanceof Journal) {
         Journal journal = (Journal) document;
         journal.setImpressionMarkProperty(journal.new ImpressionMark(journal.getContent(),
                                                                      journal.getTitle(),
                                                                      journal.getAuthor()));
         ticket.setProcessingStatus(ProcessingStatus.COMPLETE);
         StaticStore.store.put(ticket, journal);

      }
      if (document instanceof Book) {
         Book book = (Book) document;
         book.setImpressionMarkProperty(book.new BookImpressionMark(book.getContent(),
                                                                    book.getTitle(),
                                                                    book.getAuthor(),
                                                                    book.getTopic()));
         ticket.setProcessingStatus(ProcessingStatus.COMPLETE);
         StaticStore.store.put(ticket, book);
      }
   }
}
