package legend.example.project_api_legend.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class LoginDto {
    public Long Id;
    public String Email;
    public String Password;
    public String CreateBy;
    public Date CreateDate;
    public String UpdateBy;
    public Date UpdateDate;
    public String Database;
}
