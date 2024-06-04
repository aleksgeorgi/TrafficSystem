import java.util.Date;

public abstract class Ticket {
  // attributes
    private static int ticketCounter = 0;
    private String ticketNumber;
    private Date dateIssued;
    private String officerName;
    private String violatorLicenseNumber;
    private double baseFine = 0;

    // constructor
    public Ticket(Date dateIssued, String officerName, String violatorLicenseNumber) {
        try {
            if (officerName == null || violatorLicenseNumber == null) {
            throw new IllegalArgumentException("Officer name, and violator license number cannot be null.");
            }
            this.ticketNumber = generateTicketNumber();
            this.dateIssued = dateIssued;
            this.officerName = officerName;
            this.violatorLicenseNumber = violatorLicenseNumber;
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating ticket: " + e.getMessage());
        }
    }

    // additional methods
    public void updateBaseFine(double baseFine) {
        try {
        if (baseFine < 0) {
            throw new IllegalArgumentException("Base fine can't be negative.");
        }
        this.baseFine = baseFine;
        } catch (IllegalArgumentException e) {
        System.err.println("Error updating base fine: " + e.getMessage());
        }
    }

    private String generateTicketNumber() {
        return String.format("T%05d", ++ticketCounter);
    }

    public String getTicketNumber() {
        return this.ticketNumber;
    }

    // challenge requirement methods
    public void displayDetails() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Date Issued: " + dateIssued);
        System.out.println("Officer Name: " + officerName);
        System.out.println("Violator License Number: " + violatorLicenseNumber);
    }

    public abstract double calculateFine();
} 
