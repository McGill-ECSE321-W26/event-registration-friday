/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.36.0.8091.03bcab5b3 modeling language!*/

package ca.mcgill.ecse321.eventregistration.model;


import jakarta.persistence.*;

import java.io.Serializable;

// line 36 "model.ump"
// line 66 "model.ump"
@Entity
public class Registration
{
  @Embeddable
  public static class RegistrationId implements Serializable {
    @ManyToOne
    Person registrant;
    @ManyToOne
    Event event;

    public RegistrationId() {
      super();
    }

    public RegistrationId(Person registrant, Event event) {
      super();
      this.registrant = registrant;
      this.event = event;
    }

    public Person getRegistrant() {
      return registrant;
    }

    public Event getEvent() {
      return event;
    }
  }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Associations
  @EmbeddedId
  private RegistrationId rid;

  //Helper Variables
  private int cachedHashCode;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  public Registration() {}

  public Registration(RegistrationId rid)
  {
    cachedHashCode = -1;
    this.rid = rid;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public RegistrationId getRegistrationId() {
    return rid;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setRegistrationId(RegistrationId aNewRid)
  {
    boolean wasSet = false;
    if (aNewRid != null)
    {
      rid = aNewRid;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Registration compareTo = (Registration)obj;
  
    if (getRegistrationId() == null && compareTo.getRegistrationId() != null)
    {
      return false;
    }
    else if (getRegistrationId() != null && !getRegistrationId().equals(compareTo.getRegistrationId()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getRegistrationId() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getRegistrationId().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    return cachedHashCode;
  }

  public void delete()
  {
    rid = null;
  }

}