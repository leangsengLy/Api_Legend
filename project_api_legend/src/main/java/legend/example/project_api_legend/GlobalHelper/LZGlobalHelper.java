package legend.example.project_api_legend.GlobalHelper;

import java.util.Date;

public class LZGlobalHelper {
    public static class Text {
           public final static String GlobalDatabase = "LZ";
           public final static String Admin = "LZee Admin";
           public final static Date DateNow = new Date();
           public final static String pathFolderImage = "D:\\Ly Zee\\Api_Legend\\project_api_legend\\src\\main\\resources\\static\\Image\\";
           public final static String localUrl="http://localhost:8080";
           public final static String localUrl_flutter="http://10.0.2.2:8080";
           public final static String localUrl_realDevice="http://192.168.1.5:8080";
    }
    public static class LZDate {
        public final static Date DateNow = new Date();
 }
    
    public static class Status {
        public final static boolean active = true;
        public final static boolean disabled = false;
    }
    public static class Message{
        public static StatusMessage DataInvalid = new StatusMessage("error", "Data was invalid!", null);
        public static StatusMessage SomethingWentWrong = new StatusMessage("error", "Something went wrong!", null);
    }
}
