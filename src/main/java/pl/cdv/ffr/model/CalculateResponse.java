package pl.cdv.ffr.model;

public class CalculateResponse {

    private String amount;
    private String used;

    public CalculateResponse() {
    }

    public CalculateResponse(String amount, String used) {
        this.amount = amount;
        this.used = used;
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
}
