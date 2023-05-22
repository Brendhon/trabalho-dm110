package br.inatel.dm110.api.purchase;

public class PurchaseTO {
    private String invoiceCode;
    private String order;
    private String cpf;
    private String dateTime;
    private double value;

    public PurchaseTO() {}
    
    public PurchaseTO(String invoiceCode, String order, String cpf, String dateTime, double value) {
        this.invoiceCode = invoiceCode;
        this.order = order;
        this.cpf = cpf;
        this.dateTime = dateTime;
        this.value = value;
    }

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
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return String representation of PurchaseTO
     */
    @Override
    public String toString() {
        return "Invoice Code: " + this.invoiceCode + "\n" +
               "Order: " + this.order + "\n" +
               "CPF: " + this.cpf + "\n" +
               "Date Time: " + this.dateTime + "\n" +
               "Value: " + this.value;
    }
}
