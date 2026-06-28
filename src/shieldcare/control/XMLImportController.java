package shieldcare.control;

import shieldcare.entity.ImportedPolicyRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLImportController {

    public List<ImportedPolicyRecord> importXML(String filePath) {
        List<ImportedPolicyRecord> policies = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList policyNodes = document.getElementsByTagName("policy");

            for (int i = 0; i < policyNodes.getLength(); i++) {
                Node node = policyNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element policyElement = (Element) node;

                    String policyNumber = getText(policyElement, "policyNumber");
                    String insuranceType = getText(policyElement, "insuranceType");
                    String status = getText(policyElement, "status");
                    double amount = Double.parseDouble(getText(policyElement, "amount"));

                    ImportedPolicyRecord record =
                            new ImportedPolicyRecord(policyNumber, insuranceType, status, amount);

                    policies.add(record);
                }
            }

            System.out.println("XML import completed successfully.");
            System.out.println("Imported records: " + policies.size());

        } catch (Exception e) {
            System.out.println("XML import failed: " + e.getMessage());
        }

        return policies;
    }

    public boolean validateXML(String filePath) {
        File file = new File(filePath);
        return file.exists() && filePath.endsWith(".xml");
    }

    public void saveImportLog(String fileName, int recordsCount, String status) {
        System.out.println("Import log saved:");
        System.out.println("File: " + fileName);
        System.out.println("Records: " + recordsCount);
        System.out.println("Status: " + status);
    }

    private String getText(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}