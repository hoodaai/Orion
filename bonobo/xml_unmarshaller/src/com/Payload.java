package com;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents payload xml
 * ex:
 * <code>
 *  <?xml version="1.0" encoding="UTF-8"?>
    <PAYLOAD>
       <TIMESTAMP>timestamp</TIMESTAMP>
       <ACCOUNTNUMBER>accountnumber</ACCOUNTNUMBER>
       <ROLE>role</ROLE>
    </PAYLOAD>
 * </code>
 *
 */
@XmlRootElement(name = "PAYLOAD")
public class Payload {

   private String timestamp;
   private String accountNumber;
   private String role;

   @XmlElement(name = "TIMESTAMP")
   public String getTimestamp() {
      return timestamp;
   }

   public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
   }

   @XmlElement(name = "ACCOUNTNUMBER")
   public String getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
   }

   @XmlElement(name = "ROLE")
   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Payload [")
        .append(" timestamp=").append(timestamp)
        .append(" accountNumber=").append(accountNumber)
        .append(" role=").append(role)
        .append("]");

      return sb.toString();
   }
}
