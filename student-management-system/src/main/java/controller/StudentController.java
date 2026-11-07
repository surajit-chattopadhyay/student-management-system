package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>();

    @GetMapping("/students")
    public List<Student> getStudents() {

        return students;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {

        students.add(student);

        return student;
    }
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {

        boolean removed = students.removeIf(
                student -> student.getId() == id
        );

        if (removed) {
            return "Student deleted successfully!";
        }

        return "Student not found!";
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getId() == id) {

                student.setName(updatedStudent.getName());
                student.setAge(updatedStudent.getAge());
                student.setCourse(updatedStudent.getCourse());
                student.setMarks(updatedStudent.getMarks());

                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity
                .notFound()
                .build();
    }
    @GetMapping("/students/search")
    public ResponseEntity<?> searchStudent(@RequestParam int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity
                .notFound()
                .build();
    }
}