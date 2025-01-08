package Mokup;

import java.util.ArrayList;
import java.util.List;

public class MokupClients {
    private List<clientss> client ;

    public MokupClients () {

        client = new ArrayList<>();
        client.add(new clientss("MoathMouadi","momo123","dsds",05505,"fedf",2323));
        client.add(new clientss("AhmadEwaidat","ahah123","dsds",05505,"fedf",2323));
        client.add(new clientss("Diaa","didi123","dsds",05505,"fedf",2323));
        client.add(new clientss("Osama","didi123","dsds",05505,"fedf",2323));

    }
    public boolean validateClient(String username, String password) {
        for (clientss c : client) {
            if (c.getUserName().equals(username) && c.getPassword().equals(password)) {
                return true; // User is valid
            }
        }
        return false; // User not found
    }
}
