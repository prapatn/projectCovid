package net.csmsu.springprojectcovidapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.csmsu.springprojectcovidapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
	
	public List<Student> findBySname(String sname);

}
