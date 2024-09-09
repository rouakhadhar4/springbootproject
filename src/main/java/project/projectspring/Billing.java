package project.projectspring;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;





import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Billing {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	    private String invoiceId;
	    private String name;
	    private LocalDate date;
	    private String paymentDetails;
	    private double amountDue; // Changement du type de BigDecimal à double

	    public Billing() {
	        this.date = LocalDate.now(); // Date par défaut = Date système lors de la création
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getInvoiceId() {
	        return invoiceId;
	    }

	    public void setInvoiceId(String invoiceId) {
	        this.invoiceId = invoiceId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public String getPaymentDetails() {
	        return paymentDetails;
	    }

	    public void setPaymentDetails(String paymentDetails) {
	        this.paymentDetails = paymentDetails;
	    }

	    public double getAmountDue() {
	        return amountDue;
	    }

	    public void setAmountDue(double amountDue) {
	        this.amountDue = amountDue;
	    }
}