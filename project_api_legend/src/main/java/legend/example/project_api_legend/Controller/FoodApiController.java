package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Food.FoodDataModel;
import legend.example.project_api_legend.DataModel.Food.FoodFilterDataModel;
import legend.example.project_api_legend.Dto.FoodDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.FoodHelper;
import legend.example.project_api_legend.Interface.FoodService;
import legend.example.project_api_legend.Model.LZCinema;
import legend.example.project_api_legend.Model.LZFood;
import legend.example.project_api_legend.Repository.LZCinemaRepository;
import legend.example.project_api_legend.Repository.LZFoodRepository;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@AllArgsConstructor
public class FoodApiController {

    private LZCinemaRepository lzCinemaRepository;
    private LZFoodRepository lzFoodRepository;
    private FoodService foodService;
    
    @PostMapping(FoodHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody FoodFilterDataModel filter) {
       try{
        if(filter.getId()==null)return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The field id is required!"),HttpStatus.BAD_REQUEST);
        Optional<LZCinema> cinema = lzCinemaRepository.findById(filter.getId());
        if(!cinema.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Cinema not found!"),HttpStatus.BAD_REQUEST);
        List<FoodDto> result = foodService.List(filter);
        return new ResponseEntity<>(result,HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping(FoodHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody FoodDataModel model) {
       try{
        if(model.getName()==null || model.getName()=="") return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The Field name is required!"),HttpStatus.BAD_REQUEST);
        if(model.getQty()<1 || model.getQty()==null || model.getPrice() < 1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid,HttpStatus.BAD_REQUEST);
        if(model.getCinemaId()==null || model.getCinemaId()<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The CinemaId is required!"),HttpStatus.BAD_REQUEST);
        Optional<LZCinema> cinema = lzCinemaRepository.findById(model.getCinemaId());
        if(!cinema.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Cinema not found!"),HttpStatus.BAD_REQUEST);
        if(model.getUploadFileDataModel()!=null){
            try{
                UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), FoodHelper.StrText.FolderFood, model.getUploadFileDataModel().getBase64Data());
                model.getUploadFileDataModel().setFolderName(FoodHelper.StrText.FolderFood);
                String fileName = upload.UploadFile(model.getUploadFileDataModel());
                model.setImagePath("/Image/"+FoodHelper.StrText.FolderFood+"/"+fileName);
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        FoodDto data = foodService.Create(model);
        return new ResponseEntity<>(data,HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("Something went wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping(FoodHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody FoodDataModel model) {
       try{
        if(model.getId()==null || model.getId()<1) return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The Field Id is required!"),HttpStatus.BAD_REQUEST);
        if(model.getName()==null || model.getName()=="") return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The Field name is required!"),HttpStatus.BAD_REQUEST);
        if(model.getQty()<1 || model.getQty()==null || model.getPrice() < 1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid,HttpStatus.BAD_REQUEST);
        if(model.getCinemaId()==null || model.getCinemaId()<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The CinemaId is required!"),HttpStatus.BAD_REQUEST);
        Optional<LZCinema> cinema = lzCinemaRepository.findById(model.getCinemaId());
        Optional<LZFood> food = lzFoodRepository.findById(model.getId());
        if(!cinema.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Cinema not found!"),HttpStatus.BAD_REQUEST);
        if(!food.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Food not found!"),HttpStatus.BAD_REQUEST);
        if(model.getUploadFileDataModel()!=null){
            try{
                UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), FoodHelper.StrText.FolderFood, model.getUploadFileDataModel().getBase64Data());
                model.getUploadFileDataModel().setFolderName(FoodHelper.StrText.FolderFood);
                String fileName = upload.UploadFile(model.getUploadFileDataModel());
                model.setImagePath("/Image/"+FoodHelper.StrText.FolderFood+"/"+fileName);
            }catch(Exception ex){
                return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("Upload image fail!"),HttpStatus.BAD_REQUEST);
            }
        }
        FoodDto data = foodService.Update(model);
        return new ResponseEntity<>(data,HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping(FoodHelper.Url.ListJoin)
    public ResponseEntity<?> ListDetail(@RequestBody FoodFilterDataModel filter) {
        return new ResponseEntity<>(foodService.ListDetail(filter),HttpStatus.OK);
    }
    

    @GetMapping(FoodHelper.Url.Delete)
    public ResponseEntity<?> Delete(Long Id) {
       try{
        if(Id<1)return new ResponseEntity<>(LZGlobalHelper.Message.SomethingWentWrong.setDetail("The field id is required!"),HttpStatus.BAD_REQUEST);
        Optional<LZFood> food = lzFoodRepository.findById(Id);
        if(!food.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Food not found!"),HttpStatus.BAD_REQUEST);
        Boolean isDeleteSuccess = foodService.Delete(Id);
        if(isDeleteSuccess) {
            try{
                List<String> fileName = Arrays.asList(food.get().getImagePath().split("/"));
                Integer LastIndex = fileName.size()-1;
                UploadFileData.deleteImage(fileName.get(LastIndex), FoodHelper.StrText.FolderFood);
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(isDeleteSuccess ? "Delete food was successfully!":"",HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    
}
