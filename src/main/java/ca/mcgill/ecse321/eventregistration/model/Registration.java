package ca.mcgill.ecse321.eventregistration.model;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.35.0.8043.819096d90 modeling language!*/


import jakarta.persistence.*;

// line 36 "model.ump"
// line 66 "model.ump"
@Entity
public class Registration
{

  public Registration() {}

  //------------------------
  // MEMBER VARIABLES
  //------------------------
  @Embeddable
  public class RegistrationId {
    int pid;
    int eid;

    public RegistrationId() {

    }

    public RegistrationId(int pid, int eid) {
      this.pid = pid;
      this.eid = eid;
    }
  }

  //Registration Associations
  @ManyToOne(fetch=FetchType.LAZY)
  private Person registrant;
  @ManyToOne(fetch=FetchType.LAZY)
  private Event event;

  //Helper Variables
  @EmbeddedId
  private RegistrationId rid;
  private int cachedHashCode;
  private boolean canSetRegistrant;
  private boolean canSetEvent;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(Person aRegistrant, Event aEvent)
  {
    cachedHashCode = -1;
    canSetRegistrant = true;
    canSetEvent = true;
    if (!setRegistrant(aRegistrant))
    {
      throw new RuntimeException("Unable to create Registration due to aRegistrant. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setEvent(aEvent))
    {
      throw new RuntimeException("Unable to create Registration due to aEvent. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Person getRegistrant()
  {
    return registrant;
  }
  /* Code from template association_GetOne */
  public Event getEvent()
  {
    return event;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setRegistrant(Person aNewRegistrant)
  {
    boolean wasSet = false;
    if (!canSetRegistrant) { return false; }
    if (aNewRegistrant != null)
    {
      registrant = aNewRegistrant;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setEvent(Event aNewEvent)
  {
    boolean wasSet = false;
    if (!canSetEvent) { return false; }
    if (aNewEvent != null)
    {
      event = aNewEvent;
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Registration compareTo = (Registration)obj;
  
    if (getRegistrant() == null && compareTo.getRegistrant() != null)
    {
      return false;
    }
    else if (getRegistrant() != null && !getRegistrant().equals(compareTo.getRegistrant()))
    {
      return false;
    }

    if (getEvent() == null && compareTo.getEvent() != null)
    {
      return false;
    }
    else if (getEvent() != null && !getEvent().equals(compareTo.getEvent()))
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
    if (getRegistrant() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getRegistrant().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getEvent() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getEvent().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetRegistrant = false;
    canSetEvent = false;
    return cachedHashCode;
  }

  public void delete()
  {
    registrant = null;
    event = null;
  }

}