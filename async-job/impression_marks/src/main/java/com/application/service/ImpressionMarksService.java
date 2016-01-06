package main.java.com.application.service;

import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Ticket;
import main.java.com.domain.enums.ProcessingStatus;
import main.java.com.infrastructure.StaticStore;

/**
 * Asynchronous Impression marks service
 * It creates impression mark on given document.
 */
public interface ImpressionMarksService {

   /**
    * Asynchronous method to create impression mark on document {@link Document}. It immediately returns a ticket
    * for submitted document. Returned ticket will be used for further enquiry about {@link ProcessingStatus}
    * of document.
    *
    * @param {@link Document} document on which we want to create impression mark.
    * @return {@link Ticket} ticket for document if document is successfully submitted to queue.
    */
   public Ticket createImpressionMark(Document document);

   /**
    * This method retrieves processed document form {@link StaticStore}
    *
    * @param {@link Ticket} ticket returned by asynchronous {@link ImpressionMarksService.createImpressionMark} service
    * @return {@link Document} processed document
    */
   public Document getProcessedDocument(Ticket ticket);

   /**
    * This method gives current processing status of a document
    *
    * @param {@link Ticket} ticket
    * @return {@link ProcessingStatus}
    */
   public ProcessingStatus getDocumentProcessingStatus(Ticket ticket);
}
