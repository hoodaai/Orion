package main.java.com.infrastructure;

import main.java.com.domain.entities.Document;
import main.java.com.domain.entities.Ticket;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory storage to store all processed document.
 */
public class StaticStore {
   public static Map<Ticket, Document> store = new HashMap<Ticket, Document>();
}
