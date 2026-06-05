package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import services.ShipmentService;

public class ShipmentTest {

    @Test
    public void testValidShipment() {

        assertTrue(
                ShipmentService.validateShipmentData(
                        "101",
                        "123",
                        "Amman",
                        "Irbid"
                )
        );

    }

    @Test
    public void testEmptyShipmentId() {

        assertFalse(
                ShipmentService.validateShipmentData(
                        "",
                        "123",
                        "Amman",
                        "Irbid"
                )
        );

    }

    @Test
    public void testEmptySource() {

        assertFalse(
                ShipmentService.validateShipmentData(
                        "101",
                        "123",
                        "",
                        "Irbid"
                )
        );

    }

    @Test
    public void testEmptyDestination() {

        assertFalse(
                ShipmentService.validateShipmentData(
                        "101",
                        "123",
                        "Amman",
                        ""
                )
        );

    }

    @Test
    public void testAllFieldsEmpty() {

        assertFalse(
                ShipmentService.validateShipmentData(
                        "",
                        "",
                        "",
                        ""
                )
        );

    }

}