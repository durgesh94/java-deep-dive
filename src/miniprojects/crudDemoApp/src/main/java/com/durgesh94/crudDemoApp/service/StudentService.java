package com.durgesh94.crudDemoApp.service;

import com.durgesh94.crudDemoApp.entity.Student;
import com.durgesh94.crudDemoApp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        // business logic
        student.setDeleted(false);
        return  studentRepository.save(student);
    }

    public Student getStudentById(Long id){
        // return studentRepository.findById(id).orElse(null);
        return studentRepository.findByIdAndDeletedFalse(id).orElse(null);
    }

    public List<Student> getAllStudent(){
        // return studentRepository.findAll();
        return studentRepository.findByDeletedIsFalse();
    }

    public Student updateStudentById(Long id, Student studentReq){
        // Optional<Student> existingStudent = studentRepository.findById(id);
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedFalse(id);

        if(existingStudent.isEmpty()){
            return null;
        }

        Student updatedStudent = existingStudent.get();
        updatedStudent.setId(studentReq.getId());
        updatedStudent.setName(studentReq.getName());
        updatedStudent.setRollNo(studentReq.getRollNo());
        updatedStudent.setAge(studentReq.getAge());
        updatedStudent.setDeleted(false);
        return studentRepository.save(updatedStudent);
    }

    public Boolean deleteStudentById(Long id){
       boolean isStudentExist = studentRepository.existsById(id);

        if(!isStudentExist) {
            return false;
        }

        studentRepository.deleteById(id);
        return true;
    }

    public Boolean softDeleteStudent(Long id){
        // Optional<Student> existingStudent = studentRepository.findById(id);
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedFalse(id);

        if(existingStudent.isEmpty()){
            return false;
        }

        Student updatedStudent = existingStudent.get();
        updatedStudent.setDeleted(true);
        studentRepository.save(updatedStudent);
        return true;
    }

    public Student getStudentByRollNo(int rollNo) {
        return studentRepository
                .findByRollNoAndDeletedFalse(rollNo)
                .orElse(null);
    }
}
