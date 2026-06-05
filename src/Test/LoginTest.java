package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import services.AuthService;
import security.PasswordHasher;

public class LoginTest {

    @Test
    public void testValidLogin() {

        String hash =
                PasswordHasher.hashPassword(
                        "Rama@123"
                );

        assertTrue(
                AuthService.validateLogin(
                        "123",
                        "Rama@123",
                        "123",
                        hash
                )
        );

    }

    @Test
    public void testWrongPassword() {

        String hash =
                PasswordHasher.hashPassword(
                        "Rama@123"
                );

        assertFalse(
                AuthService.validateLogin(
                        "123",
                        "Wrong123",
                        "123",
                        hash
                )
        );

    }

    @Test
    public void testWrongId() {

        String hash =
                PasswordHasher.hashPassword(
                        "Rama@123"
                );

        assertFalse(
                AuthService.validateLogin(
                        "999",
                        "Rama@123",
                        "123",
                        hash
                )
        );

    }

    @Test
    public void testWrongIdAndPassword() {

        String hash =
                PasswordHasher.hashPassword(
                        "Rama@123"
                );

        assertFalse(
                AuthService.validateLogin(
                        "999",
                        "Wrong123",
                        "123",
                        hash
                )
        );

    }

}