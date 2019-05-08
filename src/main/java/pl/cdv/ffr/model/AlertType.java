package pl.cdv.ffr.model;

public enum AlertType {
    PAYMENT, DAMAGES, COMPLAINT,EQUIPMENT;

    private String aletType;

    public String getAletType() {
        return aletType;
    }

    public void setAletType(String aletType) {
        this.aletType = aletType;
    }
}
