package com.iao.users.event;

public class EventData {

    // Constructor
    public EventData(String email, String trainingName) {
        this.email = email;
        this.trainingName = trainingName;
    }
    public String email;

    public String trainingName;


    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for trainingName
    public String getTrainingName() {
        return trainingName;
    }

    // Setter for trainingName
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
}
