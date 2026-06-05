package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import security.PasswordHasher;
import security.SystemLogger;

public class AdminService {
    // Method for registering dispatcher
public static void registerDispatcher() {

    Scanner input = new Scanner(System.in);
    // Allow only admin to register dispatcher
if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

    throw new IllegalArgumentException(
            "Access Denied."
    );

}

    System.out.print("Enter Dispatcher Name: ");

    String name =
            input.nextLine();

    System.out.print("Enter Dispatcher ID: ");

    String id =
            input.nextLine();

    System.out.print("Enter Dispatcher Phone: ");

    String phone =
            input.nextLine();

    String password;

    // Keep asking until password is strong
    do {

        System.out.print("Enter Password: ");

        password =
                input.nextLine();

        if (!PasswordPolicyService
                .isStrongPassword(password)) {

            System.out.println(
                    "Weak Password. Try Again."
            );

        }

    }

    while (!PasswordPolicyService
            .isStrongPassword(password));

    // Hash password before saving
    String hashedPassword =
            PasswordHasher.hashPassword(
                    password
            );

    try {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                "data/users.txt"
                        )
                );

        String line;

        // Check duplicate ID
        while ((line = br.readLine()) != null) {

            String[] data =
                    line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            if (data[2].equals(id)) {

                System.out.println(
                        "ID already exists."
                );

                br.close();

                return;

            }

        }

        br.close();

        FileWriter fw =
                new FileWriter(
                        "data/users.txt",
                        true
                );

        // Save dispatcher data
        fw.write(
                "dispatcher,"
                        + name + ","
                        + id + ","
                        + phone + ","
                        + hashedPassword + ","
                        + "false,0\n"
        );

        fw.close();

        System.out.println(
                "Dispatcher Registered Successfully"
        );

        SystemLogger.writeToLog(
                "New dispatcher registered: "
                        + id
        );

    }

    catch (IOException e) {

        System.out.println(
                "Registration Error."
        );

        SystemLogger.writeToLog(
                "Dispatcher registration error",
                e
        );

    }

    

}

// Method for registering delivery personnel
public static void registerDeliveryPersonnel() {

    Scanner input = new Scanner(System.in);

    // Allow only admin to register delivery personnel
    if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        throw new IllegalArgumentException(
                "Access Denied."
        );

    }

    System.out.print("Enter Delivery Personnel Name: ");

    String name =
            input.nextLine();

    System.out.print("Enter Delivery Personnel ID: ");

    String id =
            input.nextLine();

    System.out.print("Enter Delivery Personnel Phone: ");

    String phone =
            input.nextLine();

    String password;

    // Keep asking until password is strong
    do {

        System.out.print("Enter Password: ");

        password =
                input.nextLine();

        if (!PasswordPolicyService
                .isStrongPassword(password)) {

            System.out.println(
                    "Weak Password. Try Again."
            );

        }

    }

    while (!PasswordPolicyService
            .isStrongPassword(password));

    // Hash password before saving
    String hashedPassword =
            PasswordHasher.hashPassword(
                    password
            );

    try {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                "data/users.txt"
                        )
                );

        String line;

        // Check duplicate ID
        while ((line = br.readLine()) != null) {

            String[] data =
                    line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            if (data[2].equals(id)) {

                System.out.println(
                        "ID already exists."
                );

                br.close();

                return;

            }

        }

        br.close();

        FileWriter fw =
                new FileWriter(
                        "data/users.txt",
                        true
                );

        // Save delivery personnel data
        fw.write(
                "delivery,"
                        + name + ","
                        + id + ","
                        + phone + ","
                        + hashedPassword + ","
                        + "false,0\n"
        );

        fw.close();

        System.out.println(
                "Delivery Personnel Registered Successfully"
        );

        SystemLogger.writeToLog(
                "New delivery personnel registered: "
                        + id
        );

    }

    catch (FileNotFoundException e) {

        System.out.println(
                "Users file not found."
        );

        SystemLogger.writeToLog(
                "Users file missing",
                e
        );

    }

    catch (IOException e) {

        System.out.println(
                "Registration Error."
        );

        SystemLogger.writeToLog(
                "Delivery personnel registration error",
                e
        );

    }

}

