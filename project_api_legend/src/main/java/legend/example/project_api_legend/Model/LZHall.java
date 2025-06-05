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
@Table(name = "LZHall")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LZHall  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long ID;
    @Column(nullable = false,length = 10)
    public String CODE;
    @Column(nullable = false,length = 50)
    public String NAME;
    public String EN_NAME;
    public Long TOTAL_CHAIRS;
    public Boolean IS_ACTIVE;
    @Column(nullable = false)
    public String CREATE_BY;
    public String UPDATE_BY;
    @Column(nullable = false)
    public Date CREATE_DATE;
    public Date UPDATE_DATE;
    @Column(nullable = false)
    public String DATABASE;
}
