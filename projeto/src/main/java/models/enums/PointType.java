package models.enums;


import lombok.Getter;
import models.Ponto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public enum PointType {
    FIRSTINTYPE{
        public Ponto objeTipoPonto (Ponto point){
             point.setFirstEntryDate(LocalDateTime.now());
             return point;
        }
    },
    FIRSTOUTYPE{
        public Ponto objeTipoPonto (Ponto point){
            point.setFirstOutDate(LocalDateTime.now());
            return point;
        }
    },
    LASTINTYPE{
        public Ponto objeTipoPonto (Ponto point){
            point.setLastEntryDate(LocalDateTime.now());
            return point;
        }
    },
    LASTOUTYPE{
        @Override
        public Ponto objeTipoPonto (Ponto point){
            point.setLastOutDate(LocalDateTime.now());
            point.setTotalHours(ChronoUnit.HOURS.between(point.getFirstEntryDate(), LocalDateTime.now()));
            return point;
        }
    };

    public abstract Ponto objeTipoPonto(Ponto point);

}
