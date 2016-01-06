package main.java.com.domain.entities;

/**
 * Represents Book document type.
 */
public class Book extends Document {

   private String topic;

   public Book(String content, String title, String author, String topic) {
      super(content, title, author);
      this.topic = topic;
   }

   /**
    * Inner class which holds impression mark for book.
    * Here we extend Document's ImpressionMark class and add one more attribute to satisfy
    * book's impression mark property need.
    */
   public class BookImpressionMark extends Document.ImpressionMark {

      private String topic;

      public BookImpressionMark(String content, String title, String author, String topic) {
         super(content, title, author);
         this.topic = topic;
      }

      public String getTopic() {
         return topic;
      }

      public void setTopic(String topic) {
         this.topic = topic;
      }
   }

   public String getTopic() {
      return topic;
   }

   public void setTopic(String topic) {
      this.topic = topic;
   }

   public void setImpressionMarkProperty(BookImpressionMark impressionMarkProperty) {
      this.impressionMarkProperty = impressionMarkProperty;
   }
}
