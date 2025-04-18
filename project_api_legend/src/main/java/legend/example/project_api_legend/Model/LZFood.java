package legend.example.project_api_legend.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="\"DBFOOD\"")
@Entity
public class LZFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String englishName;
    private Long qty;
    private Double price;
    private String ImagePath;
    private String Localhost;
    @Column(nullable = false)
    private String database;
    @Column(nullable = false)
    private String createBy;
    private String updateBy;
    @Column(nullable = false)
    private Date createDate;
    private Date updateDate;
    @Column(nullable = false)
    private Long cinemaId;
}
