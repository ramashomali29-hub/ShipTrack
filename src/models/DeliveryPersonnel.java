package models;

public class DeliveryPersonnel extends User {

    public DeliveryPersonnel(String name,
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