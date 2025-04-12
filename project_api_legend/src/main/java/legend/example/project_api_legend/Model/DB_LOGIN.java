package legend.example.project_api_legend.Model;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "DBLG")
public class DB_LOGIN {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "EMAIL", nullable = false, length = 20)
    private String Email;
    @Column(name = "PASSWORD", nullable = false, length = 13)
    private String Password;
    @Column(name = "CREATE_BY", nullable = false)
    private String CreatedBy;
    @Column(name = "UPDATE_BY", nullable = false)
    private Date CreatedDate;
    @Column(name="UPDATE_BY", nullable = true)
    private String UpdatedBy;
    @Column(name="UPDATE_DATE", nullable = true)
    private Date UpdatedDate;
}
