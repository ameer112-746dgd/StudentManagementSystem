package com.example.StudentManagementSystem.service; // Check this matches your folder!

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // IMPORTANT
import java.util.List;

@Service // <-- MAKE SURE THIS LINE IS HERE!
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public Student getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Student getByRegNumber(String regNumber) {
        return repository.findByRegNumber(regNumber).orElse(null);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}