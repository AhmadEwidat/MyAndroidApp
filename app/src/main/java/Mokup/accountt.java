package Mokup;

public class accountt {

    private String UserName ;
    private String Password ;
    private  String location ;
    private int Phone ;

    public accountt(String UserName, int Phone,String location) {
        this.UserName = UserName;
        this.Phone = Phone;
        this.location=location;

    }

    public accountt(String UserName, String password, String location, int phone) {
        this.UserName = UserName;
        this.Password = password;
        this.location = location;
        this.Phone = phone;
    }

    public accountt() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "accountt{" +
                "UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", location='" + location + '\'' +
                ", Phone=" + Phone +
                '}';
    }
}