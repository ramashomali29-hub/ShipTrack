package models;

public class User {

    protected String name;
    protected String id;
    protected String phone;
    protected String passwordHash;
    protected boolean locked;
    protected int failedAttempts;

    public User(String name,
                String id,
                String phone,
                String passwordHash,
                boolean locked,
                int failedAttempts) {

        this.name = name;
        this.id = id;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.locked = locked;
        this.failedAttempts = failedAttempts;
        
                }

      public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getId() {
    return id;
}

public void setId(String id) {
    this.id = id;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getPasswordHash() {
    return passwordHash;
}

public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}

public boolean isLocked() {
    return locked;
}

public void setLocked(boolean locked) {
    this.locked = locked;
}

public int getFailedAttempts() {
    return failedAttempts;
}

public void setFailedAttempts(int failedAttempts) {
    this.failedAttempts = failedAttempts;
}

}
