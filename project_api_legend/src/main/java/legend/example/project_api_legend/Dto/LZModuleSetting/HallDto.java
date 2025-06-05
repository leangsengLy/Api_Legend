package legend.example.project_api_legend.Dto.LZModuleSetting;

import legend.example.project_api_legend.Dto.GlobalDto.BaseDto;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
public class HallDto extends BaseDto{
    public Long Id;
    public String Code;
    public String Name;
    public String EnglishName;
    public Boolean IsActive;
    public Long TotalChairs;
}
