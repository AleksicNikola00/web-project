package beans.basic;

import beans.enumerations.OrderStatus;
import java.util.*;

public class Order {
   private UUID id;
   private ArrayList<UUID> orderedItems;
   private UUID restaurant;
   private java.util.Date date;
   private double price;
   private String username;
   private OrderStatus status;
   private boolean isDeleted;

}