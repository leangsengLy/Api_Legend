package legend.example.project_api_legend.DataModel.Movie.MovieType;


import legend.example.project_api_legend.DataModel.BaseFilterDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieTypeFilterDataMode extends BaseFilterDataModel {
    private Long Id;
    private String Name;
    private String EnglishName;
}
