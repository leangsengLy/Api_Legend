package legend.example.project_api_legend.Controller.Movie;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Movie.Movie.MovieFilterDataModel;
import legend.example.project_api_legend.Helper.MovieHelper;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class MovieApiController {
    @PostMapping(MovieHelper.URL.List)
    public ResponseEntity<?> List(@RequestBody MovieFilterDataModel filter) {
        try{
            return  new ResponseEntity<>(true,HttpStatus.OK);
        }catch(Exception ex){
            return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
