package legend.example.project_api_legend.Model.Address;

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
@Table(name = "LZCommune")
@Setter
@Getter
public class LZCommune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private Long CountryId;
    @Column(nullable = false)
    private Long DistrictId;
    @Column(nullable = false,length = 50)
    private String Name;
    @Column(length = 50)
    private String EnglishName;
    @Column(nullable = false)
    private String CreatedBy;
    private String UpdatedBy;
     @Column(nullable = false)
    private Date CreatedDate;
    private Date UpdatedDate;
    @Column(nullable = false,length = 50)
    private String Database;
}
