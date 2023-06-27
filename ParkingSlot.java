/**

ParkingSlot class represents a parking slot in a parking lot.
It stores the parking slot number, type, car owner, and the parked time.
@version 21 April 2023
@author Sajal Basnet
*/

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Represents a parking slot in a car park.
 */


public class ParkingSlot
{
    private String number;  
    private String type;
    private Car owner; 
    private LocalDateTime parkedTime;
    
/**
 * Constructor to initialize the ParkingSlot object.
 * @param number The parking slot number.
 * @param type The parking slot type (visitor or staff).
 */
    
    public ParkingSlot(String number, String type)
    {
        this.number = number;
        this.type = type;
        this.owner = null; 
        this.parkedTime = null; 
    }
    
      /**
     * Sets the number of the parking slot.
     *
     * @param number The parking slot number.
     */

    public void setNumber(String number)
    {
        this.number = number;
    }
    
      /**
     * Sets the type of the parking slot.
     *
     * @param type The parking slot type (visitor or staff).
     */
    
    public void setType(String type)
    {   
        this.type = type;
    }
    
      /**
     * Returns the parked time of the car in the parking slot.
     *
     * @return The LocalDateTime object representing the parked time.
     */
 
    public LocalDateTime getParkedTime() 
    { 
        return parkedTime;
    }
    
     /**
     * Sets the owner of the parking slot.
     *
     * @param owner The Car object representing the car owner.
     */
    
    public void setOwner(Car owner) 
    {
    this.owner = owner;
    }

 
     /**
     * Adds an owner to the parking slot based on a Car object.
     *
     * @param param The Car object representing the car owner.
     */
    
    public void addOwner (Car param)
    {
        if(this.owner == null)
        {
           // Check if the parking slot is empty
           String tp = getType();
           String rl = param.getRole();
           
           // Check if the owner's role and the parking slot type match
           
           if ((tp.equalsIgnoreCase( "Visitor" ) && rl.equalsIgnoreCase("Visitor"))
                                           ||
               (tp.equalsIgnoreCase("Staff") && rl.equalsIgnoreCase("Staff")))
            {
                       this.owner = param;
                       this.parkedTime = LocalDateTime.now();
            }                                                   
            else
                System.out.println("The owner's role(staff/visitor)and the parking slot type don't match");
                
        }
            
        else
            System.out.println("This parking slot already has a car owner!");
        
    }
    
     /**
     * Adds an owner to the parking slot based on the owner's name, ID, and role.
     *
     * @param name  The name of the car owner.
     * @param id    The ID of the car owner.
     * @param role  The role of the car owner (staff/visitor).
     */
    
    public void addOwner(String name, String id, String role )
    {
        
        // Check if the parking slot is empty
        if(this.owner == null)
        {
            // Check if the owner's role and the parking slot type match
           if ((type.equalsIgnoreCase( "Visitor" ) && role.equalsIgnoreCase("Visitor"))
                ||
               (type.equalsIgnoreCase("Staff") && role.equalsIgnoreCase("Staff")))
                {
                    Car param = new Car ( name, id, role);
                    this.owner = param;
                }
            else 
                System.out.println("The owner's role(staff/visitor)and the parking slot type don't match");
        }
        else
            System.out.println("This Parking slot already has a car owner!");
    }
 
     /**
     * Removes the owner of the parking slot.
     */
    
    public void removeOwner()
    {
        this.owner = null ;
        
    }
    
     /** * Removes the owner of the parking slot by the owner's name.
     *
     * @param name The name of the car owner.
     */
    
    public void removeOwner(String name)
    {
        if(owner.getName().equalsIgnoreCase( name) )
        {
            this.owner = null;
        }
        else
        {
            System.out.println("There is no car owner named" + name + "whose car is parked in the parking slot");
        }
    }
    
    /**
     * Returns the number of the parking slot.
     *
     * @return The parking slot number.
     */
    
    public String getNumber()
    {
        return number;
    }
    
    /**
     * Returns the type of the parking slot.
     *
     * @return The parking slot type (visitor or staff).
     */
    
    public String getType()
    {
        return type;
    }
    
    /**
     * Returns the owner of the parking slot.
     *
     * @return The Car object representing the car owner.
     */
    
    public Car getOwner()
    {
        return owner;
    }
    
   /**
     * Returns the parking time length as a formatted string.
     *
     * @return A string representing the parking time length (hours, minutes, seconds).
     */ 
   public String getParkingTimeLength() 
   {
        if (parkedTime != null) {
            Duration duration = Duration.between(parkedTime, LocalDateTime.now());
            long seconds = duration.getSeconds();
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            seconds = seconds % 60;
            return hours + " hours " + minutes + " minutes " + seconds + " seconds";
        } else {
            return "";
        }
    }

    /**
     * Returns a string representation of the ParkingSlot object.
     *
     * @return A string representation of the ParkingSlot object.
     */
    
    
    public String toString()
    {
        String desc = "Parking Slot[#: " + number +
                      ", Type: " + type;
                               
        if (owner == null)
            desc += ", \nCar Owner: No Owner is Assigned]";
        else
            desc += ", \nCar Owner: " + owner.toString()  +  "\nParked Date & Time in a proper format: " +   "\n"+getParkingTimeLength()  +  "]"; // include the parked time
      
        return desc;
    }
}
