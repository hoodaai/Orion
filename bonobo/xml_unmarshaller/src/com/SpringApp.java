package com;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.oxm.Unmarshaller;

import java.io.StringReader;
import javax.xml.transform.stream.StreamSource;

/**
 * unmarshall xml
 *
 */
public class SpringApp {

   private Unmarshaller unmarshaller;

   public void processXML(String xml) throws Exception {
      Payload payload =
         (Payload) unmarshaller.unmarshal(new StreamSource(new StringReader(xml.toString())));
      System.out.println(payload);
   }

   public static void main(String args[]) throws Exception {
      ApplicationContext ctx = new FileSystemXmlApplicationContext("application.xml");
      SpringApp application = (SpringApp) ctx.getBean("springApp");
      String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                   + "<PAYLOAD>"
                   + "<TIMESTAMP>1234567</TIMESTAMP>"
                   + "<ACCOUNTNUMBER>2222</ACCOUNTNUMBER>" 
                   + "<ROLE>VIEW_USER</ROLE>" +
                   "</PAYLOAD>";

      application.processXML(xml);
   }

   public Unmarshaller getUnmarshaller() {
      return unmarshaller;
   }

   @Required
   public void setUnmarshaller(Unmarshaller unmarshaller) {
      this.unmarshaller = unmarshaller;
   }
}
