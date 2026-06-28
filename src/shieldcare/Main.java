package shieldcare;

import shieldcare.boundary.XMLImportScreen;
import shieldcare.boundary.ClaimHandlingScreen;
import shieldcare.boundary.ReimbursementScreen;
import shieldcare.database.DBConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("ShieldCare ECB Java Implementation");
        System.out.println("----------------------------------");

        DBConnection.testConnection();

        XMLImportScreen xmlScreen = new XMLImportScreen();
        xmlScreen.displayImportScreen();

        ClaimHandlingScreen claimScreen = new ClaimHandlingScreen();
        claimScreen.displayClaimScreen();

        ReimbursementScreen reimbursementScreen = new ReimbursementScreen();
        reimbursementScreen.displayReimbursementScreen();

        System.out.println("\nPress Enter to exit...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}