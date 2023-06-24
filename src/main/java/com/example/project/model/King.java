package com.example.project.model;

import jakarta.persistence.*;

@Entity
@Table(name="kings")
public class King {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "king_generator")
   private long id;


   @Column(name = "title")
   private String title;

   @Column(name = "name")
   private String name;

   @Column(name = "isHeathen")
   private boolean isHeathen;

   //Constructor
   public King(){}

   public King(String title, String name, boolean isHeathen){
      this.title = title;
      this.name = name;
      this.isHeathen = isHeathen;
   }

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   //Getters and setters
   public String getTitle(){return title;}
   public void setTitle(String title){
      this.title = title;
   }

   public String getName(){return name;}
   public void setName(String name){
      this.name = name;
   }

   public boolean getIsHeathen(){return isHeathen;}
   public void setIsHeathen(boolean isHeathen){
      this.isHeathen = isHeathen;
   }

}
