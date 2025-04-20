package Mokup;

import java.security.PrivilegedAction;
import java.util.PrimitiveIterator;

public class services {
    private int service_id;
    private String UserName ;
    private  String description ;
    private String Time ;
    private  int price ;
    private  String garage_UserName ;
    public services(int service_id, String garage_UserName, String userName) {
        this.service_id = service_id;
        this.garage_UserName = garage_UserName;
        UserName = userName;
    }
    public services(int price, int service_id,String description, String garage_UserName,String UserName) {
        this.price = price;
        this.service_id = service_id;
        this.garage_UserName = garage_UserName;
        this.UserName=UserName;
        this.description=description;
    }
    public services(int price, int service_id, String garage_UserName,String UserName) {
        this.price = price;
        this.service_id = service_id;
        this.garage_UserName = garage_UserName;
        this.UserName=UserName;
    }
    public services(int service_id, String userName, String description, String time, int price, String garage_UserName) {
        this.service_id = service_id;
        UserName = userName;
        this.description = description;
        Time = time;
        this.price = price;
        this.garage_UserName = garage_UserName;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGarage_UserName() {
        return garage_UserName;
    }

    public void setGarage_UserName(String garage_UserName) {
        this.garage_UserName = garage_UserName;
    }

    @Override
    public String toString() {
        return "services{" +
                "service_id=" + service_id +
                ", UserName='" + UserName + '\'' +
                ", description='" + description + '\'' +
                ", Time='" + Time + '\'' +
                ", price=" + price +
                ", garage_UserName='" + garage_UserName + '\'' +
                '}';
    }
}