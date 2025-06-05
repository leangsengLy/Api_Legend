package legend.example.project_api_legend.Dto.LZModuleFood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDetailDto {
    private Long FoodId;
    private String FoodImagePath;
    private String LocalHost;
    private String FoodName;
    private String EnglishName;
    private Double Price;
    private Long Qty;
    private Long CinemaId;
    private String CinemaName;
    private String CinemaEnglishName;
    private String Code;
    private String Address;
    private String CinemaImagePath;
}
