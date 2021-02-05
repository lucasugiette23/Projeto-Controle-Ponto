package service;


import models.enums.PointType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import repository.PointRepository;
import repository.UserRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Environment environment;

    public ResponseEntity<?> baterPonto(Long id, String hourPoint) {
        var user = this.userRepository.findById(id).orElseThrow();
        var ponto = this.pointRepository.findByCreatedAtAndUserId(LocalDateTime.now(), user.getId());
        var retorno = PointType.valueOf(hourPoint).objeTipoPonto(ponto.get());
        if(hourPoint == PointType.LASTINTYPE.toString()){
            long hours = ChronoUnit.HOURS.between(retorno.getFirstOutDate(), LocalDateTime.now());
            if(hours <= 1L){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Deve haver no mínimo 1 hora de almoço");
            }
        }
        this.pointRepository.save(retorno);
        return this.validaFimSemana() ? ResponseEntity.status(HttpStatus.CREATED).body(retorno) :
                ResponseEntity.status(HttpStatus.CREATED).body("Sábado e domingo não são permitidos como dia de trabalho");
    }

    public boolean validaFimSemana(){
        DayOfWeek d = LocalDateTime.now().getDayOfWeek();
       return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY ? false : true;
    }

}
