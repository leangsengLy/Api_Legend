package legend.example.project_api_legend.GlobalHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusMessage {
    private String Status = "error";
    private String Message;
    private String Detail;
    public StatusMessage setDetail(String detail){
        this.Detail = detail;
        return this;
    }
}
