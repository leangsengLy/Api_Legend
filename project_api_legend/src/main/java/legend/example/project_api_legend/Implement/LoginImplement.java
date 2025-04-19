package legend.example.project_api_legend.Implement;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import legend.example.project_api_legend.DataModel.Login.LoginDataModel;
import legend.example.project_api_legend.Dto.LoginDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Interface.LoginService;
import legend.example.project_api_legend.MappingData.LoginDataMapping;
import legend.example.project_api_legend.Model.Login;
import legend.example.project_api_legend.Repository.LoginRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class LoginImplement implements LoginService {
    private LoginRepository loginRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public LoginDto Create(LoginDataModel model) {
        Login data = LoginDataMapping.MappingData(model);
        data.setPassword(bCryptPasswordEncoder.encode(model.getPassword()));
        data.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        Login save = loginRepository.save(data);
        return LoginDataMapping.LoginMapToLoginDto(save);
    }

    @Override
    public boolean CheckEmail(String email){
        Optional<Login> checkEmail = loginRepository.findByEmail(email);
        return checkEmail.isPresent();
    }
    @Override
    public boolean CheckPasswordNEmail(String email,String password){
        Optional<Login> login = loginRepository.getDataByEmail(email);
        return bCryptPasswordEncoder.matches(password, login.get().getPassword());
    }
    @Override
    public LoginDto Update(LoginDataModel model){
        Login data = loginRepository.findById(model.getId()).get();
        data.setPassword(model.getPassword());
        data.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        data.setUpdateBy(LZGlobalHelper.Text.Admin);
        data.setUpdateDate(LZGlobalHelper.Text.DateNow);
        loginRepository.save(data);
        return LoginDataMapping.LoginMapToLoginDto(data);
    }
  
}
