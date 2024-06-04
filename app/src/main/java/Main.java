import java.util.Date;
import java.util.Scanner;

public class Main {

    // helper login function
    public static PoliceOfficer login(String officerName, String officerRank, String officerID) {
        return new PoliceOfficer(officerID, officerName, officerRank);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Read officer details from command line
            System.out.println("Enter Officer Name: ");
            String officerName = scanner.nextLine();

            System.out.println("Enter Officer Rank: ");
            String officerRank = scanner.nextLine();

            System.out.println("Enter Officer ID: ");
            String officerID = scanner.nextLine();

            // login to create a police officer
            PoliceOfficer officer = login(officerName, officerRank, officerID);

            System.out.println("\nOfficer successfully logged in:\n");
            officer.displayOfficerDetails();

            System.out.println("\nViolations observed! Beginning to create tickets... \n");
            Ticket speedingTicket = new SpeedingTicket(new Date(), 
                                                       officer.getName(), 
                                                       "A1234567", 
                                                       15); // 15 mph over speed limit
            Ticket parkingTicket = new ParkingTicket(new Date(), 
                                                     officer.getName(), 
                                                     "B7654321", 
                                                     2, 30); // 2 hrs 30 mins
            Ticket equipmentViolationTicket = new EquipmentViolationTicket(new Date(), 
                                                                           officer.getName(), 
                                                                           "C9876543", 
                                                                           "large");

            System.out.println("Perps apprehended! Beginning to issue tickets... \n");
            officer.issueTicket(speedingTicket);
            officer.issueTicket(parkingTicket);
            officer.issueTicket(equipmentViolationTicket);

            System.out.println("\nSummary of issued Tickets:\n");
            officer.viewIssuedTickets();

        } catch (IllegalArgumentException e) {
            System.err.println("Error issuing ticket: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
