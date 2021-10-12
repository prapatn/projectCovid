package net.csmsu.springprojectcovidapp.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the type_vaccine database table.
 * 
 */
@Entity
@Table(name="type_vaccine")
@NamedQuery(name="TypeVaccine.findAll", query="SELECT t FROM TypeVaccine t")
public class TypeVaccine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtype;

	private String nametype;

	//bi-directional many-to-one association to VaccineRegi
	@OneToMany(mappedBy="typeVaccine")
	private List<VaccineRegi> vaccineRegis;

	public TypeVaccine() {
	}

	public int getIdtype() {
		return this.idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getNametype() {
		return this.nametype;
	}

	public void setNametype(String nametype) {
		this.nametype = nametype;
	}

	public List<VaccineRegi> getVaccineRegis() {
		return this.vaccineRegis;
	}

	public void setVaccineRegis(List<VaccineRegi> vaccineRegis) {
		this.vaccineRegis = vaccineRegis;
	}

	public VaccineRegi addVaccineRegi(VaccineRegi vaccineRegi) {
		getVaccineRegis().add(vaccineRegi);
		vaccineRegi.setTypeVaccine(this);

		return vaccineRegi;
	}

	public VaccineRegi removeVaccineRegi(VaccineRegi vaccineRegi) {
		getVaccineRegis().remove(vaccineRegi);
		vaccineRegi.setTypeVaccine(null);

		return vaccineRegi;
	}

}