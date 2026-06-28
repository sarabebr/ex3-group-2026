package shieldcare.boundary;

import shieldcare.control.XMLImportController;
import shieldcare.control.PolicySyncController;
import shieldcare.entity.ImportedPolicyRecord;

import java.util.List;

public class XMLImportScreen {

    public void displayImportScreen() {
        System.out.println("\n[Boundary] XML Import Screen");

//        String filePath = "data/insurer_import.xml";
        String filePath = System.getProperty("user.dir") + "\\data\\insurer_import.xml";

        XMLImportController importController = new XMLImportController();
        PolicySyncController syncController = new PolicySyncController();

        if (importController.validateXML(filePath)) {
            List<ImportedPolicyRecord> records = importController.importXML(filePath);
            importController.saveImportLog(filePath, records.size(), "Success");
            syncController.syncPolicies(records);
        } else {
            importController.saveImportLog(filePath, 0, "Failed");
            System.out.println("Invalid XML file.");
        }
    }
}