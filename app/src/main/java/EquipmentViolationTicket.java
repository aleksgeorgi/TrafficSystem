import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EquipmentViolationTicket extends Ticket {
    // attributes
    private String equipmentSize;
    private double baseFine = 60;
    Map<String, Double> fineMultiplier = new HashMap<>();

    // constructor
    public EquipmentViolationTicket(Date dateIssued, String officerName, 
                                    String violatorLicenseNumber, String equipmentSize) {
        super(dateIssued, officerName, violatorLicenseNumber);
        try {
            if (equipmentSize == null || equipmentSize.isEmpty()) {
                throw new IllegalArgumentException("Equipment size can't be null!");
            }
            this.equipmentSize = equipmentSize;
            initializeFineMultiplier();
            if (!fineMultiplier.containsKey(this.equipmentSize.toLowerCase())) {
                throw new IllegalArgumentException("Invalid equipment size: " + 
                                                   equipmentSize +
                                                   ". Must be small, medium, or large.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating equipment violation ticket: " + e.getMessage());
        }
    }

    // additional methods
    private void initializeFineMultiplier() {
        fineMultiplier.put("small", 0.5);
        fineMultiplier.put("medium", 0.75);
        fineMultiplier.put("large", 1.25);
    }

    public void updateFineMultiplier(String equipmentSize, double multiplier) {
        try {
            if (multiplier < 0) {
                throw new IllegalArgumentException("Multiplier can't be negative");
            }
            this.fineMultiplier.put(equipmentSize, multiplier);
        } catch (IllegalArgumentException e) {
            System.err.println("Error updating fine multiplier: " + e.getMessage());
        }
    }

    public void removeFineMultiplier(String equipmentSize) {
        try {
            if (!fineMultiplier.containsKey(equipmentSize)) {
                throw new IllegalArgumentException("That equipment size does not exist: " + 
                                                   equipmentSize);
            }
            this.fineMultiplier.remove(equipmentSize);
        } catch (IllegalArgumentException e) {
            System.err.println("Error removing fine multiplier: " + e.getMessage());
        }
    }

    // challenge requirement methods
    @Override
    public double calculateFine() {
        try {
            Double multiplier = fineMultiplier.get(this.equipmentSize.toLowerCase());
            if (multiplier != null) {
                return baseFine + (baseFine * multiplier);
            } else {
                throw new IllegalArgumentException(
                    "Invalid equipment size: " + equipmentSize +
                    " (must be small, medium, or large!)");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error calculating fine: " + e.getMessage());
            return baseFine; // Default to base fine if an error occurs
        }
    }
}
