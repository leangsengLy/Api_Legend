package legend.example.project_api_legend.Implement;


import java.util.Optional;

import org.springframework.stereotype.Service;

import legend.example.project_api_legend.Data.UploadFileData;
import legend.example.project_api_legend.DataModel.Cinema.CinemaDataModel;
import legend.example.project_api_legend.DataModel.Cinema.CinemaFilterDataModel;
import legend.example.project_api_legend.Dto.CinemaDto;
import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;
import legend.example.project_api_legend.Helper.LoginHelper.CinemaHelper;
import legend.example.project_api_legend.Interface.CinemaService;
import legend.example.project_api_legend.Model.LZCinema;
import legend.example.project_api_legend.Repository.LZCinemaRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CinemaImplement implements CinemaService  {
    private LZCinemaRepository lzCinemaRepository;
    @Override
    public CinemaDto Create(CinemaDataModel model) {
        model.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        model.setCreateby(LZGlobalHelper.Text.Admin);
        model.setLocalhost(LZGlobalHelper.Text.localUrl);
        model.setCreateDate(LZGlobalHelper.LZDate.DateNow);
        LZCinema cinema = lzCinemaRepository.save(MapToTable(model));
        return MappingData(cinema,1L);
    }
    @Override
    public java.util.List<CinemaDto> List(CinemaFilterDataModel filter) {
        return null;
    }

    @Override
    public Boolean CheckCode(String code){
        Optional<LZCinema> checkCode = lzCinemaRepository.findByCode(code);
        return checkCode.isPresent();
    }
    @Override
    public Boolean DeleteImage(Long Id){
        LZCinema find = lzCinemaRepository.findById(Id).get();
        find.setPathImage(null);
        find.setLocalhost(null);
        UploadFileData.deleteImage("TestImage.jpg", CinemaHelper.StrText.FolderBranch);
        lzCinemaRepository.save(find);
        return true;
    }

    @Override
    public CinemaDto Update(CinemaDataModel model) {
        LZCinema cinema = lzCinemaRepository.findById(model.getId()).get();
        cinema.setAddress(model.getAddress());
        cinema.setName(model.getName());
        cinema.setEnName(model.getEnglishName());
        cinema.setStartTime(model.getStartTime());
        cinema.setEndTime(model.getEndTime());
        cinema.setPathImage(model.getPathImage());
        cinema.setLatMap(model.getLatMap());
        cinema.setLongMap(model.getLongMap());
        cinema.setStartTime(model.getStartTime());
        cinema.setDatabase(LZGlobalHelper.Text.GlobalDatabase);
        cinema.setUpdateBy(LZGlobalHelper.Text.Admin);
        cinema.setUpdateDate(LZGlobalHelper.LZDate.DateNow);
        lzCinemaRepository.save(cinema);
        return MappingData(cinema,1L);
    }

    public static CinemaDto MappingData(LZCinema data,Long recordCount){
        return new CinemaDto(
            data.getId(),
             data.getName(),
             data.getCode(),
             data.getLocalhost(),
              data.getEnName(),
               data.getPathImage(),
                data.getAddress(),
                 data.getStartTime(),
                  data.getEndTime(),
                   data.getLatMap(),
                    data.getLongMap(),
                    recordCount,
                      data.getDatabase(),
                       data.getCreateBy(),
                        data.getUpdateBy(),
                         data.getUpdateDate(),
                          data.getCreateDate()
                          );
    }
    public static LZCinema MapToTable(CinemaDataModel data){
        return new LZCinema(data.getId(), data.getName(), data.getEnglishName(),data.getCode(),data.getPathImage(),data.getLocalhost(), data.getAddress(), data.getStartTime(), data.getEndTime(), data.getLatMap(), data.getLongMap(), data.getDatabase(), data.getCreateby(), data.getUpdateBy(), data.getCreateDate(), data.getUpdateDate());
    }
}
