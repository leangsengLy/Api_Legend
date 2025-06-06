package legend.example.project_api_legend.Model;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LZShowTime")
@Getter
@Setter
public class LZShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "DATE", nullable = false)
    private Date DATE;
     @Column(name = "HALL_ID", nullable = false)
    private Long HALL_ID;
    @Column(name = "ON_TIME", nullable = false)
    private LocalTime ON_TIME;
    @Column(name = "OFF_TIME", nullable = false)
    private LocalTime OFF_TIME;
}
