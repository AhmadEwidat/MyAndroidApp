package Mokup;

public class notifications {
    private String message;
    private String date;
    private String userType;
    private int notificationId;
    private int serviceId;
    private String title;
    private String accountt_UserName;
    private String garagee_UserName ;


    public notifications() {
    }

    public notifications(String message, String date, String userType, int notificationId, int serviceId, String accountt_UserName, String title, String garagee_UserName) {
        this.message = message;
        this.date = date;
        this.userType = userType;
        this.notificationId = notificationId;
        this.serviceId = serviceId;
        this.accountt_UserName = accountt_UserName;
        this.title = title;
        this.garagee_UserName = garagee_UserName;
    }

    public String getGaragee_UserName() {
        return garagee_UserName;
    }

    public void setGaragee_UserName(String garagee_UserName) {
        this.garagee_UserName = garagee_UserName;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getUserType() {
        return userType;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getTitle() {
        return title;
    }

    public String getAccountt_UserName() {
        return accountt_UserName;
    }
}