// Method for removing dispatcher or delivery personnel
public static void removeStaff() {

    Scanner input = new Scanner(System.in);

    // Allow only admin
    if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        throw new IllegalArgumentException("Access Denied." );

    }

    System.out.print("Enter Staff ID to Remove: ");

    String enteredId =   input.nextLine();

    try {

        BufferedReader br = new BufferedReader(  new FileReader( "data/users.txt" ));

        StringBuilder updatedFile = new StringBuilder();

        String line;

        boolean removed = false;

        // Read users file line by line
        while ((line = br.readLine()) != null) {

            String[] data =line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            String role = data[0];

            String storedId =data[2];

            // Remove only dispatcher or delivery personnel
            if (storedId.equals(enteredId)
                    &&
                    (
                    role.equalsIgnoreCase("dispatcher")
                    ||
                    role.equalsIgnoreCase("delivery")
                    )) {

                removed = true;

                continue;

            }

            // Keep remaining users
            updatedFile.append(String.join(",", data)).append("\n");

        }

        br.close();

        // Rewrite updated file
        FileWriter fw = new FileWriter( "data/users.txt" );

        fw.write( updatedFile.toString() );

        fw.close();

        if (removed) {

            System.out.println( "Staff Removed Successfully");

            SystemLogger.writeToLog("Staff removed: "  + enteredId);

        }

        else {

            System.out.println("Staff not found." );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(  "Users file not found.");

        SystemLogger.writeToLog( "Users file missing", e);

    }

    catch (NullPointerException e) {

        System.out.println("Null value detected." );

        SystemLogger.writeToLog( "Null pointer error during remove staff", e);

    }

    catch (ArrayIndexOutOfBoundsException e) {

        System.out.println( "Corrupted user data.");

        SystemLogger.writeToLog("User data format error",e);

    }

    catch (IOException e) {

        System.out.println( "Remove Staff Error." );

        SystemLogger.writeToLog( "Remove staff error",e);

    }

}

// Method for locking user account
public static void lockUserAccount() {

    Scanner input = new Scanner(System.in);

    // Allow only admin
    if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        throw new IllegalArgumentException(
                "Access Denied."
        );

    }

    System.out.print("Enter User ID to Lock: ");

    String enteredId =
            input.nextLine();

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

        boolean locked = false;

        // Read users file
        while ((line = br.readLine()) != null) {

            String[] data =
                    line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            // Check matching user
            if (data[2].equals(enteredId)) {

                // Lock account
                data[5] = "true";

                locked = true;

            }

            // Rebuild updated file
            updatedFile.append(
                    String.join(",", data)
            ).append("\n");

        }

        br.close();

        // Rewrite users file
        FileWriter fw =
                new FileWriter(
                        "data/users.txt"
                );

        fw.write(
                updatedFile.toString()
        );

        fw.close();

        if (locked) {

            System.out.println(
                    "Account Locked Successfully"
            );

            SystemLogger.writeToLog(
                    "Account locked: "
                            + enteredId
            );

        }

        else {

            System.out.println(
                    "User not found."
            );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(
                "Users file not found."
        );

        SystemLogger.writeToLog(
                "Users file missing",
                e
        );

    }

    catch (IOException e) {

        System.out.println(
                "Lock account error."
        );

        SystemLogger.writeToLog(
                "Account lock error",
                e
        );

    }

}

// Method for unlocking user account
public static void unlockUserAccount() {

    Scanner input = new Scanner(System.in);

    // Allow only admin
    if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        throw new IllegalArgumentException(
                "Access Denied."
        );

    }

    System.out.print("Enter User ID to Unlock: ");

    String enteredId =
            input.nextLine();

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

        boolean unlocked = false;

        // Read users file
        while ((line = br.readLine()) != null) {

            String[] data =
                    line.split(",");

            // Skip invalid lines
            if (data.length < 7) {

                continue;

            }

            // Check matching user
            if (data[2].equals(enteredId)) {

                // Unlock account
                data[5] = "false";

                unlocked = true;

            }

            // Rebuild updated file
            updatedFile.append(
                    String.join(",", data)
            ).append("\n");

        }

        br.close();

        // Rewrite users file
        FileWriter fw =
                new FileWriter(
                        "data/users.txt"
                );

        fw.write(
                updatedFile.toString()
        );

        fw.close();

        if (unlocked) {

            System.out.println(
                    "Account Unlocked Successfully"
            );

            SystemLogger.writeToLog(
                    "Account unlocked: "
                            + enteredId
            );

        }

        else {

            System.out.println(
                    "User not found."
            );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(
                "Users file not found."
        );

        SystemLogger.writeToLog(
                "Users file missing",
                e
        );

    }

    catch (IOException e) {

        System.out.println(
                "Unlock account error."
        );

        SystemLogger.writeToLog(
                "Account unlock error",
                e
        );

    }

}

// Maximum allowed login attempts
public static int maxLoginAttempts = 3;
// Method for updating maximum login attempts
public static void updateMaxLoginAttempts() {

    Scanner input = new Scanner(System
        .in);

    // Allow only admin
    if (!AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        throw new IllegalArgumentException(
                "Access Denied."
        );

    }

    try {

        System.out.print(
                "Enter Maximum Login Attempts: "
        );

        maxLoginAttempts =
                input.nextInt();

        input.nextLine();

        System.out.println(
                "Maximum Login Attempts Updated Successfully"
        );

    }

    catch (InputMismatchException e) {

        System.out.println(
                "Invalid Input Type."
        );

        input.nextLine();

    }

}
}
