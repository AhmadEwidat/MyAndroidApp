package Mokup;

import java.security.Provider;
import java.util.ArrayList;

public class garagee extends  accountt {
     private String NameOfGarage ;
     private  String  Image;
     ArrayList <services> Service = new ArrayList();

    public garagee(String userName,String Image, String password, String location, int phone, String nameOfGarage) {
        super(userName, password, location, phone);
        NameOfGarage = nameOfGarage;
        Image = Image ;
    }

    public garagee(String UserName, String password, String location, int phone, String nameOfGarage) {
        super(UserName, password, location, phone);
        NameOfGarage = nameOfGarage;
    }

    public  garagee () {
        super();
    }

    public String getNameOfGarage() {
        return NameOfGarage;
    }

    public void setNameOfGarage(String nameOfGarage) {
        NameOfGarage = nameOfGarage;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "garagee{" +
                "NameOfGarage='" + NameOfGarage + '\'' +
                ", Image='" + Image + '\'' +
                ", Service=" + Service +
                '}';
    }
}
