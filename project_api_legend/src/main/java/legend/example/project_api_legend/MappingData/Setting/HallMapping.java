package legend.example.project_api_legend.MappingData.Setting;

import legend.example.project_api_legend.DataModel.Hall.HallDataModel;
import legend.example.project_api_legend.Dto.Setting.HallDto;
import legend.example.project_api_legend.Model.LZHall;

public class HallMapping {
    public static LZHall MappToTable(HallDataModel model){
        var data = new LZHall();
        data.setCODE(model.getCode());
        data.setNAME(model.getName());
        data.setEN_NAME(model.getEnglishName());
        data.setIS_ACTIVE(model.getIsActive());
        data.setTOTAL_CHAIRS(model.getTotalChairs());
        data.setID(model.getId());
        data.setCREATE_BY(model.getCreateBy());
        data.setCREATE_DATE(model.getCreateDate());
        data.setUPDATE_BY(model.getUpdateBy());
        data.setUPDATE_DATE(model.getUpdateDate());
        data.setDATABASE(model.getDatabase());
        return data;
    }

    public static HallDto MapToDto(LZHall model, int totalRecords) {
        var data = new HallDto();
        data.setId(model.getID());
        data.setCode(model.getCODE());
        data.setName(model.getNAME());
        data.setEnglishName(model.getEN_NAME());
        data.setIsActive(model.getIS_ACTIVE());
        data.setTotalChairs(model.getTOTAL_CHAIRS());
        data.setCreateBy(model.getCREATE_BY());
        data.setCreateDate(model.getCREATE_DATE());
        data.setUpdateBy(model.getUPDATE_BY());
        data.setRecordCount(totalRecords);
        data.setUpdateDate(model.getUPDATE_DATE());
        data.setDatabase(model.getDATABASE());
        return data;
    }
}
