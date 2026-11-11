package in.strike.studentmanagementsystem.controller;

import in.strike.studentmanagementsystem.entity.StudentInfo;
import in.strike.studentmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/studentInformation")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public ResponseEntity<StudentInfo> saveStudent(@RequestBody  StudentInfo studentReq){
        StudentInfo studentSave = studentService.saveStudent(studentReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(studentSave);
    }

    @GetMapping("/fetchStudent")
    public ResponseEntity<StudentInfo> fetchStudent(@RequestParam Long id){
        StudentInfo studentFetch = studentService.getStudentById(id);

        if (studentFetch == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(studentFetch);
    }

    @GetMapping("/fetchStudentbyname")
    public ResponseEntity<StudentInfo> fetchStudentByName(@RequestParam String name){
        StudentInfo studentByName = studentService.getStudentByName(name);

        if (studentByName == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(studentByName);
    }

    @GetMapping("/fetchStudentbysubject")
    public ResponseEntity<List<StudentInfo>> fetchStudentBySubject(@RequestParam String subject){
        List<StudentInfo> studentBySubList = studentService.getStudentBySubject(subject);

        if (studentBySubList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(studentBySubList);
    }

    @GetMapping("/fetchAllStudent")
    public ResponseEntity<List<StudentInfo>> fetchAllStudent(){
        List<StudentInfo> studentList = studentService.getAllStudent();

        if (studentList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(studentList);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<StudentInfo> updateStudent(@RequestParam Long id, @RequestBody StudentInfo studentReq){
        StudentInfo studentResp = studentService.updateStudent(id, studentReq);

        if (studentResp == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/deletestudent")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student Deleted...!");
    }
}
