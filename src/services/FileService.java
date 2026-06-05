package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import security.SystemLogger;

public class FileService {

    // Method for reading all users from users.txt file
    public static void readUsersFile() {

        try {

            // Create BufferedReader to read the file line by line
            BufferedReader br =
                    new BufferedReader(
                            new FileReader("data/users.txt")
                    );

            String line;

            // Read each line until the end of the file
            while ((line = br.readLine()) != null) {
                System.out.println(line);

            }
            br.close();

            // Write INFO log if file reading is successful
            SystemLogger.writeToLog(
                    "Users file read successfully"
            );

        }

        // Handle file reading errors
        catch (IOException e) {

            // Write WARNING log if an error occurs
            SystemLogger.writeToLog(
                    "Error reading users file",
                    e
            );

        }

    }

}