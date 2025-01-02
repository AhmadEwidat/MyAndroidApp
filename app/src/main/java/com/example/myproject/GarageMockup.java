package com.example.myproject;

public class GarageMockup {
        private String garageName;
        private String location;
        private String personalName;
        private int logoImageResource;

        // Constructor
        public GarageMockup(String garageName, String location, String personalName, int logoImageResource) {
            this.garageName = garageName;
            this.location = location;
            this.personalName = personalName;
            this.logoImageResource = logoImageResource;
        }
    public static final GarageMockup[] gar = {
            new GarageMockup("Volvo", "Done", "16/2/2025", R.drawable.logogar),
            new GarageMockup("Mercedes", " Done", "17/2/2025", R.drawable.logoga),
            new GarageMockup("BMW", " Not finish", "19/2/2025", R.drawable.logog),
            new GarageMockup("Toyota", "finish ", "15/5/2025", R.drawable.carageimage),
            new GarageMockup("Kia", " finish ", "15/7/2025", R.drawable.kia),
    };

        // Getters
        public String getGarageName() {
            return garageName;
        }

        public String getLocation() {
            return location;
        }

        public String getPersonalName() {
            return personalName;
        }

        public int getLogoImageResource() {
            return logoImageResource;
        }
    }


