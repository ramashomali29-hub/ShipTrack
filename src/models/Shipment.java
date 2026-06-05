package models;

public class Shipment {
    private String shipmentId;

    private String customerId;

    private String source;

    private String destination;

    private String status;

    private String assignedDriver;

    public Shipment(String shipmentId,
                String customerId,
                String source,
                String destination,
                String status,
                String assignedDriver) {

    this.shipmentId = shipmentId;
    this.customerId = customerId;
    this.source = source;
    this.destination = destination;
    this.status = status;
    this.assignedDriver = assignedDriver;

}

public String getShipmentId() {
    return shipmentId;
}

public void setShipmentId(String shipmentId) {
    this.shipmentId = shipmentId;
}

public String getCustomerId() {
    return customerId;
}

public void setCustomerId(String customerId) {
    this.customerId = customerId;
}

public String getSource() {
    return source;
}

public void setSource(String source) {
    this.source = source;
}

public String getDestination() {
    return destination;
}

public void setDestination(String destination) {
    this.destination = destination;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

public String getAssignedDriver() {
    return assignedDriver;
}

public void setAssignedDriver(String assignedDriver) {
    this.assignedDriver = assignedDriver;
}


}
