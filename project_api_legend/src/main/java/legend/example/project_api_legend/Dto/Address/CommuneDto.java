package legend.example.project_api_legend.Dto.Address;

import legend.example.project_api_legend.Dto.GlobalDto.BaseDto;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CommuneDto extends BaseDto {
    public Long Id;
    public Long CountryId;
    public Long PronvinceId;
    public String Name;
    public String EnglishName;
}
