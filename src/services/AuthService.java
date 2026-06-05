package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import security.PasswordHasher;
import security.SystemLogger;

/*
 * This class handles the secure authentication process
 * for the ShipTrack system.
 *
 * The class is responsible for:
 * - Reading login credentials from the user
 * - Hashing passwords using the SHA-256 algorithm
 * - Reading stored user data from users.txt
 * - Comparing entered credentials with stored credentials
 * - Detecting invalid login attempts
 * - Checking if user accounts are locked
 * - Counting failed login attempts
 * - Displaying login status messages
 * - Writing security and login activities into the log file
 * - Handling file reading exceptions securely
 */

public class AuthService {
// Store currently logged-in user information
public static String currentUserId;
public static String currentUserRole;


    // Method for user login
    public static void login() {

        // Create Scanner object for user input
        Scanner input = new Scanner(System.in);

        // Ask the user to enter ID
        System.out.print("Enter ID: ");
        String enteredId = input.nextLine();

        // Ask the user to enter password
        System.out.print("Enter Password: ");
        String enteredPassword = input.nextLine();

        // Hash the entered password using SHA-256
        String hashedPassword =PasswordHasher.hashPassword(enteredPassword);

        try {

            // Open users.txt file for reading
            BufferedReader br =
                    new BufferedReader(
                            new FileReader("data/users.txt")
                    );

            String line;

            
StringBuilder updatedFile =  new StringBuilder();
            int failedAttempts = 0;

            // Read the file line by line
            while ((line = br.readLine()) != null) {

                // Split user data using comma separator
                String[] data = line.split(",");
                // Skip invalid lines
if (data.length < 7) {

    continue;

}

                // Get stored ID from the file
                String storedId = data[2];

                // Get stored hashed password from the file
                String storedPasswordHash = data[4];

                boolean locked =
                Boolean.parseBoolean(data[5]);

                failedAttempts =  Integer.parseInt(data[6]);

                // Invalid password for existing ID
if (storedId.equals(enteredId)
        &&
        !hashedPassword.equals(storedPasswordHash)) {

    failedAttempts++;

    // Save updated failed attempts
    data[6] =
            String.valueOf(failedAttempts);

    System.out.println(
            "Invalid Login"
    );

    System.out.println(
            "Failed Attempts: "
                    + failedAttempts
    );

    SystemLogger.writeToLog(
            "Invalid login attempt for ID: "
                    + enteredId
    );

    // Lock account if max reached
    if (failedAttempts >= AdminService.maxLoginAttempts) {

        data[5] = "true";

        System.out.println(
                "Account Locked Due To Too Many Failed Attempts."
        );

        SystemLogger.writeToLog(
                "Account locked after failed attempts for ID: "
                        + enteredId
        );

    }

}


                if (storedId.equals(enteredId) && locked) {

    System.out.println("Account is locked.");

    SystemLogger.writeToLog(
            "Locked account login attempt for ID: "
                    + enteredId
    );

    updatedFile.append(
            String.join(",", data)
    ).append("\n");

    continue;
}


                // Compare entered credentials with stored credentials
if (enteredId.equals(storedId)
        &&
        hashedPassword.equals(storedPasswordHash)) {

    

    // Reset failed attempts
    data[6] = "0";

    // Save logged-in user info
    currentUserId = storedId;

    currentUserRole = data[0];

    // Display success message
    System.out.println("Login Successful");

    // Write INFO log for successful login
    SystemLogger.writeToLog(
            "Successful login for ID: "
                    + enteredId
    );

    // Save updated line before continue
    updatedFile.append(
            String.join(",", data)
    ).append("\n");

    continue;
}
                updatedFile.append(String.join(",", data)).append("\n");

            }

            br.close();

// Rewrite updated users file
FileWriter fw =
        new FileWriter(
                "data/users.txt"
        );

fw.write(
        updatedFile.toString()
);

fw.close();

            

        }

        // Handle file reading errors
        catch (IOException e) {

            // Display generic error message to the user
            System.out.println( "Error reading user data." );

            // Write error details into the log file
            SystemLogger.writeToLog("Error during login",  e);

        }

        

    }


    // Method for customer registration
public static void registerCustomer() {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Name: ");
    String name = input.nextLine();

    System.out.print("Enter ID: ");
    String id = input.nextLine();

    System.out.print("Enter Phone: ");
    String phone = input.nextLine();

    System.out.print("Enter Password: ");
    String password = input.nextLine();

    // Keep asking until password becomes strong
while (!PasswordPolicyService.isStrongPassword(password)) {
    System.out.println( "Weak Password. Try Again." );
    System.out.print(  "Enter Password: ");
    password = input.nextLine();
}

    // Hash password before saving
    String hashedPassword = PasswordHasher.hashPassword(password);

    try {

    // Check if ID already exists
    BufferedReader br = new BufferedReader(new FileReader("data/users.txt"));
    String line;
    while ((line = br.readLine()) != null) {

        String[] data = line.split(",");
// Skip invalid lines
if (data.length < 7) {

    continue;

}

        String storedId = data[2];

        if (storedId.equals(id)) {

            System.out.println( "ID already exists.");

            br.close();

            return;

        }

    }

    br.close();

    FileWriter fw =new FileWriter("data/users.txt",true);

    // Save customer data into file
    fw.write( "customer,"+ name + ","+ id + ","+ phone + ","+ hashedPassword + "," + "false,0\n");

    fw.close();

    System.out.println( "Customer Registered Successfully" );

    SystemLogger.writeToLog("New customer registered: " + id );

}

    

    // Handle file access problems
    catch (SecurityException e) {

        System.out.println("Access denied." );

        SystemLogger.writeToLog("Security error during registration", e);

    }

    // Handle general file writing problems
    catch (IOException e) {

        System.out.println( "Registration Error." );

        SystemLogger.writeToLog(  "Customer registration error",  e );

    }

}

//for testing i did this 
public static boolean validateLogin(
        String enteredId,
        String enteredPassword,
        String storedId,
        String storedPasswordHash
) {

    String hashedPassword =
            PasswordHasher.hashPassword(
                    enteredPassword
            );

    return enteredId.equals(storedId)
            &&
            hashedPassword.equals(storedPasswordHash);

}







}