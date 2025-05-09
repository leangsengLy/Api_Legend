package legend.example.project_api_legend.Controller.Movie;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieDataModel;
import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.MovieHelper;
import legend.example.project_api_legend.Interface.MovieService;
import legend.example.project_api_legend.Repository.LZMovieRepository;
import legend.example.project_api_legend.Repository.LZMovieTypeRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;


@RestController
@AllArgsConstructor
public class MovieApiController {
    private LZMovieTypeRepository lzMovieTypeRepository;
    private LZMovieRepository lzMovieRepository;
    private MovieService movieService;
    @PostMapping(MovieHelper.URL.List)
    public ResponseEntity<?> List(@RequestBody MovieFilterDataModel filter) {
        try{
            var list = movieService.List(filter);
            return  new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(MovieHelper.URL.Create)
    public ResponseEntity<?> Create(@RequestBody MovieDataModel model) {
        try{
            List<String> message = new ArrayList<>();
            if(model.getMovieTypeId()==null) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field MoveTypeId is required!"),HttpStatus.BAD_REQUEST);
            if(model.getName()==null || model.getName()=="") message.add("Name");
            if(model.getDuration()==null || model.getDuration()<1) message.add("Duration");
            if(model.getFromDate()==null) message.add("From Date");
            if(model.getToDate()==null) message.add("To Date");
            if(message.size()>0){
                return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail(String.join(",", message)),HttpStatus.BAD_REQUEST);
            }
            if(model.getToDate().getTime() < model.getFromDate().getTime()){
                return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("To date should be smaller than from date!"),HttpStatus.BAD_REQUEST);
            }
            var findMovieType = lzMovieTypeRepository.findById(model.getMovieTypeId());
            if(!findMovieType.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie type not found!"),HttpStatus.BAD_REQUEST);
            if(model.getUploadFileDataModel()!=null){
                try{
                    UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), MovieHelper.Text.folderMovie, model.getUploadFileDataModel().getBase64Data());
                   model.getUploadFileDataModel().setFolderName(MovieHelper.Text.folderMovie);
                    String fileName = upload.UploadFile(model.getUploadFileDataModel());
                    model.setImagePath("/Image/"+MovieHelper.Text.folderMovie+"/"+fileName);
                }catch(Exception ex){
                    return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
                }
            }
            var list = movieService.Create(model);
            return  new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(MovieHelper.URL.Update)
    public ResponseEntity<?> Update(@RequestBody MovieDataModel model) {
        try{
            List<String> message = new ArrayList<>();
            if(model.getId()==null) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Id is required!"),HttpStatus.BAD_REQUEST);
            if(model.getMovieTypeId()==null) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field MoveTypeId is required!"),HttpStatus.BAD_REQUEST);
            if(model.getName()==null || model.getName()=="") message.add("Name");
            if(model.getDuration()==null || model.getDuration()<1) message.add("Duration");
            if(model.getFromDate()==null) message.add("From Date");
            if(model.getToDate()==null) message.add("To Date");
            if(message.size()>0){
                return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail(String.join(",", message)),HttpStatus.BAD_REQUEST);
            }
            if(model.getToDate().getTime() < model.getFromDate().getTime()){
                return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("To date should be smaller than from date!"),HttpStatus.BAD_REQUEST);
            }
            var findMovieType = lzMovieTypeRepository.findById(model.getMovieTypeId());
            if(!findMovieType.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie type not found!"),HttpStatus.NOT_FOUND);
            var findMovie = lzMovieRepository.findById(model.getId());
            if(!findMovie.isPresent())return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie not found!"),HttpStatus.NOT_FOUND);
            if(model.getUploadFileDataModel()!=null ){
                if(findMovie.get().getImagePath() ==null || findMovie.get().getImagePath() ==""){
                    try{
                        UploadFileData upload = new UploadFileData(model.getUploadFileDataModel().getFileName(), model.getUploadFileDataModel().getFileType(), MovieHelper.Text.folderMovie, model.getUploadFileDataModel().getBase64Data());
                       model.getUploadFileDataModel().setFolderName(MovieHelper.Text.folderMovie);
                        String fileName = upload.UploadFile(model.getUploadFileDataModel());
                        model.setImagePath("/Image/"+MovieHelper.Text.folderMovie+"/"+fileName);
                    }catch(Exception ex){
                        return  new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
                    }
                }else{
                    return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Image already existed!"),HttpStatus.INTERNAL_SERVER_ERROR);
                }
                
            }else { 
                model.setImagePath(findMovie.get().getImagePath());
            }

            var list = movieService.Update(model);
            return  new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(MovieHelper.URL.Delete)
    public ResponseEntity<?> Delete(Long Id) {
        try{
            if(Id<1) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Id is required!"),HttpStatus.INTERNAL_SERVER_ERROR);
            var findMovie = lzMovieRepository.findById(Id);
            if(!findMovie.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie not found!"),HttpStatus.NOT_FOUND);
            var isDeleteSuccess = movieService.Delete(Id);
            return new ResponseEntity<>(isDeleteSuccess,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(MovieHelper.URL.RemoveImage)
    public ResponseEntity<?> RemoveImage(Long Id) {
        try{
            if(Id<1) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The Field Id is required!"),HttpStatus.INTERNAL_SERVER_ERROR);
            var findMovie = lzMovieRepository.findById(Id);
            var isSuccess = false;
            if(!findMovie.isPresent()) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie not found!"),HttpStatus.NOT_FOUND);
            if(findMovie.get().getImagePath()!=null){
                movieService.RemoveImage(Id);
                isSuccess = true;
            }
            return new ResponseEntity<>(!isSuccess?LZGlobalHelper.Message.DataInvalid.setDetail("currently you didn't have image!"):"remove success",HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
