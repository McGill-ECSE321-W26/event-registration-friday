/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.36.0.8091.03bcab5b3 modeling language!*/

package ca.mcgill.ecse321.eventregistration.model;


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
  public OnlineEvent() { super(); }

  public OnlineEvent(String aName, Date aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit, String aUrl)
  {
    super(aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
    url = aUrl;
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