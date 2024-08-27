package com.example.test3.service;

import com.example.test3.entity.Student;
import com.example.test3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudents(String name, Integer minScore, Integer maxScore, Float minCredit, Float maxCredit, Character grade, Boolean gender) {
        return studentRepository.findStudents(name, minScore, maxScore, minCredit, maxCredit, grade, gender);
    }
}
