package legend.example.project_api_legend.Controller.Address;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Address.Country.CountryFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Address.CountryHelper;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CountryApiController {
    @PostMapping(CountryHelper.Url.List)
    public ResponseEntity<?> List(CountryFilterDataModel filter){
        try{
            
                return new ResponseEntity<>(true,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
