package beans.basic;

import beans.enumerations.RestaurantType;
import beans.enumerations.RestaurantStatus;
import java.util.*;

public class Restaurant {
   private String name;
   private UUID id;
   private RestaurantType type;
   private RestaurantStatus status;
   private String logoPath;
   private UUID geoLocationId;
   private boolean isDeleted;

}