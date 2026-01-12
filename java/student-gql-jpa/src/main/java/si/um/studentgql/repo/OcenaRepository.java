package si.um.studentgql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.studentgql.entity.OcenaEntity;

import java.util.List;

public interface OcenaRepository extends JpaRepository<OcenaEntity, Long> {
    List<OcenaEntity> findByStudent_Id(Long studentId);
}
