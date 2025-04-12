package legend.example.project_api_legend.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Login.LoginDataModel;
import legend.example.project_api_legend.Dto.LoginDto;
import legend.example.project_api_legend.Helper.LoginHelper.LoginHelper;
import legend.example.project_api_legend.Model.DB_LOGIN;
import legend.example.project_api_legend.Repository.Login.LoginRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;



@AllArgsConstructor
@RestController
public class LoginApiController {
    @Autowired
    private LoginRepository loginRepository;
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return "Fuck you baby pram" + param;
    }

    // @PostMapping(LoginHelper.Url.Create)
    // public LoginDto Create( @RequestBody LoginDataModel user){
    //     return loginRepository.save(user);
    // }
    
}
