package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    /**
     * Algorithm: View All Students
     * GET request to the root URL.
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", service.getAllStudents());
        return "index"; // Looks for templates/index.html
    }

    /**
     * Algorithm: Add Student (Part 1 - Show Form)
     */
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // Create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "add_student"; // Looks for templates/add_student.html
    }

    /**
     * Algorithm: Add/Update Student (Part 2 - Save Data)
     * Handles the HTTP POST request from the form.
     */
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // Save student to database
        service.saveStudent(student);
        return "redirect:/"; // Redirect back to homepage
    }

    /**
     * Algorithm: Update Student (Show Form with existing data)
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        // Get student from the service
        Student student = service.getById(id);

        // Set student as a model attribute to pre-populate the form
        model.addAttribute("student", student);
        return "update_student"; // Looks for templates/update_student.html
    }

    /**
     * Algorithm: Delete Student
     */
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long id) {
        // Call delete method
        this.service.deleteStudent(id);
        return "redirect:/";
    }

    /**
     * Algorithm: Search Student by Registration Number
     */
    @GetMapping("/search")
    public String search(@RequestParam("regNumber") String regNumber, Model model) {
        Student student = service.getByRegNumber(regNumber);

        if (student != null) {
            // Found: show only this student
            model.addAttribute("listStudents", Collections.singletonList(student));
        } else {
            // Not Found: show empty list and error message
            model.addAttribute("listStudents", Collections.emptyList());
            model.addAttribute("error", "Student with Reg Number [" + regNumber + "] not found.");
        }
        return "index";
    }
}