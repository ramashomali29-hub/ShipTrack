package models;

public class Customer extends User {

    public Customer(String name,
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
              failedAttempts);//super for call constructor for user class

    }

}