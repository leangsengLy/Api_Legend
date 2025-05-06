package legend.example.project_api_legend.DataModel.Movie.MovieType;

import legend.example.project_api_legend.DataModel.BaseDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieTypeDataModel extends BaseDataModel {
    private Long Id;
    private String Name;
    private String EnglishName;
}
