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

@Getter
@Setter
@Table(name = "LZMovieShow")
@Entity
public class LZMovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column( nullable = false)
    private Date DATE;
    @Column(nullable = false)
    private Long CINEMAID;
     @Column( nullable = false)
    private Long MOVIEID;
     @Column( nullable = false)
    private Long HALLID;
    private Boolean ISSHOWING;
     @Column( nullable = false)
    private String CREATED_BY;
     @Column( nullable = false)
    private Date CREATED_DATE;
    private String UPDATED_BY;
    private Date UPDATED_DATE;
     @Column( nullable = false)
    private String DATABASE;

}
