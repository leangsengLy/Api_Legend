package legend.example.project_api_legend.Controller;

import org.springframework.web.bind.annotation.RestController;

import legend.example.project_api_legend.DataModel.Offer.OfferDataModel;
import legend.example.project_api_legend.DataModel.Offer.OfferFilterDataModel;
import legend.example.project_api_legend.Helper.OfferHelper;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class OfferApiController {
    @PostMapping(OfferHelper.Url.List)
    public ResponseEntity<?> List(@RequestBody OfferFilterDataModel filter) {
        try{
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(OfferHelper.Url.Create)
    public ResponseEntity<?> Create(@RequestBody OfferDataModel model) {
        try{
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(OfferHelper.Url.Update)
    public ResponseEntity<?> Update(@RequestBody OfferDataModel model) {
        try{
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(OfferHelper.Url.Delete)
    public ResponseEntity<?> Delete(Long Id) {
        try{
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
