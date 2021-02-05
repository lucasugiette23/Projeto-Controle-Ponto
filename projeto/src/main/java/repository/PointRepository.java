package repository;


import models.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Ponto, Long> {

    Optional<Ponto> findByCreatedAtAndUserId(LocalDateTime creatdAt, Long id);

}
