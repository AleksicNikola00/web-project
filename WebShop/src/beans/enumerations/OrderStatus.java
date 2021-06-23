package beans.enumerations;

import java.util.*;

public enum OrderStatus {
   PENDING,
   IN_PREPARATION,
   WAITING_DELIVERY,
   IN_TRANSPORT,
   DELIEVERED,
   CANCELED;

   public static String convertToString(OrderStatus orderStatus) {
      // TODO: implement
      return null;
   }
   
   public static OrderStatus getOrderStatus(String orderStatus) {
      // TODO: implement
      return null;
   }

}