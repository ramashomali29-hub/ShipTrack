package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import services.AuthService;
import security.SystemLogger;

/*
 * This class handles shipment operations
 * such as creating and tracking shipments.
 */

public class ShipmentService {

    // Method for creating shipment requests
    public static void createShipment() {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Shipment ID: ");
        String shipmentId = input.nextLine();

         // Get customer ID from logged-in user
        String customerId =
        AuthService.currentUserId;

        System.out.print("Enter Source: ");
        String source = input.nextLine();

        System.out.print("Enter Destination: ");
        String destination = input.nextLine();

        // Validate empty input
        if (shipmentId.isEmpty() || customerId.isEmpty() || source.isEmpty() || destination.isEmpty()) {

            throw new IllegalArgumentException( "Shipment fields cannot be empty." );

        }

        try {

            FileWriter fw =
                    new FileWriter("data/shipments.txt", true);

            // Save shipment data into file
            fw.write( shipmentId + "," + customerId + "," + source + ","+ destination + ","+ "Pending,"+ "Not Assigned\n" );

            fw.close();

            System.out.println("Shipment Created Successfully");

            SystemLogger.writeToLog("New shipment created: "+ shipmentId);

        }

        // Handle file permission problems
        catch (SecurityException e) {

            System.out.println( "Access denied." );

            SystemLogger.writeToLog( "Security error during shipment creation",   e );

        }

        // Handle file writing problems
        catch (IOException e) {

            System.out.println("Shipment Creation Error." );

            SystemLogger.writeToLog("Shipment creation error",  e);

        }

    }


    // Method for tracking shipment status
public static void trackShipment() {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Shipment ID: ");

    String shipmentId = input.nextLine();

    try {

        BufferedReader br =new BufferedReader( new FileReader( "data/shipments.txt" ));

        String line;

        boolean found = false;

        // Read shipment file line by line
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 6) {

                continue;

            }

            String storedShipmentId =
                    data[0];

            String status =
                    data[4];

            // Check shipment ID
            if (storedShipmentId.equals(shipmentId)) {

                found = true;

                System.out.println( "Shipment Status: "+ status );

                SystemLogger.writeToLog("Shipment tracked: "  + shipmentId );

                break;

            }

        }

        br.close();

        // Shipment not found
        if (!found) {

            System.out.println("Shipment not found." );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(  "Shipment file not found." );

        SystemLogger.writeToLog( "Shipment file missing",e );

    }

    catch (IOException e) {

        System.out.println( "Error reading shipment data." );

        SystemLogger.writeToLog(  "Shipment tracking error", e);

    }

}



// Method for assigning delivery to driver (for dispatcher)
public static void assignDelivery() {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Shipment ID: ");

    String shipmentId = input.nextLine();

    System.out.print("Enter Delivery Personnel ID: ");

    String driverId = input.nextLine();

    System.out.println("\nSelect Delivery Status:");

    System.out.println("1. Pending");
    System.out.println("2. In Transit");
    System.out.println("3. Delivered");

    System.out.print("Enter Choice: ");

    int statusChoice = input.nextInt();

    input.nextLine();

    String status = "";

    // Select shipment status
    switch (statusChoice) {

        case 1:

            status = "Pending";

            break;

        case 2:

            status = "In Transit";

            break;

        case 3:

            status = "Delivered";

            break;

        default:

            throw new IllegalArgumentException( "Invalid Status Choice." );

    }

    try {

        BufferedReader br = new BufferedReader( new FileReader("data/shipments.txt"));

        StringBuilder updatedFile =new StringBuilder();

        String line;

        boolean updated = false;

        // Read shipment file line by line
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 6) {

                continue;

            }

            // Check shipment ID
            if (data[0].equals(shipmentId)) {

                // Assign driver
                data[5] = driverId;

                // Update shipment status
                data[4] = status;

                updated = true;

            }

            // Rebuild updated file
            updatedFile.append(String.join(",", data)).append("\n");

        }

        br.close();

        // Rewrite shipment file
        FileWriter fw =new FileWriter( "data/shipments.txt");

        fw.write(updatedFile.toString());

        fw.close();

        if (updated) {

            System.out.println(  "Delivery Assigned Successfully" );

            SystemLogger.writeToLog(
                    "Shipment assigned: "
                            + shipmentId
                            + " to driver: "
                            + driverId
                            + " with status: "
                            + status
            );

        }

        else {

            System.out.println( "Shipment not found."  );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println("Shipment file not found.");

        SystemLogger.writeToLog("Shipment file missing",  e);

    }

    catch (IOException e) {

        System.out.println(  "Assignment Error.");

        SystemLogger.writeToLog( "Delivery assignment error",e);

    }

    catch (InputMismatchException e) {

    System.out.println( "Invalid Input Type.");

    SystemLogger.writeToLog(  "Invalid input type during assignment", e);

}

}

