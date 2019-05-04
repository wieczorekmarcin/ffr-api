package pl.cdv.ffr.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMedia extends BaseEntity {

    private String rate;
    private String used;
    private String unit;
    private String date;
    private String amount;
    private String currency;

    public BaseMedia() {
    }

    public BaseMedia(String rate, String used, String unit, String date, String amount, String currency) {
        this.rate = rate;
        this.used = used;
        this.unit = unit;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
