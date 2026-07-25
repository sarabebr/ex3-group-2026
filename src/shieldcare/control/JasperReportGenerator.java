package shieldcare.control;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import shieldcare.entity.PolicyRenewal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JasperReportGenerator {

    private static final String REPORT_PATH =
            "src/reports/PolicyRenewalReport.jrxml";

    public void displayPolicyRenewalReport(
            PolicyRenewal renewal) throws JRException {

        if (renewal == null) {
            throw new IllegalArgumentException(
                    "Renewal data cannot be null."
            );
        }

        JasperReport jasperReport =
                JasperCompileManager.compileReport(
                        REPORT_PATH
                );

        Map<String, Object> parameters =
                createReportParameters();

        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(
                        Collections.singletonList(renewal)
                );

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        dataSource
                );

        JasperViewer.viewReport(
                jasperPrint,
                false
        );
    }

    private Map<String, Object> createReportParameters() {

        Map<String, Object> parameters =
                new HashMap<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm:ss"
                );

        parameters.put(
                "generationDate",
                LocalDateTime.now().format(formatter)
        );

        parameters.put(
                "reportTitle",
                "ShieldCare Policy Renewal Report"
        );

        return parameters;
    }
}