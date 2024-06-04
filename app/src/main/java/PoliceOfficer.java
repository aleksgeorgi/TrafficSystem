import java.util.ArrayList;
import java.util.List;

public class PoliceOfficer {
    // attributes
    private String officerID;
    private String name;
    private String rank;
    private List<Ticket> issuedTickets;

    // constructor
    public PoliceOfficer(String officerID, String name, String rank) {
        try {
            if (officerID == null || officerID.isEmpty() || name == null || 
                name.isEmpty() || rank == null || rank.isEmpty()) { // use both to catch all NAs
                throw new IllegalArgumentException("Officer ID, name, and rank cannot be null or empty.");
            }
            this.officerID = officerID;
            this.name = name;
            this.rank = rank;
            this.issuedTickets = new ArrayList<>();
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating police officer: " + e.getMessage());
        }
    }

    // additional methods
    public void displayOfficerDetails() {
        System.out.println("Officer ID: " + officerID);
        System.out.println("Name: " + name);
        System.out.println("Rank: " + rank);
    }

    public String getName() {
        return name;
    }

    // challenge requirement methods
    public void issueTicket(Ticket ticket) {
        try {
            if (ticket == null) {
                throw new IllegalArgumentException("Ticket cannot be null."); 
            }
            issuedTickets.add(ticket);
            System.out.println("Ticket number " + ticket.getTicketNumber() + 
                               " issued by Officer " + name);
        } catch (IllegalArgumentException e) {
            System.err.println("Error issuing ticket: " + e.getMessage());
        }
    }

    public void viewIssuedTickets() {
        try {
            if (issuedTickets.isEmpty()) {
                System.out.println("No tickets issued by Officer " + name);
                return;
            }
            issuedTickets.stream().forEach(ticket -> {
                ticket.displayDetails();
                System.out.println("Fine: $" + ticket.calculateFine());
                System.out.println();
            });
        } catch (Exception e) {
            System.err.println("Error viewing issued tickets: " + e.getMessage());
        }
    }
}
