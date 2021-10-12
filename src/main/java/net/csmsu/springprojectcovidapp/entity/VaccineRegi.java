package net.csmsu.springprojectcovidapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vaccineRegis database table.
 * 
 */
@Entity
@Table(name="vaccineRegis")
@NamedQuery(name="VaccineRegi.findAll", query="SELECT v FROM VaccineRegi v")
public class VaccineRegi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idv;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int round;

	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="sid")
	private Student student;

	//bi-directional many-to-one association to TypeVaccine
	@ManyToOne
	@JoinColumn(name="idtype")
	private TypeVaccine typeVaccine;

	public VaccineRegi() {
	}

	public int getIdv() {
		return this.idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRound() {
		return this.round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TypeVaccine getTypeVaccine() {
		return this.typeVaccine;
	}

	public void setTypeVaccine(TypeVaccine typeVaccine) {
		this.typeVaccine = typeVaccine;
	}

}