package framework;

import java.util.Date;

public class Entry {

    private Date date;
    private double amount;
    private String description;
    private String fromAccountId;
    private String fromPersonName;

    public Entry() {}

    public Entry(double amount, String description, String fromAccountId, String fromPersonName) {
        this.date = new Date();
        this.amount = amount;
        this.description = description;
        this.fromAccountId = fromAccountId;
        this.fromPersonName = fromPersonName;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }
}