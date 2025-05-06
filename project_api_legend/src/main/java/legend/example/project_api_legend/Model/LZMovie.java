package legend.example.project_api_legend.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Table(name = "dbmovie")
@Entity
public class LZMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long movieTypeId;
    @Column(nullable = false)
    private String name;
    private String englishName;
    @Column(nullable = false)
    private Double duration;
    @Column(nullable = false)
    private Date release;
    private String imagePath;
    @Column(nullable = false)
    private Date fromDate;
    @Column(nullable = false)
    private Date toDate;
    private String urlYoutube;
    private String description;
     @Column(nullable = false)
    private String createBy;
    @Column(nullable = false)
    private Date createDate;
    private String updateBy;
    private Date updateDate;

}
