# ShieldCare – Assignment 3

## Policy Renewal Report Generation

This project implements Assignment 3 for the ShieldCare insurance management system.

The implementation extends the existing ShieldCare ECB architecture by adding a Policy Renewal Report generation module using JasperReports.

## Main Features

- Policy Renewal Report generation
- Policy renewal validation
- Renewal amount calculation
- Renewal recommendations
- JasperReports PDF generation
- Microsoft Access database integration

## Architecture

The project follows the Entity–Control–Boundary (ECB) architectural pattern.

### Boundary Classes
- PolicyRenewalReportScreen

### Control Classes
- PolicyRenewalReportController
- PolicyRenewalValidationController
- RenewalAmountController
- PolicySyncController
- JasperReportGenerator

### Entity Classes
- PolicyRenewalReport
- PolicyRenewal
- Policy
- Client
- Employee
- Insurer
- Car

## Technologies

- Java
- Microsoft Access
- UCanAccess
- JasperReports 6.21.3

## Project Structure

```
src/
data/
lib/
reports/
```

 How to Run

Run the executable JAR file:

```bash
java -jar ShieldCareECB.jar
```

## Group

Group#2026

University of Haifa  
Department of Information Systems
