package legend.example.project_api_legend.MappingData.Address;

import legend.example.project_api_legend.DataModel.Address.Country.CountryDataModel;
import legend.example.project_api_legend.Dto.Address.CountryDto;
import legend.example.project_api_legend.Model.Address.LZCountry;

public class CountryDataMapping {
    public static CountryDto MappingToDto (LZCountry model,int recordCount){
        var data = new CountryDto();
        data.setCode(model.getCode());
        data.setId(model.getId());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setCreateBy(model.getCreatedBy());
        data.setUpdateBy(model.getUpdatedBy());
        data.setUpdateDate(model.getUpdatedDate());
        data.setRecordCount(recordCount);
        data.setCreateDate(model.getCreatedDate());
        data.setDatabase(model.getDatabase());
        data.setImagePath(model.getImagePath());
        return data;
    }
    public static LZCountry MappingToTable (CountryDataModel model){
        var data = new LZCountry();
        data.setCode(model.getCode());
        data.setId(model.getId());
        data.setName(model.getName());
        data.setImagePath(model.getImagePath());
        data.setEnglishName(model.getEnglishName());
        data.setCreatedBy(model.getCreateBy());
        data.setUpdatedBy(model.getUpdateBy());
        data.setUpdatedDate(model.getUpdateDate());
        data.setCreatedDate(model.getCreateDate());
        data.setDatabase(model.getDatabase());
        return data;
    }

}
