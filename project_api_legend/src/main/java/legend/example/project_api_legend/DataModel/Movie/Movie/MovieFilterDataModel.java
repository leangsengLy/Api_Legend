package legend.example.project_api_legend.DataModel.Movie.Movie;

import legend.example.project_api_legend.DataModel.BaseFilterDataModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieFilterDataModel extends BaseFilterDataModel {
    private Long Id;
    private Long MovieTypeId;
    private String Name;
    private String EnglishName;
}
