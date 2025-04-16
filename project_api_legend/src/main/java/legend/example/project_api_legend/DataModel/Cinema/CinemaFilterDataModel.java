package legend.example.project_api_legend.DataModel.Cinema;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CinemaFilterDataModel {
    private Long Id;
    private String Name;
    private String EnglishName;
    private String Address;
    private LocalTime StartTime;
    private LocalTime EndTime;
}
