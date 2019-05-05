package pl.cdv.ffr.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMedia extends BaseEntity {

    private String status;
    private String rate;
    private String used;
    private String date;
    private String amount;
    private String unit;
    private final String currency = "PLN";

    public BaseMedia() {
    }


    public BaseMedia(String status, String rate, String used, String date, String amount, String unit) {
        this.status = status;
        this.rate = rate;
        this.used = used;
        this.date = date;
        this.amount = amount;
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCurrency() {
        return currency;
    }
}
