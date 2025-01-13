package Mokup;
import java.util.ArrayList;

public class services_req {
    private int service_req ;
    private String status ;
    private String startTime;
    private String endTime;
    private cars car;
    private String garagee_UserName;
    private  accountt accountt_UserName ;
    private ArrayList <services> arrayList=new ArrayList<>();

    public services_req() {

    }
    public services_req(int service_req, String startTime, String status, String endTime, ArrayList<services> arrayList, accountt accountt_UserName, String garagee_UserName,cars car) {
        this.service_req = service_req;
        this.startTime = startTime;
        this.status = status;
        this.endTime = endTime;
        this.arrayList = arrayList;
        this.accountt_UserName = accountt_UserName;
        this.garagee_UserName = garagee_UserName;
        this.car=car;

    }
    public services_req(int service_req, String startTime, String status, String endTime, cars car,String garagee_UserName) {
        this.service_req = service_req;
        this.startTime = startTime;
        this.status = status;
        this.endTime = endTime;
        this.car = car;
        this.garagee_UserName = garagee_UserName;
    }

    public int getService_req() {
        return service_req;
    }

    public void setService_req(int service_req) {
        this.service_req = service_req;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public cars getCar() {
        return car;
    }

    public void setCar(cars car) {
        this.car = car;
    }

    public String getGaragee_UserName() {
        return garagee_UserName;
    }

    public void setGaragee_UserName(String garagee_UserName) {
        this.garagee_UserName = garagee_UserName;
    }

    public ArrayList<services> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<services> arrayList) {
        this.arrayList = arrayList;
    }

    public accountt getAccountt_UserName() {
        return accountt_UserName;
    }

    public void setAccountt_UserName(accountt accountt_UserName) {
        this.accountt_UserName = accountt_UserName;
    }

    @Override
    public String toString() {
        return "services_req{" +
                "service_req=" + service_req +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", Car_id='" + car.getId() + '\'' +
                ", garagee_UserName='" + garagee_UserName + '\'' +
                '}';
    }

}
