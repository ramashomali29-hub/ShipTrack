package Test;
import static org.junit.Assert.*;
import org.junit.Test;
import security.PasswordHasher;

public class PasswordHasherTest {

    @Test
    public void testHashGenerated() {

        String hash =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        assertNotNull(hash);

    }

    @Test
    public void testSamePasswordSameHash() {

        String hash1 =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        String hash2 =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        assertEquals(hash1, hash2);

    }

    @Test
    public void testDifferentPasswordsDifferentHashes() {

        String hash1 =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        String hash2 =
                PasswordHasher.hashPassword(
                        "Admin@124"
                );

        assertNotEquals(hash1, hash2);

    }

    @Test
    public void testHashNotEmpty() {

        String hash =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        assertFalse(hash.isEmpty());

    }

    @Test
    public void testHashLength() {

        String hash =
                PasswordHasher.hashPassword(
                        "Admin@123"
                );

        assertEquals(64, hash.length());

    }

}