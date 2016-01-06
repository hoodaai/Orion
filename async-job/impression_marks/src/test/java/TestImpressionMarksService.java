package test.java;

import main.java.com.application.service.ImpressionMarksService;
import main.java.com.application.service.ImpressionMarksServiceImpl;
import main.java.com.domain.entities.Book;
import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Journal;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import junit.framework.TestCase;

/**
 * Junits for {@link ImpressionMarksService}
 * <p/>
 * test methods naming convention :- <li>
 * test<method-name>For<argument-description>()
 */
public class TestImpressionMarksService extends TestCase {

   private static ImpressionMarksService impressionMarkService;

   @Override
   public void setUp() {
      impressionMarkService = new ImpressionMarksServiceImpl();
   }

   /**
    * Case Submit book to create impression mark:
    * - Inputs: Book
    * - Exp. Output: Ticket
    * - Assertions: Book should be submitted successfully and a valid ticket should be returned
    */
   public void testCreateImpressionMarksServiceForBook() {
      Document document = new Book("It is 1939. In Nazi Germany, the country is holding its breath. " +
                                   "Death has never been busier - and will become busier still.",
                                   "The Book Thief",
                                   "Markus Zusak",
                                   "Fiction");
      final Ticket ticket = impressionMarkService.createImpressionMark(document);
      final ProcessingStatus processingStatus = impressionMarkService.getDocumentProcessingStatus(ticket);

      /** Returned ticket should not be null */
      assertNotNull("Ticket is null", ticket);

      /** Processing status of returned ticket should be IN_PROGRESS */
      assertEquals(ProcessingStatus.IN_PROGRESS, processingStatus);
   }

   /**
    * Case Submit book to create impression mark:
    * - Inputs: Book
    * - Exp. Output: Ticket
    * - Assertions: Book should be submitted successfully and a valid ticket should be returned
    */
   public void testCreateImpressionMarksServiceForJournal() {
      Document document = new Journal(
         "Finding an appropriate and simple source language, to be used in implementing student compiler project.",
         "Creating an Appropriate Programming Language for Student Compiler Project",
         "Elinda Kajo Mece ");

      final Ticket ticket = impressionMarkService.createImpressionMark(document);
      final ProcessingStatus processingStatus = impressionMarkService.getDocumentProcessingStatus(ticket);

      /** Returned ticket should not be null */
      assertNotNull("Ticket is null", ticket);

      /** Processing status of returned ticket should be IN_PROGRESS */
      assertEquals(ProcessingStatus.IN_PROGRESS, processingStatus);
   }
}
