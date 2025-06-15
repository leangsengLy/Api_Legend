package legend.example.project_api_legend.MappingData.Address;

import legend.example.project_api_legend.DataModel.Address.Commune.CommuneDataModel;
import legend.example.project_api_legend.Dto.Address.CommuneDto;
import legend.example.project_api_legend.Model.Address.LZCommune;

public class CommuneDataMapping {
    public static CommuneDto MappingToDto (LZCommune model,int recordCount){
        var data = new CommuneDto();
        data.setId(model.getId());
        data.setPronvinceId(model.getDistrictId());
        data.setCountryId(model.getCountryId());
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
    public static LZCommune MappingToTable (CommuneDataModel model){
        var data = new LZCommune();
        data.setId(model.getId());
        data.setDistrictId(model.getDistrictId());
        data.setCountryId(model.getCountryId());
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
