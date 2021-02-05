package models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ponto")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    @Type(type="uuid-char")
    private UUID uid;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private LocalDateTime firstEntryDate;

    @Column(updatable = false)
    private LocalDateTime firstOutDate;

    @Column(updatable = false)
    private LocalDateTime lastEntryDate;

    @Column(updatable = false)
    private LocalDateTime lastOutDate;

    private int points;

    private Long totalHours;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
