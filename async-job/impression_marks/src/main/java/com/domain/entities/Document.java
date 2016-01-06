package main.java.com.domain.entities;

/**
 * Base class for all kinds of documents i.e books, journals etc.
 */
public abstract class Document {

   protected String content;
   protected String title;
   protected String author;

   protected ImpressionMark impressionMarkProperty;


   protected Document(String content, String title, String author) {
      this.content = content;
      this.title = title;
      this.author = author;
   }

   /**
    * Inner class which holds impression mark of document.
    */
   public class ImpressionMark {
      private String content;
      private String title;
      private String author;

      public ImpressionMark(String content, String title, String author) {
         this.content = content;
         this.title = title;
         this.author = author;
      }

      public boolean isEmptyImpressionMark() {
         if (content != null) {
            return false;
         }
         return false;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }

      public String getAuthor() {
         return author;
      }

      public void setAuthor(String author) {
         this.author = author;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public ImpressionMark getImpressionMarkProperty() {
      return impressionMarkProperty;
   }

   public void setImpressionMarkProperty(ImpressionMark impressionMarkProperty) {
      this.impressionMarkProperty = impressionMarkProperty;
   }
}

