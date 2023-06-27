/**
 * The ApplicationFrame class provides a GUI to interact with the CarPark system, allowing
 * the user to perform various actions such as parking and removing cars, finding parking slots,
 * and displaying the current state of the car park, etc. The interface includes buttons for each
 * action, and a text area to display the output.
 * @version 21 April 2023
 * @author Sajal Basnet
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

/**
 * This class represents an application frame for a car park management system.
 * The frame contains various buttons for managing the car park and a text area
 * to display information related to the actions performed.
 */

public class ApplicationFrame extends JFrame implements ActionListener {
    private CarPark carpark;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    private JTextArea txtDisplayData;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;

    
    /**
     * Constructor for ApplicationFrame. Initializes the CarPark object,
     * the buttons for various actions, and the text area for displaying data.
     * It also calls methods to initialize the graphical user interface and the menu.
     */
    
    public ApplicationFrame() {
        
        // Create a car park with 10 parking slots(Assuming only 10 parking slots are available)
        carpark = new CarPark(10);
        
        // Initialize action buttons
        btn1 = new JButton("1: Add a Parking Slot");
        btn2 = new JButton("2: Remove a Parking Slot");
        btn3 = new JButton("3: Display Car Parked");
        btn4 = new JButton("4: Park a Car into a Slot");
        btn5 = new JButton("5: Find a Car");
        btn6 = new JButton("6: Remove a Car");
        btn7 = new JButton("7: Exit");
        
        // Initialize the text area for displaying data
        txtDisplayData = new JTextArea();
        initGUI();
        initMenu();
              
        
    }
    
     /**
     * Initializes the menu bar with a file menu containing a creator info
     * menu item and an exit menu item.
     */
    
