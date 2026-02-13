/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.36.0.8091.03bcab5b3 modeling language!*/

package ca.mcgill.ecse321.eventregistration.model;


import jakarta.persistence.Entity;

import java.sql.Date;
import java.sql.Time;

// line 13 "model.ump"
// line 56 "model.ump"
@Entity
public class InPersonEvent extends Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //InPersonEvent Attributes
  private String address;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public InPersonEvent() { super(); }

  public InPersonEvent(String aName, Date aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit, String aAddress)
  {
    super(aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
    address = aAddress;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "]";
  }
}