# ShipTrack – Secure Package Delivery Management System

ShipTrack is a Java application that manages package delivery operations, including shipments, delivery personnel, and tracking records for customers and staff. It was built with a security-first approach and tested using unit testing, fuzz testing, and static code analysis.

## User Roles & Features

**System Admin**
- Register and remove dispatchers and delivery personnel
- Define password strength policies (length, uppercase, lowercase, digits, special characters)
- Set the maximum number of login attempts
- Lock and unlock user accounts

**Dispatcher**
- Assign deliveries to drivers
- Update delivery status (Pending, In Transit, Delivered)
- Register new delivery personnel
- View and update personal information

**Delivery Personnel**
- View assigned deliveries
- Update delivery status (Picked Up, In Transit, Delivered)

**Customer**
- Register and log in
- Create shipment requests
- Track package status
- View and update personal information

## Security Features
- Secure login using [hashing algorithm, e.g. SHA-256 with salt / PBKDF2]
- Configurable password strength policy
- Account lockout after a maximum number of failed login attempts
- Role-based access control
- Input validation
- Activity logging
-  Passwords hashed using SHA-256

## Testing & Code Quality
- **Unit Testing:** [JUnit] tests for core features
- **Fuzz Testing:** Jazzer to detect crashes and unexpected behavior from malformed input
- **Static Code Analysis:** PMD for code quality and security review (reports included: `pmd-report.csv`, `pmd-security.csv`)
- **Threat Modeling:** Data flow diagram analyzed using STRIDE, with use case and misuse case diagrams

## Tech Stack
- Java
- Jazzer (fuzz testing)
- PMD (static analysis)
- Git for version control

## How to Run
```
java -jar project.jar
```
