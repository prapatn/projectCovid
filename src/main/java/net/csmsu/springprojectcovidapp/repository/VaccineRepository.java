package net.csmsu.springprojectcovidapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.csmsu.springprojectcovidapp.entity.VaccineRegi;

public interface VaccineRepository extends JpaRepository<VaccineRegi, Integer> {
//	@Query("select v from VaccineRegi v")
//	List<VaccineRegi> findAll();
}
