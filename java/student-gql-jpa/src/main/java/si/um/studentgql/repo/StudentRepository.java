package si.um.studentgql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.studentgql.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> { }
