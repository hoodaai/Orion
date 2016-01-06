package test.java;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.com.application.service.ImpressionMarksService;
import main.java.com.application.service.ImpressionMarksServiceImpl;
import main.java.com.domain.entities.Book;
import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Journal;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import main.java.com.infrastructure.SharedQueue;
import main.java.com.infrastructure.StaticStore;
import main.java.com.infrastructure.Worker.QueueConsumer;

/**
 * Junits for {@link QueueConsumer}
 * <p/>
 * test methods naming convention :- <li>
 * test<method-name>For<argument-description>()
 */
public class TestQueueConsumer extends TestCase {

   private static ImpressionMarksService impressionMarkService;
   private static QueueConsumer consumer;

   final private static List<Ticket> tickets = new ArrayList<Ticket>();

   /**
    * Case Ininitiate {@link SharedQueue} consumer to process impression mark on submitted {@link Document}
    * - Inputs: nothing
    * - Exp. Output: nothing
    * - Assertions: Book should be dequeued from {@link SharedQueue}. Impression mark should
    * be created on {@link Document}. {@link ProcessingStatus} should be COMPLETE
    */
   public void testProcessingComplete() {
      submitDocuments();
      consumer.initiateThreadPool();

      /** If book is processed successfully then it should be available in static store*/
      final Book book = (Book) StaticStore.store.get(tickets.get(0));
      assertNotNull(book.getImpressionMarkProperty());

      /** If book is processed successfully then ProcessingStatus should be COMPLETE */
      final ProcessingStatus processingStatusForBook = impressionMarkService.getDocumentProcessingStatus(tickets.get(0));
      assertEquals(ProcessingStatus.COMPLETE, processingStatusForBook);

      /** Impression mark property should be equal to journal's property, if impression mark is created successful*/
      final Book processedbooK = (Book) impressionMarkService.getProcessedDocument(tickets.get(0));
      assertEquals(book.getContent(), processedbooK.getContent());
      assertEquals(book.getTitle(), processedbooK.getTitle());
      assertEquals(book.getAuthor(), processedbooK.getAuthor());
      assertEquals(book.getTopic(), processedbooK.getTopic());

      /** If journal is processed successfully then it should be available in static store*/
      final Journal journal = (Journal) StaticStore.store.get(tickets.get(1));
      assertNotNull(journal.getImpressionMarkProperty());

      /** If journal is processed successfully then ProcessingStatus should be COMPLETE */
      final ProcessingStatus processingStatusForJournal = impressionMarkService.getDocumentProcessingStatus(tickets.get(
         1));
      assertEquals(ProcessingStatus.COMPLETE, processingStatusForJournal);

      /** Impression mark property should be equal to journal's property, if impression mark is created successful*/
      Document processedJournal = impressionMarkService.getProcessedDocument(tickets.get(1));
      assertEquals(journal.getContent(), processedJournal.getContent());
      assertEquals(journal.getTitle(), processedJournal.getTitle());
      assertEquals(journal.getAuthor(), processedJournal.getAuthor());
   }

   /**
    * Case Ininitiate {@link SharedQueue} consumer to process impression mark on submitted {@link Document}
    * - Inputs: nothing
    * - Exp. Output: nothing
    * - Assertions: Book should be dequeued from {@link SharedQueue}. Impression mark should
    * be created on {@link Document}. {@link ProcessingStatus} should not be IN_PROGRESS
    */
   public void testProcessingCompleteNegativeTest() {
      submitDocuments();
      consumer.initiateThreadPool();

      /** If book is processed successfully then it should be available in static store*/
      final Document book = StaticStore.store.get(tickets.get(0));
      assertNotNull(book.getImpressionMarkProperty());

      /** If book is processed successfully then ProcessingStatus should not be IN_PROGRESS */
      final ProcessingStatus processingStatusForBook = impressionMarkService.getDocumentProcessingStatus(tickets.get(0));
      assertNotSame(ProcessingStatus.IN_PROGRESS, processingStatusForBook);

      /** If journal is processed successfully then it should be available in static store*/
      final Document journal = StaticStore.store.get(tickets.get(1));
      assertNotNull(journal.getImpressionMarkProperty());

      /** If journal is processed successfully then ProcessingStatus should not be IN_PROGRESS */
      final ProcessingStatus processingStatusForJournal = impressionMarkService.getDocumentProcessingStatus(tickets.get(
         1));
      assertNotSame(ProcessingStatus.IN_PROGRESS, processingStatusForJournal);
   }

   /**
    * Helper method to submit documents in queue for processing
    */
   private void submitDocuments() {
      /** creating book*/
      Document document = new Book("It is 1939. In Nazi Germany, the country is holding its breath. " +
                                   "Death has never been busier - and will become busier still.",
                                   "The Book Thief",
                                   "Markus Zusak",
                                   "Fiction");
      tickets.add(impressionMarkService.createImpressionMark(document));

      /** creating journal*/
      document = new Journal(
         "Finding an appropriate and simple source language, to be used in implementing student compiler project.",
         "Creating an Appropriate Programming Language for Student Compiler Project",
         "Elinda Kajo Mece ");
      tickets.add(impressionMarkService.createImpressionMark(document));

   }

   @Override
   public void setUp() {
      impressionMarkService = new ImpressionMarksServiceImpl();
      consumer = new QueueConsumer();
   }
}
