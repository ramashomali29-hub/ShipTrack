package services;

import java.util.Scanner;
import java.util.InputMismatchException;
public class PasswordPolicyService {

    // Security policy settings
    static int minLength = 8;
    static int minUppercase = 1;
    static int minLowercase = 1;
    static int minDigits = 1;
    static int minSpecial = 1;

    // Method for checking password strength
    public static boolean isStrongPassword(String password) {

        // Check minimum length
        if (password.length() < minLength) {

            return false;

        }

        int uppercaseCount = 0;
        int lowercaseCount = 0;
        int digitCount = 0;
        int specialCount = 0;

        // Count password characters
        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {

                uppercaseCount++;

            }

            else if (Character.isLowerCase(c)) {

                lowercaseCount++;

            }

            else if (Character.isDigit(c)) {

                digitCount++;

            }

            else {

                specialCount++;

            }

        }

        // Check policy requirements
        return uppercaseCount >= minUppercase
                &&
                lowercaseCount >= minLowercase
                &&
                digitCount >= minDigits
                &&
                specialCount >= minSpecial;

    }

   // Method for updating password policy
public static void updatePasswordPolicy() {

    Scanner input = new Scanner(System.in);

    try {

        System.out.print(
                "Enter Minimum Password Length: "
        );

        minLength =
                input.nextInt();

        System.out.print(
                "Enter Minimum Uppercase Letters: "
        );

        minUppercase =
                input.nextInt();

        System.out.print(
                "Enter Minimum Lowercase Letters: "
        );

        minLowercase =
                input.nextInt();

        System.out.print(
                "Enter Minimum Digits: "
        );

        minDigits =
                input.nextInt();

        System.out.print(
                "Enter Minimum Special Characters: "
        );

        minSpecial =
                input.nextInt();

        input.nextLine();

        System.out.println(
                "Password Policy Updated Successfully"
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