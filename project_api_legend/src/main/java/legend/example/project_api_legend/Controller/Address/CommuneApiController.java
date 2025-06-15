package legend.example.project_api_legend.Controller.Address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Address.Commune.CommuneDataModel;
import legend.example.project_api_legend.DataModel.Address.Commune.CommuneFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Address.CommuneHelper;
import legend.example.project_api_legend.Interface.Address.CommuneService;
import legend.example.project_api_legend.Repository.Address.LZCommuneRepository;
import legend.example.project_api_legend.Repository.Address.LZCountryRepository;
import legend.example.project_api_legend.Repository.Address.LZDistrictRepository;
import legend.example.project_api_legend.Repository.Address.LZProvinceRepository;
import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
public class CommuneApiController {
    private CommuneService communeService;
    private LZCountryRepository lzCountryRepository;
    private LZDistrictRepository lzDistrictRepository;
    private LZCommuneRepository lzCommuneRepository;
    @PostMapping(CommuneHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody CommuneFilterDataModel filter){
        try{
                var list = communeService.List(filter);
                return new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PostMapping(CommuneHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody CommuneDataModel model){
        try{
            var msg = "";
            if(model.getName()=="" || model.getName()==null) msg ="name";
            else if(model.getCreateBy()=="" || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase()=="" || model.getDatabase()==null) msg ="database";
            else if(model.getDistrictId()<1 || model.getDatabase()==null) msg ="districtId";
            else if(model.getCountryId()<1 || model.getDatabase()==null) msg ="countryId";
            if(msg!="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);

             var find = lzCountryRepository.findById(model.getCountryId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            var findPro = lzDistrictRepository.findById(model.getDistrictId());
            if(!findPro.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Province not found!"),HttpStatus.NOT_FOUND);

            var data = communeService.Create(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @PostMapping(CommuneHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody CommuneDataModel model){
        try{
            var msg = "";
            if(model.getName().isEmpty() || model.getName()==null) msg ="name";
            else if(model.getCreateBy().isEmpty() || model.getCreateBy()==null) msg ="Username";
            else if(model.getDatabase().isEmpty() || model.getDatabase()==null) msg ="database";
            else if(model.getId()<1 || model.getId()==null) msg ="id";
            else if(model.getDistrictId()<1 || model.getDatabase()==null) msg ="districtId";
            else if(model.getCountryId()<1 || model.getDatabase()==null) msg ="countryId";
            if(!msg.isEmpty()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field ("+msg+") is required"),HttpStatus.BAD_REQUEST);

            var find = lzCountryRepository.findById(model.getCountryId());
            if(!find.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Country not found!"),HttpStatus.NOT_FOUND);
            var findPro = lzDistrictRepository.findById(model.getDistrictId());
            if(!findPro.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Province not found!"),HttpStatus.NOT_FOUND);
            var findCommune = lzCountryRepository.findById(model.getId());
            if(!findCommune.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Commune not found!"),HttpStatus.NOT_FOUND);
            var data = communeService.Update(model);
            return new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
   
     @GetMapping(CommuneHelper.Url.Delete)
    public ResponseEntity<?> Delete(@RequestParam Long id){
        try{
                if(id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field id is required"),HttpStatus.BAD_REQUEST); 
                var find = lzCommuneRepository.findById(id);
                if(!find.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Commune not found!"),HttpStatus.NOT_FOUND);
                var isSussess = communeService.Delete(id);
                return new ResponseEntity<>(isSussess,HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
