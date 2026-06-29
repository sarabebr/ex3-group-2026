package shieldcare;

import shieldcare.boundary.XMLImportScreen;
import shieldcare.boundary.ClaimHandlingScreen;
import shieldcare.boundary.ReimbursementScreen;
import shieldcare.database.DBConnection;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);

        System.setOut(printStream);
        System.setErr(printStream);

        try {
            System.out.println("ShieldCare ECB Java Implementation");
            System.out.println("----------------------------------");
            System.out.println("Working directory: " + System.getProperty("user.dir"));
            System.out.println();

            DBConnection.testConnection();

            XMLImportScreen xmlScreen = new XMLImportScreen();
            xmlScreen.displayImportScreen();

            ClaimHandlingScreen claimScreen = new ClaimHandlingScreen();
            claimScreen.displayClaimScreen();

            ReimbursementScreen reimbursementScreen = new ReimbursementScreen();
            reimbursementScreen.displayReimbursementScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JTextArea textArea = new JTextArea(output.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(800, 500));

        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "ShieldCare ECB Output",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}