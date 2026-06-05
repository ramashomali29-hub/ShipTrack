package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import security.SystemLogger;

public class ProfileService {
    // Method for viewing personal information
public static void viewPersonalInfo() {

    try {

        BufferedReader br =new BufferedReader(new FileReader("data/users.txt"));

        String line;

        boolean found = false;

        // Read file line by line
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            String storedId = data[2];

            // Check logged-in user ID
            if (storedId.equals(AuthService.currentUserId)) {

                found = true;

                System.out.println( "\n===== Personal Information =====" );

                System.out.println( "Role: " + data[0] );

                System.out.println("Name: " + data[1]);

                System.out.println("ID: " + data[2] );

                System.out.println("Phone: " + data[3]);

                SystemLogger.writeToLog( "Viewed personal info for ID: "   + AuthService.currentUserId);

                break;

            }

        }

        br.close();

        // If user is not found
        if (!found) {

            System.out.println( "User not found." );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println("Users file not found.");

        SystemLogger.writeToLog(  "Users file missing", e);

    }

    catch (IOException e) {

        System.out.println("Error reading user data.");

        SystemLogger.writeToLog( "View personal info error", e);

    }

}


// Method for updating personal information
public static void updatePersonalInfo() {

    Scanner input = new Scanner(System.in);

   System.out.println("\n===== Update Personal Info =====");

System.out.println("1. Update Name");
System.out.println("2. Update Phone");
System.out.println("3. Update Both");

System.out.print("Enter Choice: ");

int choice =
        input.nextInt();

input.nextLine();

String newName = "";

String newPhone = "";

// Update based on user choice
switch (choice) {

    case 1:

        System.out.print("Enter New Name: ");

        newName = input.nextLine();

        break;

    case 2:

        System.out.print("Enter New Phone: ");

        newPhone = input.nextLine();

        break;

    case 3:

        System.out.print("Enter New Name: ");

        newName = input.nextLine();

        System.out.print("Enter New Phone: ");

        newPhone = input.nextLine();

        break;

    default:

        System.out.println( "Invalid Choice.");

        return;

}

    try {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                "data/users.txt"
                        )
                );

        StringBuilder updatedFile =
                new StringBuilder();

        String line;

        boolean updated = false;

        // Read file line by line
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            String storedId = data[2];

            // Check logged-in user
            if (storedId.equals(AuthService.currentUserId)) {

                // Update name if entered
if (!newName.isEmpty()) {

    data[1] = newName;

}

// Update phone if entered
if (!newPhone.isEmpty()) {

    data[3] = newPhone;

}

                updated = true;

            }

            // Rebuild updated file
            updatedFile.append(String.join(",", data) ).append("\n");

        }

        br.close();

        // Rewrite users file
        FileWriter fw =new FileWriter(  "data/users.txt" );

        fw.write( updatedFile.toString());

        fw.close();

        if (updated) {
            System.out.println("Personal information updated successfully.");
            SystemLogger.writeToLog( "Updated personal info for ID: "  + AuthService.currentUserId);
        }

        else {

            System.out.println( "User not found.");

        }

    }

    catch (FileNotFoundException e) {
        System.out.println("Users file not found.");
        SystemLogger.writeToLog("Users file missing",e);

    }

    catch (IOException e) {
        System.out.println( "Error updating personal information.");
        SystemLogger.writeToLog(  "Update personal info error", e );
    }

}


}
