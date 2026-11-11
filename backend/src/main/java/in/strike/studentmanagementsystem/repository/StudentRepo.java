package in.strike.studentmanagementsystem.repository;

import in.strike.studentmanagementsystem.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<StudentInfo, Long> {

    Optional<StudentInfo> findByName(String name);

    List<StudentInfo> findBySubject(String subject);
}
