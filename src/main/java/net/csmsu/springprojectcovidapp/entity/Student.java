package net.csmsu.springprojectcovidapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String sid;

	private String major;

	private String sname;

	private String year;

	//bi-directional many-to-one association to VaccineRegi
	@OneToMany(mappedBy="student")
	private List<VaccineRegi> vaccineRegis;

	public Student() {
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<VaccineRegi> getVaccineRegis() {
		return this.vaccineRegis;
	}

	public void setVaccineRegis(List<VaccineRegi> vaccineRegis) {
		this.vaccineRegis = vaccineRegis;
	}

	public VaccineRegi addVaccineRegi(VaccineRegi vaccineRegi) {
		getVaccineRegis().add(vaccineRegi);
		vaccineRegi.setStudent(this);

		return vaccineRegi;
	}

	public VaccineRegi removeVaccineRegi(VaccineRegi vaccineRegi) {
		getVaccineRegis().remove(vaccineRegi);
		vaccineRegi.setStudent(null);

		return vaccineRegi;
	}

}