package legend.example.project_api_legend.Controller;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Login.LoginDataModel;
import legend.example.project_api_legend.Dto.LoginDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.LoginHelper.LoginHelper;
import legend.example.project_api_legend.Interface.LoginService;
import legend.example.project_api_legend.Model.Login;
import legend.example.project_api_legend.Repository.Login.LoginRepository;
import lombok.AllArgsConstructor;

import java.lang.StackWalker.Option;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@AllArgsConstructor
@RestController
public class LoginApiController {
    private LoginService loginService;
    private LoginRepository loginRepository;
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return "Fuck you baby pram" + param;
    }

    @PostMapping(LoginHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody LoginDataModel user){
        try{
        if(user.getEmail()=="" 	|| user.getPassword()=="") return new ResponseEntity<>(LoginHelper.Message.InvalidUserPassword,HttpStatus.BAD_REQUEST);
            boolean isHaveCheckEmail = loginService.CheckEmail(user.getEmail());
            System.out.println(isHaveCheckEmail);
            if(isHaveCheckEmail) return new ResponseEntity<>(LoginHelper.Message.EmailAlready,HttpStatus.BAD_REQUEST);
            user.setCreateBy("LyZee");
            user.setCreateDate(new Date());
            LoginDto data = loginService.Create(user);
            return new ResponseEntity<>(data,HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(LoginHelper.Url.Login)
    public ResponseEntity<?> UserLogin(@RequestBody LoginDataModel user) {
        try{
            if(user.getEmail()=="" 	|| user.getPassword()=="") return new ResponseEntity<>(LoginHelper.Message.InvalidUserPassword,HttpStatus.BAD_REQUEST);
                boolean isHaveCheckEmail = loginService.CheckPasswordNEmail(user.getEmail(), user.getPassword());
                return new ResponseEntity<>(isHaveCheckEmail,HttpStatus.OK);
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PostMapping(LoginHelper.Url.Update)
    public ResponseEntity<?> postMethodName(@RequestBody LoginDataModel login) {
        try{
            if(login.getId()==null)  return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Id not found!"),HttpStatus.BAD_REQUEST);
            Optional<Login> find = loginRepository.findById(login.getId());
            if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Data not found!"),HttpStatus.BAD_REQUEST);
            if(login.getEmail()=="" 	|| login.getPassword()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Please input email or password!"),HttpStatus.BAD_REQUEST);
            boolean isHaveCheckEmail = loginService.CheckPasswordNEmail(login.getEmail(), login.getOldPassword());
            if(!isHaveCheckEmail)return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Your password is incorrect!"),HttpStatus.BAD_REQUEST);
            LoginDto data =  loginService.Update(login);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    
    
    
}
