package legend.example.project_api_legend.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDto {
    private Long Id;
    private String Email;
    private String Password;
    private String CreateBy;
    private Date CreateDate;
    private String UpdateBy;
    private Date UpdateDate;
    private String Database;
}
