package models;

public class Admin extends User {

    public Admin(String name,
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