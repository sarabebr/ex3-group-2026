# ShieldCare ECB Java Implementation

## Project Description
This project implements the ECB design class diagram for the ShieldCare insurance agency management system.

The implementation focuses on:
1. XML import from external insurer systems
2. Policy synchronization
3. Claim handling
4. Reimbursement management

## Architecture
The project follows the Entity-Control-Boundary architectural pattern.

### Boundary Classes
- XMLImportScreen
- ClaimHandlingScreen
- ReimbursementScreen

### Control Classes
- XMLImportController
- PolicySyncController
- ClaimHandlingController
- ReimbursementController

### Entity Classes
- Client
- Policy
- Claim
- ClaimDocument
- ClaimForm
- Payment
- Reimbursement
- Insurer
- XMLImportLog
- ImportedPolicyRecord
- ImportedClaimRecord
- ThirdPartyInteraction
- DamageReport

## How to Run
Run the executable JAR file:

```bash
java -jar ShieldCareECB.jar