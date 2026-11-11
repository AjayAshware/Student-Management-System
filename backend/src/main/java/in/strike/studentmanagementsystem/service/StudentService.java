package in.strike.studentmanagementsystem.service;

import in.strike.studentmanagementsystem.entity.StudentInfo;
import in.strike.studentmanagementsystem.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    public StudentInfo saveStudent(StudentInfo studentReq){
         return studentRepo.save(studentReq);
    }

    public StudentInfo getStudentById(Long id){
        Optional<StudentInfo> studentResp = studentRepo.findById(id);
        return studentResp.orElse(null);
    }

    public StudentInfo getStudentByName(String name){
        Optional<StudentInfo> studentResp = studentRepo.findByName(name);
        return  studentResp.orElse(null);
    }

    public List<StudentInfo> getAllStudent(){
        return studentRepo.findAll();
    }

    public List<StudentInfo> getStudentBySubject(String subject) {
        return studentRepo.findBySubject(subject);
    }

    public StudentInfo updateStudent(Long id, StudentInfo updateInfo){
        StudentInfo studentExist = studentRepo.findById(id).orElse(null);
        if (studentExist == null) return  null;

        studentExist.setName(updateInfo.getName());
        studentExist.setEmail(updateInfo.getEmail());
        studentExist.setRollNum(updateInfo.getRollNum());
        studentExist.setSubject(updateInfo.getSubject());
        studentExist.setAge(updateInfo.getAge());

        return studentRepo.save(studentExist);
    }

    public void deleteStudent(Long id) {
        if (!studentRepo.existsById(id))  return;

        studentRepo.deleteById(id);
    }
}
