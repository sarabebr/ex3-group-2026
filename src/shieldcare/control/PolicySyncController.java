package shieldcare.control;

import shieldcare.entity.ImportedPolicyRecord;

import java.util.List;

public class PolicySyncController {

    public void syncPolicies(List<ImportedPolicyRecord> importedPolicies) {
        System.out.println("Starting policy synchronization...");

        for (ImportedPolicyRecord record : importedPolicies) {
            if (policyExists(record.getPolicyNumber())) {
                updatePolicy(record);
            } else {
                createPolicy(record);
            }
        }

        createSyncLog(importedPolicies.size());
    }

    private boolean policyExists(String policyNumber) {
        return policyNumber.endsWith("1") || policyNumber.endsWith("5");
    }

    private void createPolicy(ImportedPolicyRecord record) {
        System.out.println("Creating new policy: " + record.getPolicyNumber());
    }

    private void updatePolicy(ImportedPolicyRecord record) {
        System.out.println("Updating existing policy: " + record.getPolicyNumber());
    }

    private void createSyncLog(int count) {
        System.out.println("Synchronization log created. Records processed: " + count);
    }
}