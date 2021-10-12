package net.csmsu.springprojectcovidapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.csmsu.springprojectcovidapp.entity.Student;
import net.csmsu.springprojectcovidapp.entity.VaccineRegi;
import net.csmsu.springprojectcovidapp.repository.StudentRepository;
import net.csmsu.springprojectcovidapp.repository.VaccineRepository;

@Service
public class MyService {
	
	@Autowired
	StudentRepository studentRepository ;
	
	@Autowired
	VaccineRepository vaccineRepository;
	
	//Search
	public Optional<Student> getStudentBySid(String str) {
		return studentRepository.findById(str);
	}
	public List<Student> getStudentBySname(String str) {
		return studentRepository.findBySname(str);
	}
	
	public List<Student> getAllStudent(){
		return studentRepository.findAll(Sort.by("major"));
	}
	
	
	//DELETE
	public void deleteStdBySid(String str) {
		studentRepository.deleteById(str);
	}
	
	//ADD
	public void addStudent(Student student) {
		studentRepository.save(student);
	}
	//Update
	public Student updateStudent(Student student) {
		
		return studentRepository.save(student);
	}
	
	
	//Search
	public VaccineRegi getAllVaccine(){
		System.out.println(vaccineRepository);
		return vaccineRepository.findById(1).get();
	}

}
