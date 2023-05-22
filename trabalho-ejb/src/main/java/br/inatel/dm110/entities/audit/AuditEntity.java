package br.inatel.dm110.entities.audit;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUDIT_ENTITY", schema = "public")
public class AuditEntity {

	public AuditEntity() {
		this.creationDate = Timestamp.valueOf(LocalDateTime.now());
	}

	public AuditEntity(String registerCode, String operation) {
		this.registerCode = registerCode;
		this.operation = operation;
		this.creationDate = Timestamp.valueOf(LocalDateTime.now());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "REGISTER_CODE")
	private String registerCode;

	@Column(name = "OPERATION")
	private String operation;

	@Column(name = "CREATION_DATE")
	private Timestamp creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTimestamp() {
		return creationDate.toString();
	}
}
