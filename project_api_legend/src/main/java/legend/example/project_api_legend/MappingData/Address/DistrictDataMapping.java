package legend.example.project_api_legend.MappingData.Address;

import legend.example.project_api_legend.DataModel.Address.District.DistrictDataModel;
import legend.example.project_api_legend.Dto.Address.DistrictDto;
import legend.example.project_api_legend.Model.Address.LZDistrict;

public class DistrictDataMapping {
    public static DistrictDto MappingToDto (LZDistrict model,int recordCount){
        var data = new DistrictDto();
        data.setCode(model.getCode());
        data.setId(model.getId());
        data.setCountryId(model.getCountryId());
        data.setProvinceId(model.getProvinceId());
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
    public static LZDistrict MappingToTable (DistrictDataModel model){
        var data = new LZDistrict();
        data.setCode(model.getCode());
        data.setId(model.getId());
        data.setCountryId(model.getCountryId());
        data.setProvinceId(model.getProvinceId());
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
