package com.ismael.FeeManagementSystem.service;
import com.ismael.FeeManagementSystem.entity.Student;
import com.ismael.FeeManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add a new student
    public Student addStudent(Student student) {
        // You can implement additional validation logic here
        return studentRepository.save(student);
    }

    // Update student information
    public Student updateStudent(Student updatedStudent) {
        // Check if the student exists
        Optional<Student> existingStudent = studentRepository.findById(updatedStudent.getStudentId());
        if (existingStudent.isPresent()) {
            Student studentToUpdate = existingStudent.get();

            // Update student information based on the provided data
            studentToUpdate.setFirstName(updatedStudent.getFirstName());
            studentToUpdate.setLastName(updatedStudent.getLastName());
            studentToUpdate.setGender(updatedStudent.getGender());
            studentToUpdate.setDateOfBirth(updatedStudent.getDateOfBirth());
            studentToUpdate.setContactInformation(updatedStudent.getContactInformation());
            studentToUpdate.setAddress(updatedStudent.getAddress());

            // Save the updated student
            return studentRepository.save(studentToUpdate);
        } else {
            // Student not found
            return null;
        }
    }

    // Remove a student by ID
    public void removeStudent(Long studentId) {
        // Check if the student exists
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        }
    }

    // Retrieve student details by ID
    public Student getStudentById(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
