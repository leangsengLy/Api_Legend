package legend.example.project_api_legend.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LZUserProfile")
@Setter
@Getter
public class LZUserProfile {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long ID;
    @Column( nullable = false,  unique = true)
    private Long LOGIN_ID;
    private String PROFILE_IMG_PATH;
    private String CV_IMG_PATH;
     @Column( nullable = false,  unique = true)
    private String NAME;
    private String EN_NAME;
    @Column(length = 500)
    private String DESCRIPTION;
    @Column( nullable = false, length = 10)
    private String PHONE1;
    @Column( length = 10)
    private String PHONE2;
    @Column(nullable = false)
    private String MAJOR;
    @Column( length = 500)
    private String EXPERIENCE_DESC;
    @Column( nullable = false,length = 500)
    private String ADDRESS;
     @Column(nullable = false, length = 500)
    private String EN_ADDRESS;
    @Column( nullable = false)
    private String CREATED_BY;
     @Column( nullable = false)
    private Date CREATED_DATE;
    private String UPDATED_BY;
    private Date UPDATED_DATE;
     @Column( nullable = false)
    private String DATABASE;
}
