package beans.model;

import beans.enumerations.ItemType;
import beans.enumerations.ItemUnit;
import java.util.*;

public class Item {
   private UUID id;
   private String name;
   private double price;
   private ItemType type;
   private ItemUnit unitType;
   private double unitAmount;
   private String description;
   private String picturePath;
   private UUID restaurantId;
   private boolean isDeleted;

}