package Mokup;

import java.security.Provider;
import java.util.ArrayList;

public class garagee extends  accountt {
     private String NameOfGarage ;
     ArrayList <services> Service = new ArrayList();

    public garagee(String userName, String password, String location, int phone, String nameOfGarage) {
        super(userName, password, location, phone);
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

    @Override
    public String toString() {
        return "garagee{" +
                "NameOfGarage='" + NameOfGarage + '\'' +
                '}';
    }

}
