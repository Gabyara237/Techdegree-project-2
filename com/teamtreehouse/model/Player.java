package com.teamtreehouse.model;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  private int heightInInches;
  private boolean previousExperience;

  // Constructor
  public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.heightInInches = heightInInches;
    this.previousExperience = previousExperience;
  }

// ------- Getters-----------
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getHeightInInches() {
    return heightInInches;
  }

  public boolean isPreviousExperience() {
    return previousExperience;
  }

  // Method that allows the correct organization of the players in alphabetical order.
  @Override
  public int compareTo(Player other) {
    // We always want to sort by last name then first name\
    if(this.lastName.compareTo(other.lastName)==0){
      return this.firstName.compareTo(other.firstName);
    }else{
      return lastName.compareTo(other.lastName);
    }
  }


}
