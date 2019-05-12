package pl.cdv.ffr.model;

public enum InvoiceType {
    PAID, NOT_PAID;

    private String invoiceType;

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
}
