package legend.example.project_api_legend.Controller.Address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Address.Province.ProvinceDataModel;
import legend.example.project_api_legend.DataModel.Address.Province.ProvinceFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Address.ProvinceHelper;
import legend.example.project_api_legend.Interface.Address.ProvinceService;
import legend.example.project_api_legend.Repository.Address.LZCountryRepository;
import legend.example.project_api_legend.Repository.Address.LZProvinceRepository;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class ProvinceApiController {
    private LZProvinceRepository lzProvinceRepository;
    private ProvinceService provinceService;
    private LZCountryRepository lzCountryRepository;
    @PostMapping(ProvinceHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody ProvinceFilterDataModel filter){
        try{
                var list = provinceService.List(filter);
                return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PostMapping(ProvinceHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody ProvinceDataModel model){
        try{
            var msg = "";
            if(model.getName()=="" || model.getName()==null) msg ="name";
            else if(model.getCreateBy()=="" || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase()=="" || model.getDatabase()==null) msg ="database";
            else if(model.getCountryId() < 1 || model.getCountryId()==null) msg ="countryId";
             var findCountry = lzCountryRepository.findById(model.getCountryId());
            if(!findCountry.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            if(msg!="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
            var data = provinceService.Create(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @PostMapping(ProvinceHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody ProvinceDataModel model){
        try{
            var msg = "";
            if(model.getName().isEmpty() || model.getName()==null) msg ="name";
            else if(model.getCreateBy().isEmpty() || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase().isEmpty() || model.getDatabase()==null) msg ="database";
            else if(model.getId()<1 || model.getId()==null) msg ="id";
             else if(model.getCountryId() < 1 || model.getCountryId()==null) msg ="countryId";
            if(!msg.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
            var find = lzProvinceRepository.findById(model.getId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Province not found!"),HttpStatus.NOT_FOUND);
            var findCountry = lzCountryRepository.findById(model.getCountryId());
            if(!findCountry.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            var data = provinceService.Update(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
     @GetMapping(ProvinceHelper.Url.Delete)
    public ResponseEntity<?> Delete(@RequestParam Long id){
        try{
                if(id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field id is required"),HttpStatus.BAD_REQUEST); 
                var find = lzProvinceRepository.findById(id);
                if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
                var isSussess = provinceService.Delete(id);
                return new ResponseEntity<>(isSussess,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
