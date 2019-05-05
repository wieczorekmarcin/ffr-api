package pl.cdv.ffr.model;

public enum BillType {
    COLD_WATER, COMMON_PART, ELECTRICITY, HEATING, HOT_WATER, REPAIR_FOUND, TRASH;

    private String billType;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
