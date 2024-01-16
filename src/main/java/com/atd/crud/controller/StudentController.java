package com.atd.crud.controller;

import com.atd.crud.model.Student;
import com.atd.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/select")
    public List<Student> selectAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/select/{id}")
    public Student selectStudentById(@PathVariable Long id){
        return studentService.getStudentById(id).orElse(null);
    }

    @PostMapping("/insert")
    public Student insertStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){
        if(studentService.getStudentById(id).isPresent()){
            student.setId(id);
            return studentService.saveStudent(student);
        }
        else{
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
}
