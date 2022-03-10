package com.capg.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Student;
import com.capg.exception.RecordNotFoundException;
import com.capg.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepository repo ;
	
	@PostMapping(value="/create-student", consumes = { MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Student>  createStudent(@Valid @RequestBody Student stu)
	{
		Student student = repo.save(stu);
		
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);

	}
	
	@GetMapping( "/student-list")
   public List<Student> getList()
   {
	   return (List<Student>)repo.findAll();
   }
	
	@GetMapping("/student-list/{id}")
	public ResponseEntity<Student> getDataById(@PathVariable int id ) throws RecordNotFoundException
	{
		 if(repo.findById(id).isPresent())
		 {
			 Student student = repo.findById(id).get();
			 return new ResponseEntity<Student>(student,HttpStatus.OK);
		 }
		 else
		 {
			 throw new RecordNotFoundException("Student with id"+id+"not found");
		 }
	}
	
	@PutMapping("/update-details")
	public Student updateData(@RequestBody Student stu)
	{
		return repo.save(stu);
	}
	
	@DeleteMapping("/delete-details")
	public void deleteById(@PathVariable int id)
	{
	repo.deleteById(id);
	}
	
}
