package com.letscode.projetobiblioteca.service;

import com.letscode.projetobiblioteca.model.Student;
import com.letscode.projetobiblioteca.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
        return this.studentRepository.save(student);
    }

    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }
}
