/**
 * The ApplicationFrame class is used to interact with the CarPark system by providing
 * the user with a menu of options to choose from. It uses a Scanner object to get
 * input from the user and displays output to the console.
 * @version 21 April 2023
 * @author Sajal Basnet
 */
import java.util.Scanner;
import java.util.Arrays;

public class Application
{
    private CarPark carpark;
    private Scanner kb;
    
    /**
     * Application constructor initializes the CarPark and Scanner objects.
     */
    public Application()
    {
        carpark = new CarPark(10);  
        kb = new Scanner(System.in);
    }
    
   /**
     * Displays the menu options to the user and handles user inputs.
     * This method provides a loop that presents the user with a menu
     * of actions they can perform on the car park until they choose to exit.
     */
    public void displayMenu()
    {
        
        int option = 0;  
        
       
        String[] allowedSlotTypes = {"visitor", "staff"};
        String[] allowedOwnerRoles = {"visitor", "staff"};
        
        do
        {
         System.out.println(" ......MENU ....... ");
         System.out.println(" 1 - Add a parking slot ");
         System.out.println(" 2 - Remove a parking slot ");
         System.out.println(" 3 - Display parking slot, car owner and the parked date/time");
         System.out.println(" 4 - Park a car into a slot   ");
         System.out.println(" 5 - Find a car  ");
         System.out.println(" 6 - Remove a car ");
         System.out.println(" 7 - Exit  ");
         System.out.println(" Choose:  ");
          
         String input = kb.nextLine();
         
        
        if (input.matches("[1-7]")) 
        {
            option = Integer.parseInt(input);
            
             switch(option)
             {
                 
                case 1:  
                        System.out.print("Enter the parking slot(Format like: A12): ");
                        String number = kb.next();
                        kb.nextLine();  
                        if (number.length() != 3 ||
                            !Character.isUpperCase(number.charAt(0)) || 
                            !Character.isDigit(number.charAt(1)) || 
                            !Character.isDigit(number.charAt(2))) {
                            System.out.println("Invalid parking slot number. Please enter a valid parking slot number where uppercase letter is followed by 2 digits(Format like: A12).");
                            break;
                        }
                        System.out.print("Enter the parking slot Type(Staff/Visitor): ");
                          String type = kb.nextLine().toLowerCase();

                        if (!Arrays.asList(allowedSlotTypes).contains(type)) 
                        {
                                System.out.println("Invalid parking slot type. Please enter either Visitor or Staff.");
                                break;
                        }
                        
                        ParkingSlot newSlot = new ParkingSlot(number, type);
                        if (carpark.addParkingSlot(newSlot)) 
                        {
                            System.out.println("Parking slot added successfully.");
                        } 
                        else 
                        {
                            System.out.println("Failed to add parking slot. A slot with the same number already exists or the car park is full.");
                        }
                        break;
                       
                
                case 2:  
                        System.out.print("Enter the parking slot Number to remove: ");
                        String numberToRemove = kb.next();
                        kb.nextLine(); // consume leftover newline character
                        if( carpark.removeParkingSlot(numberToRemove) == true )
                            System.out.println("The parking slot is removed successfully!");
                        else
                            System.out.println("There is no parking slot with number " + numberToRemove);
                        break;
                
                case 3:  
                        System.out.println(carpark);  
                        break; 
                  
               case 4:
                        System.out.print("Enter the parking slot number(Format like: A12): ");
                        String slotNumber = kb.next();
                        kb.nextLine(); // consume leftover newline character

                        ParkingSlot slot = carpark.findParkingSlot(slotNumber);
                        if (slot == null) 
                        {
                            System.out.println("There is no parking slot with number " + slotNumber);
                            break;
                        }

                        if (slot.getOwner() != null) 
                        {
                            System.out.println("The parking slot is already occupied by a car");
                            break;
                        }

                        System.out.print("Enter the owner name: ");
                        String ownerName = kb.next();
                        kb.nextLine();
                        System.out.print("Enter the car registration number(Format like: A1234): ");
                        String regNum = kb.next();
                        kb.nextLine();

                        if (!regNum.matches("[A-Za-z]{1}[0-9]{4}")) 
                        {
                            System.out.println("Invalid car registration number. Please enter a valid registration number (Format like: A1234).");
                            break;
                        }

                        System.out.print("Enter the car owner role(staff/visitor): ");
                        String ownerRole = kb.next().toLowerCase();
                        kb.nextLine();
                        if (!Arrays.asList(allowedOwnerRoles).contains(ownerRole)) 
                        {
                            System.out.println("Invalid owner role. Please enter either Visitor or Staff.");
                            break;
                        }

                        if (!slot.getType().equals(ownerRole)) 
                        {
                            System.out.println("The parking slot type does not match the owner role. Please park the car in the appropriate slot.");
                            break;
                        }

                        Car car = new Car(ownerName, regNum, ownerRole);
                        slot.addOwner(car);
                        System.out.println("The car is parked in the slot " + slot.getNumber() + " by " + slot.getOwner().getName() +
                        ". Date and Time of addition: " + slot.getParkedTime());
                        break;


               case 5:
                        System.out.print("Enter the car registration number: ");
                        String PregNum = kb.next();
                        kb.nextLine(); // consume leftover newline character
                        ParkingSlot slots = carpark.findParkingSlotByCarReg(PregNum);
                        if (slots == null) 
                        {
                            System.out.println("There is no car with registration number " + PregNum);
                            break;
                        }
                            System.out.println("The car with registration number " + PregNum + " is parked in the slot " + slots.getNumber());
                            break;


                case 6:
                        System.out.print("Enter the car registration number to remove the owner: ");
                        String regNumberToRemove = kb.next();
                        kb.nextLine();  
                        ParkingSlot parkingSlotToRemove = carpark.findParkingSlotByCarReg(regNumberToRemove);
                        if (parkingSlotToRemove == null) 
                        {
                            System.out.println("There is no car with registration number " + regNumberToRemove);
                            break;
                        }
                        if (parkingSlotToRemove.getOwner() == null) 
                        {
                            System.out.println("The parking slot is empty. No owner to remove!");
                            break;
                        }
                        parkingSlotToRemove.removeOwner();
                        System.out.println("The owner is removed from the car with registration number " + regNumberToRemove);
                        break;

                 case 7:  
                        System.out.println("\n\n\n\n\t\t\t Thanks for using the application ... \n\n");
                        break;
            
                default:
                        System.out.println("Invalid option. Try again(1-7)....");
                        break;
                
                
             } 
            } 
            
        } while(option != 7);
    }
    
    /**
     * The main method serves as the entry point for the application.
     * This method creates an Application object and calls the displayMenu method.
     * 
     * @param args command line arguments (unused in this application)
     */
    
    public static void main(String []args)
    {  
        Application ourCarPark = new Application();   
        ourCarPark.displayMenu();
                
    }
}
