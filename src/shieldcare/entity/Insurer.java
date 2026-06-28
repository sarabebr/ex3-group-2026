package shieldcare.entity;

public class Insurer {
    private int insurerId;
    private String name;
    private String phone;
    private String email;

    public Insurer(int insurerId, String name, String phone, String email) {
        this.insurerId = insurerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}