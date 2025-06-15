package legend.example.project_api_legend.MappingData.Address;

import legend.example.project_api_legend.DataModel.Address.Province.ProvinceDataModel;
import legend.example.project_api_legend.Dto.Address.ProvinceDto;
import legend.example.project_api_legend.Model.Address.LZProvince;

public class ProvinceDataMapping {
    public static ProvinceDto MappingToDto (LZProvince model,int recordCount){
        var data = new ProvinceDto();
        data.setId(model.getId());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setCreateBy(model.getCreatedBy());
        data.setUpdateBy(model.getUpdatedBy());
        data.setUpdateDate(model.getUpdatedDate());
        data.setRecordCount(recordCount);
        data.setCreateDate(model.getCreatedDate());
        data.setDatabase(model.getDatabase());
        return data;
    }
    public static LZProvince MappingToTable (ProvinceDataModel model){
        var data = new LZProvince();
        data.setId(model.getId());
        data.setName(model.getName());
        data.setEnglishName(model.getEnglishName());
        data.setCreatedBy(model.getCreateBy());
        data.setUpdatedBy(model.getUpdateBy());
        data.setUpdatedDate(model.getUpdateDate());
        data.setCreatedDate(model.getCreateDate());
        data.setDatabase(model.getDatabase());
        return data;
    }

}
