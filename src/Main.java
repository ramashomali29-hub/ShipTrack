import java.util.Scanner;

import services.AdminService;
import services.AuthService;
import services.PasswordPolicyService;
import services.ProfileService;
import services.ShipmentService;


public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== ShipTrack System =====");

            System.out.println("1. Register Customer");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");

            choice = input.nextInt();

            input.nextLine();

            switch (choice) {

                case 1:

                    AuthService.registerCustomer();

                    break;

                case 2:

    AuthService.login();

    // ================= CUSTOMER MENU =================
    if (AuthService.currentUserId != null
            &&
            AuthService.currentUserRole.equalsIgnoreCase("customer")) {

        int customerChoice;

        do {

            System.out.println("\n===== Customer Menu =====");

            System.out.println("1. Create Shipment");
            System.out.println("2. Track Shipment");
            System.out.println("3. View Personal Info");
            System.out.println("4. Update Personal Info");
            System.out.println("5. Logout");

            System.out.print("Enter Choice: ");

            customerChoice =
                    input.nextInt();

            input.nextLine();

            switch (customerChoice) {

                case 1:

                    ShipmentService.createShipment();

                    break;

                case 2:

                    ShipmentService.trackShipment();

                    break;

                case 3:

                    ProfileService.viewPersonalInfo();

                    break;

                case 4:

                    ProfileService.updatePersonalInfo();

                    break;

                case 5:

                    System.out.println(
                            "Logging out..."
                    );

                    // Clear logged-in session
                    AuthService.currentUserId = null;

                    AuthService.currentUserRole = null;

                    break;

                default:

                    System.out.println(
                            "Invalid Choice."
                    );

            }

        }

        while (customerChoice != 5);

    }

    // ================= ADMIN MENU =================
    if (AuthService.currentUserId != null
            &&
            AuthService.currentUserRole.equalsIgnoreCase("admin")) {

        int adminChoice;

        do {

            System.out.println("\n===== Admin Menu =====");

            System.out.println("1. Register Dispatcher");
            System.out.println("2. Register Delivery Personnel");
            System.out.println("3. Remove Staff");
            System.out.println("4. Lock User Account");
            System.out.println("5. Unlock User Account");
            System.out.println("6. Update Password Policy");
            System.out.println("7. Update Max Login Attempts");
            System.out.println("8. Logout");

            System.out.print("Enter Choice: ");

            adminChoice =
                    input.nextInt();

            input.nextLine();

            switch (adminChoice) {

                case 1:

                    AdminService.registerDispatcher();

                    break;

                case 2:

                    AdminService.registerDeliveryPersonnel();

                    break;

                    case 3:

    AdminService.removeStaff();

    break;

    case 4:

    AdminService.lockUserAccount();

    break;

case 5:

    AdminService.unlockUserAccount();

    break;
case 6:

    PasswordPolicyService.updatePasswordPolicy();

    break;

    case 7:

    AdminService.updateMaxLoginAttempts();

    break;
                case 8:

                    System.out.println(
                            "Logging out..."
                    );

                    // Clear logged-in session
                    AuthService.currentUserId = null;

                    AuthService.currentUserRole = null;

                    break;

                default:

                    System.out.println(
                            "Invalid Choice."
                    );

            }

        }

        while (adminChoice != 8);

    }
// ================= DISPATCHER MENU =================
if (AuthService.currentUserId != null
        &&
        AuthService.currentUserRole.equalsIgnoreCase("dispatcher")) {

    int dispatcherChoice;

    do {

        System.out.println("\n===== Dispatcher Menu =====");

        System.out.println("1. Assign Delivery");
        System.out.println("2. View Personal Info");
        System.out.println("3. Update Personal Info");
        System.out.println("4. Logout");

        System.out.print("Enter Choice: ");

        dispatcherChoice =
                input.nextInt();

        input.nextLine();

        switch (dispatcherChoice) {

            case 1:

                ShipmentService.assignDelivery();

                break;

            case 2:

                ProfileService.viewPersonalInfo();

                break;

            case 3:

                ProfileService.updatePersonalInfo();

                break;

            case 4:

                System.out.println(
                        "Logging out..."
                );

                // Clear logged-in session
                AuthService.currentUserId = null;

                AuthService.currentUserRole = null;

                break;

            default:

                System.out.println(
                        "Invalid Choice."
                );

        }

    }

    while (dispatcherChoice != 4);

}

// ================= DELIVERY PERSONNEL MENU =================
if (AuthService.currentUserId != null
        &&
        AuthService.currentUserRole.equalsIgnoreCase("delivery")) {

    int deliveryChoice;

    do {

        System.out.println(
                "\n===== Delivery Personnel Menu ====="
        );

        System.out.println(
                "1. View Assigned Deliveries"
        );

        System.out.println(
                "2. Update Delivery Status"
        );

        System.out.println(
                "3. Logout"
        );

        System.out.print(
                "Enter Choice: "
        );

        deliveryChoice =
                input.nextInt();

        input.nextLine();

        switch (deliveryChoice) {

            case 1:

                ShipmentService.viewAssignedDeliveries();

                break;

            case 2:

                ShipmentService.updateDeliveryStatus();

                break;

            case 3:

                System.out.println(
                        "Logging out..."
                );

                // Clear logged-in session
                AuthService.currentUserId = null;

                AuthService.currentUserRole = null;

                break;

            default:

                System.out.println(
                        "Invalid Choice."
                );

        }

    }

    while (deliveryChoice != 3);

}
    break;

            }

                            
        }

        while (choice != 3);

input.close();
       
    }

}
                               


       
