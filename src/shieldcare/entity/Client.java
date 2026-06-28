package shieldcare.entity;

public class Client {
    private int clientId;
    private String fullName;
    private String idNumber;
    private String phone;
    private String email;

    public Client(int clientId, String fullName, String idNumber, String phone, String email) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.phone = phone;
        this.email = email;
    }

    public int getClientId() {
        return clientId;
    }

    public String getFullName() {
        return fullName;
    }
}