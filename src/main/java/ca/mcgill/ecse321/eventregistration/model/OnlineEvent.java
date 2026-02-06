package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.8043.819096d90 modeling language!*/


import jakarta.persistence.Entity;

import java.sql.Date;
import java.sql.Time;

// line 21 "model.ump"
// line 74 "model.ump"
@Entity
public class OnlineEvent extends Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OnlineEvent Attributes
  private String url;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OnlineEvent(int aId, String aName, Date aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit, String aUrl)
  {
    super(aId, aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
    url = aUrl;
  }

  public OnlineEvent() {

  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
  }

  public String getUrl()
  {
    return url;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "url" + ":" + getUrl()+ "]";
  }
}