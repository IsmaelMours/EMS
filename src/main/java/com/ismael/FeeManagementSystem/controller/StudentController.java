package com.ismael.FeeManagementSystem.controller;

import com.ismael.FeeManagementSystem.entity.Student;
import com.ismael.FeeManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student updatedStudent) {
        return studentService.updateStudent(updatedStudent);
    }

    @DeleteMapping("/remove/{studentId}")
    public void removeStudent(@PathVariable Long studentId) {
        studentService.removeStudent(studentId);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}