    private void initMenu() 
    {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
    
        // Add creator info menu item with an action listener
        
        JMenuItem creatorMenuItem = new JMenuItem("Creator Info");
        creatorMenuItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                JOptionPane.showMessageDialog(ApplicationFrame.this, "This Car Park Management System was created by Sajal Basnet(104170062)!!");
            }
        });
    
        // Add exit menu item with an action listener
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
            System.exit(0); // exit the system
        }
        });
    
        // Add menu items to the file menu and add the file menu to the menu bar
        fileMenu.add(creatorMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

     /**
     * Initializes the graphical user interface by setting up the frame title,
     * size, and layout. It also sets up the action buttons, their fonts,
     * and colors, as well as the text area for displaying data.
     */

    private void initGUI() 
    {
        
        // Set up the frame properties
        setTitle("Car Park Management");
        setBounds(60,60,1400,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add action listeners to the buttons
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        
        // Set up the button fonts
        Font font = new Font("Script MT Bold", Font.BOLD, 30);
        btn1.setFont(font);
        btn2.setFont(font);
        btn3.setFont(font);
        btn4.setFont(font);
        btn5.setFont(font);
        btn6.setFont(font);
        btn7.setFont(font);

        // Set up the button colors
        btn1.setBackground(Color.MAGENTA);
        btn2.setBackground(Color.MAGENTA);
        btn3.setBackground(Color.YELLOW);
        btn4.setBackground(Color.MAGENTA);
        btn5.setBackground(Color.MAGENTA);
        btn6.setBackground(Color.MAGENTA);
        btn7.setBackground(Color.RED);
        
        // Create a JPanel for the action buttons and set its layout
        JPanel panelMenuButtons = new JPanel();
        LayoutManager layout = new GridLayout(7,2);
        panelMenuButtons.setLayout(layout);
        
        // Add the action buttons to the panel
        panelMenuButtons.add(btn1);
        panelMenuButtons.add(btn2);
        panelMenuButtons.add(btn3);
        panelMenuButtons.add(btn4);
        panelMenuButtons.add(btn5);
        panelMenuButtons.add(btn6);
        panelMenuButtons.add(btn7);

        // Create a JPanel for the text area
        JPanel panelRight = new JPanel(new GridLayout(1,1));
        txtDisplayData.setBackground(Color.BLACK);
        panelRight.add(txtDisplayData);
         
        // Create a JPanel to contain both the button panel and the text area panel
        JPanel allPanels = new JPanel(new GridLayout(1,1));
        allPanels.add(panelMenuButtons);
        allPanels.add(panelRight);
          
        // Add the container panel to the frame
        this.add(allPanels);
        
        // Set up the text area properties
        txtDisplayData.setForeground(Color.WHITE);
        txtDisplayData.setText("Welcome to the Car Park Manegement System!!" );
        txtDisplayData.setFont(new Font("Arial", Font.BOLD, 30));

    }
    
    /**
     * Displays a confirmation dialog to confirm the cancellation of an action.
     * 
     * @return true if the user confirms the cancellation, false otherwise.
     */
    private boolean confirmCancel() 
    {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Confirm Cancel", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }
    
    /** This method handles the actions performed by clicking the buttons*/
    public void actionPerformed(ActionEvent e) 
    {
        
        // Button 1 section - Adding a new parking slot
    if (e.getSource() == btn1) 
    {
        // Handle the action for adding a new parking slot

        // Get the parking slot number from the user
        String number = null;
        do 
        {
            number = JOptionPane.showInputDialog(this, "Enter the parking slot (Format like: A12):");
            if (number == null || number.trim().equals(""))
            {
                if (confirmCancel()) 
                {
                // return without displaying any error message
                return;
                }
            }
        } while (number == null || number.trim().equals(""));
        
        // Validate the parking slot number
        if (number.length() != 3 ||
            !Character.isUpperCase(number.charAt(0)) ||
            !Character.isDigit(number.charAt(1)) ||
            !Character.isDigit(number.charAt(2))) 
            {
                JOptionPane.showMessageDialog(this, "Invalid parking slot number. Please enter a valid parking slot number where uppercase letter is followed by 2 digits(Format like: A12).");
                return;
            }

        boolean validType = false;
        String type = null;
        do 
        {
            type = JOptionPane.showInputDialog(this, "Enter the parking slot type (Staff/Visitor):");
            if (type == null || type.trim().equals("")) 
            {
                if (confirmCancel()) 
                {
                // return without displaying any error message
                return;
                }
            } 
            else 
            {
                type = type.toLowerCase();
                String[] allowedSlotTypes = {"visitor", "staff"};
                if (Arrays.asList(allowedSlotTypes).contains(type)) 
                {
                    validType = true;
                } 
                else 
                {
                JOptionPane.showMessageDialog(this, "Invalid parking slot type. Please enter either Visitor or Staff.");
                }
            }
        } while (!validType);

        // Create a new ParkingSlot object and try to add it to the car park
        ParkingSlot newSlot = new ParkingSlot(number, type);
        if (carpark.addParkingSlot(newSlot)) 
        {
            txtDisplayData.setText("Parking slot added successfully.");
            if (newSlot.getType().equals("visitor")) 
            {
                btn1.setBackground(Color.MAGENTA); // set MAGENTA color for visitor parking slot
            } 
            else if (newSlot.getType().equals("staff")) 
            {
                btn1.setBackground(Color.GRAY); // set GRAY color for staff parking slot
            }
        } 
        else 
        {
            txtDisplayData.setText("Failed to add parking slot. \nA slot with the same number already exists or the car park is full.");
        }

        txtDisplayData.setForeground(Color.RED);
        txtDisplayData.setFont(new Font("Arial", Font.BOLD, 20));
    }
       
        // Button 2 section - Removing a parking slot
    else if (e.getSource() == btn2) 
        {
            // Handle the action for removing a parking slot

            // Get the parking slot number to remove from the user
            String numberToRemove = null;
            do 
            {
                numberToRemove = JOptionPane.showInputDialog(this, "Enter the parking slot number to remove:");
                if (numberToRemove == null || numberToRemove.trim().equals("")) 
                {
                    if (confirmCancel()) 
                    {
                        // return without displaying any error message
                        return;
                    }
                }
            } while (numberToRemove == null || numberToRemove.trim().equals(""));
              
            // Validate the parking slot number
            if (numberToRemove == null || numberToRemove.trim().equals("")) 
            {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid parking slot number to remove.");
                return;
            }
    
            // Find the parking slot to remove
            ParkingSlot slotToRemove = carpark.findParkingSlot(numberToRemove);

            if (slotToRemove == null) 
            {
                txtDisplayData.setText("There is no parking slot with number " + numberToRemove);
                return;
            }
    
            // Check if the parking slot is found
            String slotType = slotToRemove.getType();
            if (carpark.removeParkingSlot(numberToRemove)) 
            {
                txtDisplayData.setText("The parking slot is removed successfully!");
        
                if (slotType.equals("visitor")) 
                {
                    btn2.setBackground(Color.MAGENTA); // set color for visitor parking slot
                } 
                else if (slotType.equals("staff")) 
                {
                    btn2.setBackground(Color.GRAY); // set color for staff parking slot
                }
            } 
            else 
            {
                txtDisplayData.setText("Failed to remove parking slot. \nThe slot may be occupied or the number is invalid.");
            }
            
            txtDisplayData.setForeground(Color.BLUE);
            txtDisplayData.setFont(new Font("Arial", Font.BOLD, 20));
            
        }
        
        // Button 3 section - Display the current status of the carpark
        else if (e.getSource() == btn3) 
        {   
            // Set display text color to yellow
            txtDisplayData.setForeground(Color.YELLOW);
            // Display the current status of the carpark
            txtDisplayData.setText(carpark.toString());
        }
        
        // Button 4 section - Park a car in a specified slot
        else if (e.getSource() == btn4) 
        {
            String slotNumber = null;
            while (true) 
            {
                 // Prompt the user to enter the parking slot number
                slotNumber = JOptionPane.showInputDialog(this, "Enter the parking slot number to park a car (Format like: A12):");
                if (slotNumber == null || slotNumber.trim().equals("")) 
                {
                    if (confirmCancel()) 
                    {
                        // return without displaying any error message
                        return;
                    }   
                } 
                else 
                {
                     break;
                }
            }

        // Find the parking slot with the given slot number
        ParkingSlot slot = carpark.findParkingSlot(slotNumber);
        if (slot == null) 
        {
            txtDisplayData.setText("There is no parking slot with number " + slotNumber + ".");
            return;
        }
        
        // Check if the parking slot is already occupied
        if (slot.getOwner() != null) 
        {
            txtDisplayData.setText("The parking slot is already occupied by a car.");
            return;
        }

        String ownerName = null;
        while (true) 
        {
            ownerName = JOptionPane.showInputDialog(this, "Enter the owner name:");
            if (ownerName == null || ownerName.trim().equals("")) 
            {
                if (confirmCancel()) 
                {
                    // return without displaying any error message
                    return;
                }
            } 
            else 
            {
                break;
            }
        }

        String regNum = null;
        while (true) 
        {
            // Prompt the user to enter the car registration number (in the format A1234)
            regNum = JOptionPane.showInputDialog(this, "Enter the car registration number (Format like: A1234):");
            if (regNum == null || regNum.trim().equals("")) 
            {
                if (confirmCancel())
                {
                    // return without displaying any error message
                    return;
                }
            }
            else 
            {
                break;
            }
        }

        // Check if the car registration number matches the specified format
        if (!regNum.matches("[A-Za-z]{1}[0-9]{4}")) 
        {
            JOptionPane.showMessageDialog(this, "Invalid car registration number. Please enter a valid registration number (Format like: A1234).");
            return;
        }

        String ownerRole = null;
        while (true) 
        {
             // Prompt the user to enter the car owner role (Staff/Visitor)
            ownerRole = JOptionPane.showInputDialog(this, "Enter the car owner role (Staff/Visitor):");
            if (ownerRole == null || ownerRole.trim().equals("")) 
            {
                if (confirmCancel())    
                {
                    // return without displaying any error message
                    return;
                }
            } 
        else 
        {
            break;
        }
        }
        ownerRole = ownerRole.toLowerCase();
        String[] allowedOwnerRoles = {"visitor", "staff"};
        if (!Arrays.asList(allowedOwnerRoles).contains(ownerRole)) 
        {
            JOptionPane.showMessageDialog(this, "Invalid owner role. Please enter either Visitor or Staff.");
            return;
        }

        if (!slot.getType().equals(ownerRole)) 
        {
            JOptionPane.showMessageDialog(this, "The parking slot type does not match the owner role. Please park the car in the appropriate slot.");
            return;
        }

        // Create a new Car object with the provided owner name, registration number, and owner role
        Car car = new Car(ownerName, regNum, ownerRole);
        slot.addOwner(car);
        txtDisplayData.setText("The car is parked in the slot " + slot.getNumber() + " by " + slot.getOwner().getName() +
            ". Date and Time of addition: " + slot.getParkedTime() + ".");

        if (slot.getType().equals("visitor")) 
        {
            btn4.setBackground(Color.MAGENTA);
        } 
        else if (slot.getType().equals("staff")) 
        {
        btn4.setBackground(Color.GRAY);
        }

        txtDisplayData.setForeground(Color.GREEN);
        txtDisplayData.setFont(new Font("Arial", Font.BOLD, 20));
        String displayText = carpark.toString();
        String[] displayLines = displayText.split("\n");
        txtDisplayData.setText("");
            for (String line : displayLines) 
            {
                int lineLength = line.length();
                for (int i = 0; i < lineLength; i += 60) 
                {
                    int endIndex = Math.min(i + 60, lineLength);
                    txtDisplayData.append(line.substring(i, endIndex) + "\n");
                }
            }
         
        }

        // Button 5 section - Find a car owner by its registration number
       else if (e.getSource() == btn5) 
       {
            ParkingSlot slot = null;
            String regNum = null;

            do 
            {
                regNum = JOptionPane.showInputDialog(this, "Enter the car registration number to find the parking slot: (Format like: A1234)");
                if (regNum == null) 
                {
                    if (confirmCancel()) 
                    {
                        // return without displaying any error message
                        return;
                    }
                }
            } while (regNum == null || regNum.trim().equals(""));

            do 
            {
                // Find the parking slot containing the car with the specified registration number
                slot = carpark.findParkingSlotByCarReg(regNum);
                if (slot == null) 
                {
                    int confirm = JOptionPane.showConfirmDialog(this, "There is no car with registration number " + regNum + ". Do you want to try again?", "Invalid Car Registration Number", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) 
                    {
                        do 
                        {
                            regNum = JOptionPane.showInputDialog(this, "Enter the car registration number to find the parking slot:");
                            if (regNum == null) 
                            {
                                if (confirmCancel()) 
                                {
                                // return without displaying any error message
                                return;
                                }
                            }
                        } while (regNum == null || regNum.trim().equals(""));
                    }   
                    else 
                    {
                        return;
                    }
                }
            } while (slot == null);

            txtDisplayData.setText("The car with registration number " + regNum + " is parked in the slot " + slot.getNumber() + ".");
            
            // Get the type of the parking slot
            if (slot.getType().equals("visitor")) 
            {
                btn5.setBackground(Color.MAGENTA); // set color for visitor parking slot
            } 
            else if (slot.getType().equals("staff")) 
            {
                btn5.setBackground(Color.GRAY); // set color for staff parking slot
            }
            txtDisplayData.setForeground(Color.MAGENTA);
            txtDisplayData.setFont(new Font("Arial", Font.BOLD, 20));
        }


        // Button 6 section - Remove a car owner by its registration number
        else if (e.getSource() == btn6) 
        {
    
            ParkingSlot slotToRemove = null;
            String regNum = null;

            do 
            {
                regNum = JOptionPane.showInputDialog(this, "Enter the car registration number to remove the owner: (Format like: A1234)");
                if (regNum == null) 
                {
                    if (confirmCancel()) 
                    {
                        // return without displaying any error message
                        return;
                    }
                }
            } while (regNum == null || regNum.trim().equals(""));

            do 
            {
                // Find the parking slot containing the car with the specified registration number
                slotToRemove = carpark.findParkingSlotByCarReg(regNum);
                if (slotToRemove == null) 
                {
                    int confirm = JOptionPane.showConfirmDialog(this, "There is no car with registration number " + regNum + ". Do you want to try again?", "Invalid Car Registration Number", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) 
                    {
                        do 
                        {
                            regNum = JOptionPane.showInputDialog(this, "Enter the car registration number to remove the owner:");
                            if (regNum == null) 
                            {
                                if (confirmCancel()) 
                                {
                                    // return without displaying any error message
                                    return;
                                }
                            }
                        } while (regNum == null || regNum.trim().equals(""));
                    } 
                    else 
                    {
                        return;
                    }
                }
            } while (slotToRemove == null);

            if (slotToRemove.getOwner() == null) 
            {
                txtDisplayData.setText("The parking slot is empty. No owner to remove!");
                return;
            }

            // Get the type of the parking slot
            String type = slotToRemove.getType();
            slotToRemove.removeOwner();
            txtDisplayData.setText("The owner is removed from the car with registration number " + regNum + ".");

            switch (type) 
            {
                case "visitor":
                    btn6.setBackground(Color.MAGENTA);
                    break;
                case "staff":
                    btn6.setBackground(Color.GRAY);
                    break;
                default:
                    btn6.setBackground(Color.WHITE);
                    break;
            }

            txtDisplayData.setForeground(Color.GREEN);
            txtDisplayData.setFont(new Font("Arial", Font.BOLD, 20));
        }


        // Button 7 section - Exit the application
        else if (e.getSource() == btn7) 
        {
            // Prompt the user to confirm the exit
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the application?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            // If the user confirms the exit, close the application
            if (confirm == JOptionPane.YES_OPTION) 
            {
            System.exit(0);
            }
        }

        
    }
    
    // The main method serves as the entry point for the application.
    public static void main(String[] args) 
    {
        // Create an instance of the ApplicationFrame class
        ApplicationFrame parking = new ApplicationFrame();
        // Set the application window to visible
        parking.setVisible(true);
    }
}
