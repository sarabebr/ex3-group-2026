package shieldcare.entity;

import java.time.LocalDateTime;

public class XMLImportLog {
    private int importId;
    private String fileName;
    private LocalDateTime importDate;
    private String status;
    private int recordsCount;

    public XMLImportLog(int importId, String fileName, String status, int recordsCount) {
        this.importId = importId;
        this.fileName = fileName;
        this.importDate = LocalDateTime.now();
        this.status = status;
        this.recordsCount = recordsCount;
    }

    public String getStatus() {
        return status;
    }
}