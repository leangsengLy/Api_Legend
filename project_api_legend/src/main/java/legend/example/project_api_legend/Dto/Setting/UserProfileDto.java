package legend.example.project_api_legend.Dto.Setting;

import java.util.Date;

import legend.example.project_api_legend.Dto.GlobalDto.BaseDto;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UserProfileDto extends BaseDto {
    public Long Id;
    public Long LoginId;
    public String ProfileImagePath;
    public String CoverImagePath;
    public String Name;
    public String EnglishName;
    public String Description;
    public String Phone1;
    public String Phone2;
    public String Major;
    public String ExperienceDescription;
    public String Address;
    public String EnglishAddress;
    public String CreatedBy;
    public String UpdatedBy;
    public String Database;
    public Date CreatedDate;
    public Date UpdatedDate;
}
