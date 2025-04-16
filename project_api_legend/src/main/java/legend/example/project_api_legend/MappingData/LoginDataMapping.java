package legend.example.project_api_legend.MappingData;

import legend.example.project_api_legend.DataModel.Login.LoginDataModel;
import legend.example.project_api_legend.Dto.LoginDto;
import legend.example.project_api_legend.Model.Login;

public class LoginDataMapping {
    public static Login MappingData(LoginDataModel data) {
        return new Login(
            data.getId(),
            data.getEmail(),
            data.getPassword(),
            data.getCreateBy(),
            data.getCreateDate(),
            data.getUpdateBy(),
            data.getUpdateDate(),
            data.getDatabase()
        );
    }
    
    public static LoginDto LoginMapToLoginDto(Login data) {
        return new LoginDto(
            data.getId(),
            data.getEmail(),
            data.getPassword(),
            data.getCreateBy(),
            data.getCreateDate(),
            data.getUpdateBy(),
            data.getUpdateDate(),
            data.getDatabase()
        );
    }
}
