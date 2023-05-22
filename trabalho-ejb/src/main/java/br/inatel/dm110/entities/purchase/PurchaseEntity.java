package br.inatel.dm110.entities.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "PURCHASE_ENTITY", schema = "public")
public class PurchaseEntity {
    @Id
    @Column(name = "INVOICE_CODE")
    private String invoiceCode;

    @Column(name = "ORDER_ITEM")
    private String order;

    @Column(name = "CPF")
    private String CPF;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @Column(name = "VALUE")
    private double value;

    public PurchaseEntity() {}

    public PurchaseEntity(String invoiceCode, String order, String CPF, LocalDateTime dateTime, double value) {
        this.invoiceCode = invoiceCode;
        this.order = order;
        this.CPF = CPF;
        this.dateTime = dateTime;
        this.value = value;
    }

    // Getters and setters
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

