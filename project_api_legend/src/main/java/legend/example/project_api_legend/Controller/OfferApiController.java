package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Dto.Media.OfferDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.GlobalHelper.StatusMessage;
import legend.example.project_api_legend.Helper.Food.FoodHelper;
import legend.example.project_api_legend.Helper.Media.OfferHelper;
import legend.example.project_api_legend.Interface.OfferService;
import legend.example.project_api_legend.Repository.OfferRepository;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class OfferApiController {
    private OfferService offerService;
    private OfferRepository offerRepository;
    @PostMapping(OfferHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody OfferFilterDataModel filter) {
        try{
            var list = offerService.List(filter);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(OfferHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody OfferDataModel model) {
        try{
        if(model.getLabel()==null || model.getLabel()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field label is required!"),HttpStatus.BAD_REQUEST);
        if(model.getDetail()==null || model.getDetail()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Detail is required!"),HttpStatus.BAD_REQUEST);
        if(model.getUploadFileDataModel()!=null){
            try{
                UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), OfferHelper.StrText.FolderOffer, model.getUploadFileDataModel().getBase64Data());
                model.getUploadFileDataModel().setFolderName(OfferHelper.StrText.FolderOffer);
                // String fileName = upload.UploadFile(model.getUploadFileDataModel());
                // model.setPathImage("/Image/"+OfferHelper.StrText.FolderOffer+"/"+fileName);
                  StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setPathImage("/Image/"+OfferHelper.StrText.FolderOffer+"/"+fileName.getDetail());
                    }
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        OfferDto data = offerService.Create(model);
        return new ResponseEntity<>(data,HttpStatus.OK);
       }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping(OfferHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody OfferDataModel model) {
        try{
                    if(model.getId()==null )return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field id is required!"),HttpStatus.BAD_REQUEST);
                if(model.getLabel()==null || model.getLabel()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field label is required!"),HttpStatus.BAD_REQUEST);
                if(model.getDetail()==null || model.getDetail()=="") return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Detail is required!"),HttpStatus.BAD_REQUEST);
                var isExisted  = offerRepository.findById(model.getId());
                if(!isExisted.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Offer not found!"),HttpStatus.BAD_REQUEST);
                if(model.getUploadFileDataModel()!=null){
                    try{
                        UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), OfferHelper.StrText.FolderOffer, model.getUploadFileDataModel().getBase64Data());
                        model.getUploadFileDataModel().setFolderName(OfferHelper.StrText.FolderOffer);
                         StatusMessage fileName = upload.UploadFile(model.getUploadFileDataModel());
                    if(fileName.getStatus()!="error"){
                        model.setPathImage("/Image/"+OfferHelper.StrText.FolderOffer+"/"+fileName.getDetail());
                    }
                    }catch(Exception ex){
                        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
                    }
                }
                OfferDto data = offerService.Update(model);
                return new ResponseEntity<>(data,HttpStatus.OK);
           }catch(Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           }
    }
    @GetMapping(OfferHelper.Url.Delete)
    public ResponseEntity<?> Delete(Long Id) {
        try{
            if(Id<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field id is required!"),HttpStatus.BAD_REQUEST);
            var data  = offerRepository.findById(Id);
            if(!data.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Offer not found!"),HttpStatus.BAD_REQUEST);
            var isDeleteSuccess = offerService.Delete(Id);
            if(isDeleteSuccess) {
                try{
                    if(data.get().getPathImage()!=null){
                        List<String> fileName = Arrays.asList(data.get().getPathImage().split("/"));
                        Integer LastIndex = fileName.size()-1;
                        UploadFileData.deleteImage(fileName.get(LastIndex), FoodHelper.StrText.FolderFood);
                    }
                    
                }catch(Exception ex){
                    return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(isDeleteSuccess?"Delete successfuly":"Fail delete offer!", HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
