/**
 * Represents a car park with a certain capacity and a collection of parking slots.
   @version 21 April 2023
   @author Sajal Basnet
*/

import java.util.ArrayList;


/**
 * Represents a car park with parking slots.
 */
public class CarPark {
    private ArrayList<ParkingSlot> parkingSlots;
    private int capacity;

    /**
     * Constructor to initialize the CarPark object.
     *
     * @param capacity The maximum capacity of the car park.
     */
    
    public CarPark(int capacity) 
    {
        this.capacity = capacity;
        parkingSlots = new ArrayList<ParkingSlot>();
    }
    
    
    /**
     * Adds a new parking slot to the car park.
     *
     * @param newSlot The new ParkingSlot object to add.
     * @return true if the parking slot is added successfully, false otherwise.
     */

    public boolean addParkingSlot(ParkingSlot newSlot) 
    {
        if (parkingSlots.size() >= capacity) 
        {
            return false;
        }

        for (ParkingSlot slot : parkingSlots) 
        {
            if (slot.getNumber().equals(newSlot.getNumber())) 
            {
                return false;
            }
        }

        parkingSlots.add(newSlot);
        return true;
    }
    
    
     /**
     * Finds a parking slot by its number.
     *
     * @param number The number of the parking slot to find.
     * @return The ParkingSlot object if found, null otherwise.
     */

    public ParkingSlot findParkingSlot(String number) 
    {
        for (ParkingSlot slot : parkingSlots) 
        {
            if (slot.getNumber().equals(number)) 
            {
                return slot;
            }
        }
        return null;
    }

    
    /**
     * Finds a parking slot by the car's registration number.
     *
     * @param regNum The registration number of the car.
     * @return The ParkingSlot object if found, null otherwise.
     */
    
    public ParkingSlot findParkingSlotByCarReg(String regNum) 
    {
        for (ParkingSlot slot : parkingSlots) 
        {
            if (slot.getOwner() != null && slot.getOwner().getId().equalsIgnoreCase(regNum)) 
            {
                return slot;
            }
        }
        return null;
    }
    
    /**
     * Checks if a parking slot is occupied.
     *
     * @param slotNumber The number of the parking slot.
     * @return true if the parking slot is occupied, false otherwise.
     */

    public boolean isOccupied(String slotNumber) 
    {
        ParkingSlot slot = findParkingSlot(slotNumber);
        if (slot != null && slot.getOwner() != null) 
        {
            return true;
        } else 
        {
            return false;
        }
    }

     /**
     * Removes a parking slot by its number.
     *
     * @param slotNumber The number of the parking slot to remove.
     * @return true if the parking slot is removed successfully, false otherwise.
     */
    
    public boolean removeParkingSlot(String slotNumber)
    {
        int index = findParkingSlotIndex(slotNumber);
        if (index != -1) 
        {
            parkingSlots.remove(index);
            return true;
        }
    return false;
    }

    
    /**
     * Finds the index of a parking slot by its number.
     *
     * @param slotNumber The number of the parking slot.
     * @return The index of the parking slot if found, -1 otherwise.
     */

    private int findParkingSlotIndex(String slotNumber) 
    {
        for (int i = 0; i < parkingSlots.size(); i++) 
        {
            if (parkingSlots.get(i).getNumber().equalsIgnoreCase(slotNumber)) 
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Finds a car by its registration number.
     *
     * @param regNum The registration number of the car.
     * @return The Car object if found, null otherwise.
     */

    public Car findCarByRegNum(String regNum) 
    {
        for (ParkingSlot slot : parkingSlots) 
        {
            if (slot.getOwner() != null && slot.getOwner().getId().equals(regNum)) 
            {
                return slot.getOwner();
            }
        }
        return null;
    }
    
    /**
     * Checks if a car can park in a given parking slot.
     *
     * @param slot The parking slot to check.
     * @param car  The car to check.
     * @return true if the car can park in the parking slot, false otherwise.
     */

    public boolean canParkCar(ParkingSlot slot, Car car) 
    {
        if (car.getRole().equalsIgnoreCase(slot.getType())) 
        {
            return true;
        }
        return false;
    }


    /**
     * Finds an available parking slot for the given role.
     *
     * @param role The role of the user (visitor or staff).
     * @return The available ParkingSlot object if found, null otherwise.
     */
    
    public ParkingSlot findAvailableParkingSlot(String role) 
    {
        for (ParkingSlot slot : parkingSlots) 
        {
            if (slot != null && slot.getOwner() == null && slot.getType().equalsIgnoreCase(role)) 
            {
                return slot;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the car park, including all parking slots.
     *
     * @return A string representation of the car park.
     */
    @Override
    public String toString() 
    {
        String result = "Parking Slots:\n";
        for (ParkingSlot slot : parkingSlots) {
            result += slot + "\n";
        }
        return result;
    }
}
