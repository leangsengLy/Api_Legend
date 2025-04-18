package legend.example.project_api_legend.Model;

import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DBCinema")
public class LZCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    private String enName;
    private String code;
    private String pathImage;
    private String Localhost;
    @Column(nullable=false)
    private String address;
    private LocalTime startTime;
    private LocalTime endTime;
    private String latMap;
    private String longMap;
    
    @Column(nullable = false)
    private String database;
    @Column(nullable = false)
    private String createBy;
    private String updateBy;
    @Column(nullable = false)
    private Date createDate;
    private Date updateDate;
}
