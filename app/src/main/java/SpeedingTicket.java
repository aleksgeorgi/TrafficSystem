import java.util.Date;

public class SpeedingTicket extends Ticket {
    // attributes
    private double overSpeedLimit;
    private double baseFine = 40;

    // constructor
    public SpeedingTicket(Date dateIssued, String officerName, 
                            String violatorLicenseNumber, double overSpeedLimit) {
        super(dateIssued, officerName, violatorLicenseNumber);
        try {
        if (overSpeedLimit < 0) {
            throw new IllegalArgumentException("Speed limit can't be negative.");
        }
        this.overSpeedLimit = overSpeedLimit;
        } catch (IllegalArgumentException e) {
        System.err.println("Error creating speeding ticket: " + e.getMessage());
        }
    }

    // challenge requirement methods
    @Override
    public double calculateFine() {
        return baseFine + (overSpeedLimit * 11);
    }
}