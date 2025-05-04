package legend.example.project_api_legend.DataModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseFilterDataModel {
    public String Search;
    public Long Pages;
    public Long Records;
}