// Method for viewing assigned deliveries (for delivary peroneeel)
public static void viewAssignedDeliveries() {

    try {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                "data/shipments.txt"
                        )
                );

        String line;

        boolean found = false;

        System.out.println(
                "\n===== Assigned Deliveries ====="
        );

        // Read shipment file line by line
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 6) {

                continue;

            }

            // Check assigned driver ID
            if (data[5].equals(
                    AuthService.currentUserId
            )) {

                found = true;

                System.out.println(
                        "\nShipment ID: "
                                + data[0]
                );

                System.out.println(
                        "Customer ID: "
                                + data[1]
                );

                System.out.println(
                        "Source: "
                                + data[2]
                );

                System.out.println(
                        "Destination: "
                                + data[3]
                );

                System.out.println(
                        "Status: "
                                + data[4]
                );

            }

        }

        br.close();

        if (!found) {

            System.out.println(
                    "No assigned deliveries found."
            );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(
                "Shipment file not found."
        );

        SystemLogger.writeToLog(
                "Shipment file missing",
                e
        );

    }

    catch (IOException e) {

        System.out.println(
                "Error reading deliveries."
        );

        SystemLogger.writeToLog(
                "Assigned deliveries read error",
                e
        );

    }

}



// Method for updating delivery status (for delivary personeel)
public static void updateDeliveryStatus() {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter Shipment ID: ");

    String shipmentId =
            input.nextLine();

    System.out.println("\nSelect New Status:");

    System.out.println("1. Picked Up");
    System.out.println("2. In Transit");
    System.out.println("3. Delivered");

    System.out.print("Enter Choice: ");

    int choice =
            input.nextInt();

    input.nextLine();

    String newStatus = "";

    // Select new status
    switch (choice) {

        case 1:

            newStatus = "Picked Up";

            break;

        case 2:

            newStatus = "In Transit";

            break;

        case 3:

            newStatus = "Delivered";

            break;

        default:

            throw new IllegalArgumentException(
                    "Invalid Status Choice."
            );

    }

    try {

        BufferedReader br =
                new BufferedReader(
                        new FileReader(
                                "data/shipments.txt"
                        )
                );

        StringBuilder updatedFile =
                new StringBuilder();

        String line;

        boolean updated = false;

        // Read shipment file
        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            // Skip invalid lines
            if (data.length < 6) {

                continue;

            }

            // Check shipment ID
            if (data[0].equals(shipmentId)) {

                // Update shipment status
                data[4] = newStatus;

                updated = true;

            }

            // Rebuild updated file
            updatedFile.append(
                    String.join(",", data)
            ).append("\n");

        }

        br.close();

        // Rewrite shipment file
        FileWriter fw =
                new FileWriter(
                        "data/shipments.txt"
                );

        fw.write(
                updatedFile.toString()
        );

        fw.close();

        if (updated) {

            System.out.println(
                    "Delivery Status Updated Successfully"
            );

            SystemLogger.writeToLog(
                    "Shipment status updated: "
                            + shipmentId
                            + " -> "
                            + newStatus
            );

        }

        else {

            System.out.println(
                    "Shipment not found."
            );

        }

    }

    catch (FileNotFoundException e) {

        System.out.println(
                "Shipment file not found."
        );

        SystemLogger.writeToLog(
                "Shipment file missing",
                e
        );

    }

    catch (InputMismatchException e) {

        System.out.println(
                "Invalid Input Type."
        );

        SystemLogger.writeToLog(
                "Invalid status input",
                e
        );

    }

    catch (IOException e) {

        System.out.println(
                "Status Update Error."
        );

        SystemLogger.writeToLog(
                "Delivery status update error",
                e
        );

    }

}

//for testing 
public static boolean validateShipmentData(
        String shipmentId,
        String customerId,
        String source,
        String destination
) {

    if (shipmentId.isEmpty()
            || customerId.isEmpty()
            || source.isEmpty()
            || destination.isEmpty()) {

        return false;

    }

    return true;

}
}