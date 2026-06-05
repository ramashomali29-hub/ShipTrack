package security;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SystemLogger {

    // Create a logger object for the ShipTrack system
    final static Logger LOGGER =
            Logger.getLogger("ShipTrackLog");

    // Static block runs once when the class is loaded
    static {

        try {

            FileHandler fh =
                    new FileHandler("shiptrack.log", true);

            // Prevent duplicate handlers
            if (LOGGER.getHandlers().length == 0) {

                LOGGER.addHandler(fh);

            }

            SimpleFormatter formatter =
                    new SimpleFormatter();

            fh.setFormatter(formatter);

            // Prevent logs from appearing in the console twice
            LOGGER.setUseParentHandlers(false);

        }

        // Handle file input/output errors
        catch (IOException e) {

            System.out.println("Logger has problem");

        }

        // Handle security related logger errors
        catch (SecurityException e) {

            System.out.println("Logger has problem");

        }

    }

    // Method for writing normal INFO logs
    public static void writeToLog(String msg) {

        LOGGER.log(Level.INFO, msg);

    }

    // Method for writing WARNING logs with exceptions
    public static void writeToLog(String msg, Exception e) {

        LOGGER.log(Level.WARNING, msg, e);

    }

}