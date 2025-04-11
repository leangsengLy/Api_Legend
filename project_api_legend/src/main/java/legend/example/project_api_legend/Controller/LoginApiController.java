package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
@RequestMapping("api/login")
public class LoginApiController {
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return "Fuck you baby pram" + param;
    }
    
}
