package Mokup;

import java.util.Date;
import java.util.PrimitiveIterator;

public class Report {
    private String NameOfGarage;
    private String Image;
    private String Model;
    private String Id;
    private String UserName;
    private String price;
    private String description;
    private String status;
    private String startTime;
    private String endTime;


    public Report(String nameOfGarage, String image, String model, String id, String userName, String price, String description, String status, String endTime, String startTime) {
        NameOfGarage = nameOfGarage;
        Image = image;
        Model = model;
        Id = id;
        UserName = userName;
        this.price = price;
        this.description = description;
        this.status = status;
        this.endTime = endTime;
        this.startTime = startTime;
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

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "NameOfGarage='" + NameOfGarage + '\'' +
                ", Image='" + Image + '\'' +
                ", Model='" + Model + '\'' +
                ", Id='" + Id + '\'' +
                ", UserName='" + UserName + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}