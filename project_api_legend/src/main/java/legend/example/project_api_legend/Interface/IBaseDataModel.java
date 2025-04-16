package legend.example.project_api_legend.Interface;


import java.util.Date;

import legend.example.project_api_legend.GlobalHelper.LZGlobalHelper;

public interface IBaseDataModel {
    String Username = LZGlobalHelper.Text.Admin;
    String Database=LZGlobalHelper.Text.GlobalDatabase;
    Date DateNow = new Date();
} 
