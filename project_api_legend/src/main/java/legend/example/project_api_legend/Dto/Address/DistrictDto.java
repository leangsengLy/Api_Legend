package legend.example.project_api_legend.Dto.Address;

import legend.example.project_api_legend.Dto.GlobalDto.BaseDto;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class DistrictDto extends BaseDto {
    public Long Id;
    public Long CountryId;
    public Long ProvinceId;
    public String Code;
    public String Name;
    public String EnglishName;
}
