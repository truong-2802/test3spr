package com.example.test3.controller;

import com.example.test3.entity.Student;
import com.example.test3.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String listStudents(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Integer> minScore,
            @RequestParam Optional<Integer> maxScore,
            @RequestParam Optional<Float> minCredit,
            @RequestParam Optional<Float> maxCredit,
            @RequestParam Optional<String> grade,
            @RequestParam Optional<Boolean> gender,
            Model model) {

        List<Student> students = studentService.findStudents(
                name.orElse(null),
                minScore.orElse(null),
                maxScore.orElse(null),
                minCredit.orElse(null),
                maxCredit.orElse(null),
                grade.filter(g -> !g.isEmpty()).map(g -> g.charAt(0)).orElse(null),
                gender.orElse(null)
        );

        model.addAttribute("listStudents", students);
        return "index";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "new_student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "update_student";
        } else {
            return "redirect:/students";
        }
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
