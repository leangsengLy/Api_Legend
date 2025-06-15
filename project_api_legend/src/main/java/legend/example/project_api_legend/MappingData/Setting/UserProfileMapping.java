package legend.example.project_api_legend.MappingData.Setting;

import legend.example.project_api_legend.Dto.Setting.UserProfileDto;
import legend.example.project_api_legend.Model.LZUserProfile;

public class UserProfileMapping {
    public static LZUserProfile MappingData(LZUserProfile model) {
        LZUserProfile data = new LZUserProfile();
        data.setID(model.getID());
        data.setLOGIN_ID(model.getLOGIN_ID());
        data.setPROFILE_IMG_PATH(model.getPROFILE_IMG_PATH());
        data.setCV_IMG_PATH(model.getCV_IMG_PATH());
        data.setNAME(model.getNAME());
        data.setEN_NAME(model.getEN_NAME());
        data.setDESCRIPTION(model.getDESCRIPTION());
        data.setPHONE1(model.getPHONE1());
        data.setMAJOR(model.getMAJOR());
        data.setEXPERIENCE_DESC(model.getEXPERIENCE_DESC());
        data.setADDRESS(model.getADDRESS());
        data.setCREATED_BY(model.getCREATED_BY());
        data.setCREATED_DATE(model.getCREATED_DATE());
        data.setUPDATED_BY(model.getUPDATED_BY());
        data.setUPDATED_DATE(model.getUPDATED_DATE());
        data.setDATABASE(model.getDATABASE());
        return data;
    }
    public static UserProfileDto MappingDto(LZUserProfile data,int recordCount) {
        var res = new UserProfileDto();
        res.setId(data.getID());
        res.setLoginId(data.getLOGIN_ID());
        res.setProfileImagePath(data.getPROFILE_IMG_PATH());
        res.setCoverImagePath(data.getCV_IMG_PATH());
        res.setName(data.getNAME());
        res.setEnglishName(data.getEN_NAME());
        res.setDescription(data.getDESCRIPTION());
        res.setPhone1(data.getPHONE1());
        res.setMajor(data.getMAJOR());
        res.setExperienceDescription(data.getEXPERIENCE_DESC());
        res.setAddress(data.getADDRESS());
        res.setCreatedBy(data.getCREATED_BY());
        res.setUpdatedBy(data.getUPDATED_BY());
        res.setRecordCount(recordCount);
        res.setCreatedDate(data.getCREATED_DATE());
        res.setUpdatedDate(data.getUPDATED_DATE());
        res.setDatabase(data.getDATABASE());
        res.setCreatedBy(data.getCREATED_BY());
        res.setUpdatedBy(data.getUPDATED_BY());
        res.setDatabase(data.getDATABASE());
        return res;
    }
}
