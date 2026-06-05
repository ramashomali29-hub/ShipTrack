package models;

public class Dispatcher extends User {

    public Dispatcher(String name,
                      String id,
                      String phone,
                      String passwordHash,
                      boolean locked,
                      int failedAttempts) {

        super(name,
              id,
              phone,
              passwordHash,
              locked,
              failedAttempts);

    }

}