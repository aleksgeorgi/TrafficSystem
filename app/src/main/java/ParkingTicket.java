import java.util.Date;

public class ParkingTicket extends Ticket {
    // attributes
    private double timeOverLimit; // calculated by custom method
    private double baseFine = 20;

    public ParkingTicket(Date dateIssued, String officerName, String violatorLicenseNumber, 
                         int hoursOverLimit, int minutesOverLimit) {
        super(dateIssued, officerName, violatorLicenseNumber);
        try {
            if (hoursOverLimit < 0 || minutesOverLimit < 0) {
                throw new IllegalArgumentException("Hours or minutes over limit can't be negative!");
            }
            this.timeOverLimit = calculateTimeOverLimit(hoursOverLimit, minutesOverLimit);
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating parking ticket: " + e.getMessage());
        }
    }

    // additional methods
    private double calculateTimeOverLimit(int hoursOverLimit, int minutesOverLimit) {
        return hoursOverLimit + (minutesOverLimit / 60.0);
    }

    // challenge requirement methods
    @Override
    public double calculateFine() {
        return baseFine + (timeOverLimit * 10);
    }
}
