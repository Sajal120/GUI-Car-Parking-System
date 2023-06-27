    /**

    A class that represents a car object with attributes such as the owner's name, car registration number, and owner's role.
    @version 21 April 2023
    @author Sajal Basnet
    */
public class Car {
    private String name;
    private String id;
    private String role;

     /**
     * Constructor to create a new Car object with the specified name, registration number, and owner role.
     *
     * @param name The name of the car owner.
     * @param id   The registration number of the car.
     * @param role The role of the car owner (visitor or staff).
     */
    
    public Car(String name, String id, String role) 
    {
        setName(name);
        setId(id);
        setRole(role);
    }

    
    /**
     * Default constructor to create a new Car object with default values.
     */
    
    public Car() 
    {
        setName("UNKNOWN");
        setId("00000");
        setRole("STAFF");
    }
    
    
    /**
     * Sets the name of the car owner.
     *
     * @param name The name of the car owner.
     */
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * Sets the registration number of the car.
     *
     * @param id The registration number of the car.
     */

    public void setId(String id) 
    {
        if (id.length() != 5) 
        {
            System.out.println("Car registration number needs to be 5 characters");
            return;
        }

        char firstChar = id.charAt(0);
        if (!Character.isLetter(firstChar)) 
        {
            System.out.println("First character of Car registration number needs to be a letter");
            return;
        }

        if (Character.isDigit(id.charAt(1)) && Character.isDigit(id.charAt(2)) && Character.isDigit(id.charAt(3)) && Character.isDigit(id.charAt(4))) 
        {
            this.id = id;
        } else 
        {
            System.out.println("Last four characters of Car registration number need to be digits");
        }
    }
    
     /**
     * Sets the role of the car owner.
     *
     * @param role The role of the car owner (visitor or staff).
     */

    public void setRole(String role) 
    {
        this.role = role;
    }
    
    /**
     * Gets the name of the car owner.
     *
     * @return The name of the car owner.
     */
    
    public String getName() 
    {
        return name;
    }
    
    /**
     * Gets the registration number of the car.
     *
     * @return The registration number of the car.
     */

    public String getId() 
    {
        return id;
    }
    
    /**
     * Gets the role of the car owner.
     *
     * @return The role of the car owner (visitor or staff).
     */

    public String getRole() 
    {
        return role;
    }
    
    /**
     * Returns a string representation of the car.
     *
     * @return A string representation of the car, including the registration number, owner name, and owner role.
     */

    public String toString() 
    {
        String desc = "Car[Registration Number: " + id +
                ", \nCar Owner Name: " + name +
                ", Owner Role: " + role + "]";

        return desc;
    }
}
