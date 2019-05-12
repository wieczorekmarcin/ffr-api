package pl.cdv.ffr.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Invoice extends BaseEntity {

    private String invoiceUrl;
    private String date;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    public Invoice() {
    }

    public Invoice(String invoiceUrl, String date, InvoiceType invoiceType) {
        this.invoiceUrl = invoiceUrl;
        this.date = date;
        this.invoiceType = invoiceType;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }
}
