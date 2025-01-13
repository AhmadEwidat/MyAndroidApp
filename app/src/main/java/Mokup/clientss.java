package Mokup;



import java.util.ArrayList;

public class clientss extends  accountt{

    private  String FullName ;
   private  ArrayList <cars>arrayList ;

    public ArrayList<cars> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<cars> arrayList) {
        this.arrayList = arrayList;
    }

    public clientss() {
        super();
    }

    public clientss(String UserName, String password, String location, int phone, String fullName) {
        super(UserName, password, location, phone);
        FullName = fullName;
        arrayList=new ArrayList<cars>();




    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }


    @Override
    public String toString() {
        return "clientss{" +
                "FullName='" + FullName + '\'' +
                ", IdNum=" +
                '}';
    }

}

