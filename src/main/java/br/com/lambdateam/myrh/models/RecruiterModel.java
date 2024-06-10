package br.com.lambdateam.myrh.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "recruiters")
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date contactDate;
	private String name;
	private String telephone;
	private String email;
	private String company;
	private String contactType;
}