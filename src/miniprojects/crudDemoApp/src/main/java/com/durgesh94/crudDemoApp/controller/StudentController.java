package com.durgesh94.crudDemoApp.controller;

import com.durgesh94.crudDemoApp.entity.Student;
import com.durgesh94.crudDemoApp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        // System.out.println(student.getName());
        System.out.println("Step 1: inside controller");
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student studentResp = studentService.getStudentById(id);

        if(studentResp == null){
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<Student>>getAllStudent(){
        List<Student> studentList = studentService.getAllStudent();
        if(studentList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq){
        Student updatedStudent = studentService.updateStudentById(id, studentReq);
        if(updatedStudent == null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.deleteStudentById(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("message", "Student deleted successfully.");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map> softDeleteStudent(@PathVariable Long id){
        Boolean isDeleted = studentService.softDeleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("message", "Student soft deleted successfully.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Student> getStudentByRollNo(@RequestParam int rollNo) {

        Student student = studentService.getStudentByRollNo(rollNo);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }
}
