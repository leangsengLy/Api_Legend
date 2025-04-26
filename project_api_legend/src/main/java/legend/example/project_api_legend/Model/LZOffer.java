package legend.example.project_api_legend.Model;

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
@Table(name = "DBOffer")
public class LZOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathImage;
    @Column(nullable = false)
    private String label;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private String database;
    @Column(nullable = false)
    private String createBy;
    @Column(nullable = false)
    private Date createDate;
    private String updateBy;
    private Date updateDate;
}
