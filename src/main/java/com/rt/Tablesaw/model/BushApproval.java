package com.rt.Tablesaw.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bush_approval")
@Getter @Setter @NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BushApproval implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Long id;
	
	@DateTimeFormat(pattern = "yy-MM-dd")
	@Column(name="DATE")
	private Date date;
	
	@Column(name="APPROVAL")
	private int approval;
	
	@Column(name="WHO")
	private String who;
}
