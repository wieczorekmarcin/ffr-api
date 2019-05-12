package pl.cdv.ffr.model;

public class CalculateResponse {

    private String amount;
    private String used;
    private String rate;

    public CalculateResponse() {
    }

    public CalculateResponse(String amount, String used, String rate) {
        this.amount = amount;
        this.used = used;
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
