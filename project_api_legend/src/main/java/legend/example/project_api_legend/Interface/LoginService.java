package legend.example.project_api_legend.Interface;


import legend.example.project_api_legend.DataModel.Login.LoginDataModel;
import legend.example.project_api_legend.Dto.LoginDto;
public interface LoginService {
    LoginDto Create(LoginDataModel model);
    boolean CheckEmail(String email);
    boolean CheckPasswordNEmail(String email,String password);
    LoginDto Update(LoginDataModel login);
}
