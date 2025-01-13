package Mokup;

import java.security.PrivateKey;
import java.util.ArrayList;

public class cars {
    private  String Id ;
    private String Image;
    private  String Model ;
    private  accountt accountt_UserName ;
    private ArrayList<services>arrayList=new ArrayList<>();

    public cars() {

    }

    public cars(String id, String image, String model, accountt accountt_UserName) {
        Id = id;
        Image = image;
        Model = model;
        this.accountt_UserName = accountt_UserName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public accountt getAccountt_UserName() {
        return accountt_UserName;
    }

    public void setAccountt_UserName(accountt accountt_UserName) {
        this.accountt_UserName = accountt_UserName;
    }

    public ArrayList<services> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<services> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "cars{" +
                "Id='" + Id + '\'' +
                ", Image='" + Image + '\'' +
                ", Model='" + Model + '\'' +
                ", accountt_UserName='" + accountt_UserName + '\'' +
                '}';
    }

}
