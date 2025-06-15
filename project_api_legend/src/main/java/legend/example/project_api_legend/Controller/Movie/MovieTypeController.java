package legend.example.project_api_legend.Controller.Movie;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeDataModel;
import legend.example.project_api_legend.DataModel.Movie.MovieType.MovieTypeFilterDataMode;
import legend.example.project_api_legend.Dto.Setting.MovieTypeDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.Setting.MovieTypeHelper;
import legend.example.project_api_legend.Interface.MovieTypeService;
import legend.example.project_api_legend.Repository.LZMovieTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@AllArgsConstructor
public class MovieTypeController {
    MovieTypeService movieTypeService;
    LZMovieTypeRepository lzMovieTypeRepository;
    @PostMapping(MovieTypeHelper.URL.List)
    public ResponseEntity<?> List(@RequestBody MovieTypeFilterDataMode filter) {
        try{
           var list = movieTypeService.List(filter);
            return  new ResponseEntity<>(list,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(MovieTypeHelper.URL.Create)
    public ResponseEntity<?> Create(@RequestBody MovieTypeDataModel model) {
        try{
            if(model.getName()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Field name is required!"),HttpStatus.BAD_REQUEST);
            MovieTypeDto data = movieTypeService.Create(model);
            return  new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(MovieTypeHelper.URL.Update)
    public ResponseEntity<?> Update(@RequestBody MovieTypeDataModel model) {
        try{
            if(model.getName()==null) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Name is required!"),HttpStatus.BAD_REQUEST);
            if(model.getId()==null || model.getId()<1) return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("The field Id is required!"),HttpStatus.BAD_REQUEST);
            var find = lzMovieTypeRepository.findById(model.getId());
            if(!find.isPresent())  return new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie type not found!"),HttpStatus.NOT_FOUND);
            MovieTypeDto data = movieTypeService.Update(model);
            return  new ResponseEntity<>(data,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(MovieTypeHelper.URL.Delete)
    public ResponseEntity<?> Delete(long Id) {
        try{
            if(Id<1) return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Please input id!"),HttpStatus.BAD_REQUEST);
            var find = lzMovieTypeRepository.findById(Id);
            if(!find.isPresent())return  new ResponseEntity<>(LZGlobalHelper.Message.DataInvalid.setDetail("Movie Type not found!"),HttpStatus.NOT_FOUND);
            movieTypeService.Delete(Id);
            return  new ResponseEntity<>(true,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
