package br.inatel.dm110.entities.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "PURCHASE_ENTITY", schema = "public")
public class PurchaseEntity {
    @Id
    @Column(name = "INVOICE_CODE")
    private String invoiceCode;

    @Column(name = "ORDER_ITEM")
    private String order;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "DATE_TIME")
    private Timestamp dateTime;

    @Column(name = "VALUE")
    private double value;

    public PurchaseEntity() {}

    public PurchaseEntity(String invoiceCode, String order, String cpf, String dateTime, double value) {
        this.invoiceCode = invoiceCode;
        this.order = order;
        this.cpf = cpf;
        this.dateTime = Timestamp.valueOf(dateTime);
        this.value = value;
    }

    // Getters and setters
    public String getInvoiceCode() {
        return this.invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDateTime() {
        return this.dateTime.toString();
    }

    public void setDateTime(String dateTime) {
        this.dateTime = Timestamp.valueOf(dateTime);
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

