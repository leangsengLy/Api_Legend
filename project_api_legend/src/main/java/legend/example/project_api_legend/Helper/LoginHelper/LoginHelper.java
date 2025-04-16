package legend.example.project_api_legend.Helper.LoginHelper;

import legend.example.project_api_legend.GlobalHelper.StatusMessage;

public class LoginHelper {
    public static class Url {
         public final static String Test = "api/login/test"; 
         public final static String Create = "api/login/create"; 
         public final static String Login = "api/login/login"; 
         public final static String Update = "api/login/update"; 
    }
    public static class Type{
        public final static String Admin = "Admin";
    }

    public static class Message {
        public static final StatusMessage Invalid = new StatusMessage("error","Invalid Data!",null);
        public static final StatusMessage InvalidUserPassword = new StatusMessage("error","Please input Username or Password!",null);
        public static final StatusMessage EmailAlready = new StatusMessage("error","Email already existed!",null);
    }
}
