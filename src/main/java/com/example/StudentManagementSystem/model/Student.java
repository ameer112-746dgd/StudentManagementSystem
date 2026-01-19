package com.example.StudentManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reg_number", nullable = false, unique = true)
    private String regNumber;

    private String name;
    private String email;
    private String department;
    private String level;

    // --- MANUAL GETTERS AND SETTERS (Add these!) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}