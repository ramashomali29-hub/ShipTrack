
package Test;
import static org.junit.Assert.*;
import org.junit.Test;
import services.PasswordPolicyService;

public class PasswordPolicyServiceTest {

    @Test
    public void testStrongPassword() {

        assertTrue(
                PasswordPolicyService
                        .isStrongPassword("Rama@123")
        );

    }

    @Test
    public void testNoUppercase() {

        assertFalse(
                PasswordPolicyService
                        .isStrongPassword("rama@123")
        );

    }

    @Test
    public void testNoLowercase() {

        assertFalse(
                PasswordPolicyService
                        .isStrongPassword("RAMA@123")
        );

    }

    @Test
    public void testNoDigit() {

        assertFalse(
                PasswordPolicyService
                        .isStrongPassword("Rama@abc")
        );

    }

    @Test
    public void testNoSpecialCharacter() {

        assertFalse(
                PasswordPolicyService
                        .isStrongPassword("Rama1234")
        );

    }

    @Test
    public void testShortPassword() {

        assertFalse(
                PasswordPolicyService
                        .isStrongPassword("Ra@1")
        );

    }

    @Test
    public void testComplexPassword() {

        assertTrue(
                PasswordPolicyService
                        .isStrongPassword("Secure@2025")
        );

    }

}