package beans.model;

import java.util.UUID;

import beans.enumerations.Mark;

public class Comment {
   private UUID id;
   private String username;
   private UUID restaurantId;
   private String text;
   private Mark mark;
   private boolean isDeleted;

}