package service;


import models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.PointRepository;
import repository.ProjectRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class ProjectService {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<?> allocateTimeInProject(UUID userUid, Long hours) {
        var user = this.userRepository.findByUid(userUid).orElseThrow();
        var ponto = this.pointRepository.findByCreatedAtAndUserId(LocalDateTime.now(), user.getId());
        if(!ponto.isPresent()){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Não pode alocar tempo num dia em que não foi trabalhado.");
        }
        return hours <= ponto.get().getTotalHours() ? ResponseEntity.status(HttpStatus.CREATED).
            body(this.projectRepository.save(Project.builder().createdAt(LocalDateTime.now())
                .hoursDedicated(ponto.get().getTotalHours()).user(user).build())) :
                ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE.ordinal()).body("Não pode alocar tempo maior que o tempo trabalhado no dia.");
    }


}
