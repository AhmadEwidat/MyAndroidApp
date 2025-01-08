package Mokup;

import java.util.ArrayList;
import java.util.List;

public class MokupGarages {
    private List<garagee> garages;

    public MokupGarages() {
        garages = new ArrayList<>();
        garages.add(new garagee("garage", "garage123", "Location1", 123456, "Garage One"));
        garages.add(new garagee("Garage2", "garage456", "Location2", 789012, "Garage Two"));
        garages.add(new garagee("Garage3", "garage789", "Location3", 345678, "Garage Three"));
    }

    public boolean validateGarage(String username, String password) {
        for (garagee g : garages) {
            if (g.getUserName().equals(username) && g.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
