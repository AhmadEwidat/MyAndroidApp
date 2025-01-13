package Mokup;

import java.util.ArrayList;

public class NotifactionsGarage {
    private String accountt_UserName ;
    private String Model ;
    private  String Image;
    private services_req servicesReqest;



    public NotifactionsGarage(String accountt_UserName, String model, String image,services_req servicesReqest) {
        this.accountt_UserName = accountt_UserName;
        Model = model;
        Image = image;
        this.servicesReqest = servicesReqest;
    }

    public String getAccountt_UserName() {
        return accountt_UserName;
    }

    public void setAccountt_UserName(String accountt_UserName) {
        this.accountt_UserName = accountt_UserName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public services_req getServicesReqest() {
        return servicesReqest;
    }

    public void setServicesReqest(services_req servicesReqest) {
        this.servicesReqest = servicesReqest;
    }

    @Override
    public String toString() {
        return "NotifactionsGarage{" +
                "accountt_UserName='" + accountt_UserName + '\'' +
                ", Model='" + Model + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }

}
