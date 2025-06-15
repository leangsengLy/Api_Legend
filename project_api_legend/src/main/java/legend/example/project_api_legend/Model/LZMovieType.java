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

@Entity
@Setter
@Getter
@Table(name = "dbmovieType")
public class LZMovieType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String englishName;
    @Column(nullable = false)
    private String createBy;
    @Column(nullable = false)
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    @Column(nullable = false,name = "Database")
    private String database;
}
