package legend.example.project_api_legend.Controller.Address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Address.District.DistrictDataModel;
import legend.example.project_api_legend.DataModel.Address.District.DistrictFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Address.DistrictHelper;
import legend.example.project_api_legend.Interface.Address.DistrictService;
import legend.example.project_api_legend.Repository.Address.LZCountryRepository;
import legend.example.project_api_legend.Repository.Address.LZDistrictRepository;
import legend.example.project_api_legend.Repository.Address.LZProvinceRepository;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class DistrictApiController {
    private DistrictService districtService;
    private LZCountryRepository lzCountryRepository;
    private LZProvinceRepository lzProvinceRepository;
    private LZDistrictRepository lzDistrictRepository;
    @PostMapping(DistrictHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody DistrictFilterDataModel filter){
        try{
                var list = districtService.List(filter);
                return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PostMapping(DistrictHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody DistrictDataModel model){
        try{
            var msg = "";
            if(model.getName()=="" || model.getName()==null) msg ="name";
            else if(model.getCode()=="" || model.getCode()==null) msg ="code";
            else if(model.getCreateBy()=="" || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase()=="" || model.getDatabase()==null) msg ="database";
            else if(model.getCountryId()<1 || model.getDatabase()==null) msg ="countryId";
            else if(model.getProvinceId()<1 || model.getDatabase()==null) msg ="provinceId";
            if(msg!="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
            var find = lzCountryRepository.findById(model.getCountryId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            var findPro = lzProvinceRepository.findById(model.getProvinceId());
            if(!findPro.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Province not found!"),HttpStatus.NOT_FOUND);
            boolean isExistedCode = districtService.CheckCode(model.getCode(), 0L, model.getDatabase());
            if(isExistedCode) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already existed"),HttpStatus.NOT_FOUND);
            var data = districtService.Create(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @PostMapping(DistrictHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody DistrictDataModel model){
        try{
            var msg = "";
            if(model.getName().isEmpty() || model.getName()==null) msg ="name";
            else if(model.getCode().isEmpty() || model.getCode()==null) msg ="code";
            else if(model.getCreateBy().isEmpty() || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase().isEmpty() || model.getDatabase()==null) msg ="database";
            else if(model.getId()<1 || model.getId()==null) msg ="id";
            else if(model.getCountryId()<1 || model.getDatabase()==null) msg ="countryId";
            else if(model.getProvinceId()<1 || model.getDatabase()==null) msg ="provinceId";
            if(!msg.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);
            var find = lzCountryRepository.findById(model.getCountryId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            var findDistrict = lzDistrictRepository.findById(model.getId());
            if(!findDistrict.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("District not found!"),HttpStatus.NOT_FOUND);
            var findPro = lzProvinceRepository.findById(model.getProvinceId());
            if(!findPro.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Province not found!"),HttpStatus.NOT_FOUND);
             boolean isExistedCode = districtService.CheckCode(model.getCode(), model.getId(), model.getDatabase());
            if(isExistedCode) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Code already existed"),HttpStatus.NOT_FOUND);
            var data = districtService.Update(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping(DistrictHelper.Url.CheckCode)
    public ResponseEntity<?> CheckCode(@RequestParam String code,@RequestParam Long id,@RequestParam String database){
        try{
            if(code.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field code is required"),HttpStatus.BAD_REQUEST); 
            boolean isExistedCode = districtService.CheckCode(code, id, database);
                return new ResponseEntity<>(isExistedCode,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping(DistrictHelper.Url.Delete)
    public ResponseEntity<?> Delete(@RequestParam Long id){
        try{
                if(id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field id is required"),HttpStatus.BAD_REQUEST); 
                var find = lzDistrictRepository.findById(id);
                if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
                var isSussess = districtService.Delete(id);
                return new ResponseEntity<>(isSussess,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
