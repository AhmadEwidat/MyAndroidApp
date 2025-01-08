package Mokup;

public class clientss extends  accountt{

    private  String FullName ;
    private  int IdNum ;

    public clientss() {
        super();
    }

    public clientss(String UserName, String password, String location, int phone, String fullName, int idNum) {
        super(UserName, password, location, phone);
        FullName = fullName;
        IdNum = idNum;


    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getIdNum() {
        return IdNum;
    }

    public void setIdNum(int idNum) {
        IdNum = idNum;
    }

    @Override
    public String toString() {
        return "clientss{" +
                "FullName='" + FullName + '\'' +
                ", IdNum=" + IdNum +
                '}';
    }

}

