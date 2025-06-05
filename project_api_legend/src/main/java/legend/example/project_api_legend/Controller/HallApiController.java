package legend.example.project_api_legend.Controller;
import legend.example.project_api_legend.DataModel.Hall.HallDataModel;
import legend.example.project_api_legend.DataModel.Hall.HallFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.LZModuleSetting.HallHelper;
import legend.example.project_api_legend.Interface.HallService;
import legend.example.project_api_legend.Repository.LZHallRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HallApiController {
    private HallService hallService;
    private LZHallRepository lzHallRepository;
    @PostMapping(HallHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody HallFilterDataModel filter){
        try {
            var list = hallService.List(filter);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(HallHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody HallDataModel model){
         
        try {
            var message = "";
             if(model.getCode() == null || model.getCode().isEmpty()) message = "The field code is required! ";
            else if(model.getName() == null || model.getName().isEmpty()) message = "The field field is required! ";
            else if(model.getTotalChairs() == null || model.getTotalChairs() < 1) message = "The field total chairs must be greater than 0!";
            if(message != "") {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail(message), HttpStatus.BAD_REQUEST);
            }
            var isExisted  = hallService.CheckCode(model.getCode(), model.getId() != null ? model.getId() : 0L);
            if(isExisted) {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field code already exists!"), HttpStatus.BAD_REQUEST);
            }
            var result = hallService.Create(model);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @PostMapping(HallHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody HallDataModel model){
        var message = "";
        try {
            if(model.getId() == null || model.getId() < 1) message = "The field id is required! ";
            else if(model.getCode() == null || model.getCode().isEmpty()) message = "The field code is required! ";
            else if(model.getName() == null || model.getName().isEmpty()) message = "The field field is required! ";
            else if(model.getTotalChairs() == null || model.getTotalChairs() < 1) message = "The field total chairs must be greater than 0!";
            if(message != "") {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail(message), HttpStatus.BAD_REQUEST);
            }
            if(model.getIsActive() == null) model.setIsActive(true);
            var isExisted  = hallService.CheckCode(model.getCode(), model.getId() != null ? model.getId() : 0L);
            if(isExisted) {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field code already exists!"), HttpStatus.BAD_REQUEST);
            }
            var data = lzHallRepository.findById(model.getId()).get();
            if(data == null) {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataNotFound.setDetail("Hall not found!"), HttpStatus.NOT_FOUND);
            }
            var result = hallService.Update(model);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
     @GetMapping(HallHelper.Url.CheckCode)
    public ResponseEntity<?> CheckCode(@RequestParam String code,@RequestParam(defaultValue = "0") Long id){
        try {
            if(code=="" || code.isEmpty()) return new ResponseEntity<>("The field code is required!", HttpStatus.BAD_REQUEST);
            var isExists = hallService.CheckCode(code, id);
            return new ResponseEntity<>(isExists, HttpStatus.OK);
        } catch (Exception e) {
              return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping(HallHelper.Url.Delete)
    public ResponseEntity<?> Delete(Long id){
        try {
            var findHall = lzHallRepository.findById(id);
            if(findHall.isEmpty()) {
                return new ResponseEntity<>(LZGlobalHelper.Message.DataNotFound.setDetail("Hall not found!"), HttpStatus.NOT_FOUND);
            }
            var status = hallService.Delete(id);
            return new ResponseEntity<>(LZGlobalHelper.Message.Successfuly.setDetail(status), HttpStatus.OK);
        } catch (Exception e) {
              return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
