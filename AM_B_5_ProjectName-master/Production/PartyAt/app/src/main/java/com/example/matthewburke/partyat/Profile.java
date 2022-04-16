package com.example.matthewburke.partyat;

import com.google.android.gms.location.places.Place;

/**
 * Created by Aaron on 11/5/2017.
 */

public class Profile {
    String firstName;
    String lastName;
    String userName;
    String password;
    String phoneNumber;
    String emailAddr;
    String location;
    Place place;
    boolean update;

    /*create profile without information*/
    public Profile(){
        this.firstName = "None";
        this.lastName = "None";
        this.userName = "None";
        this.password = "None";
        this.phoneNumber = "None";
        this.emailAddr = "None";
        this.location = "None";
        //this.place = getPlaceById(, this.location);
        this.update = false;
    }

    /*create profile using location ID string*/
    public Profile(String fName, String lName, String uName, String pswd, String pNumber, String email, String loc){
        this.firstName = fName;
        this.lastName = lName;
        this.userName = uName;
        this.password = pswd;
        this.phoneNumber = pNumber;
        this.emailAddr = email;
        this.location = loc;
        //this.place = getPlaceById(, this.location);
        this.update = false;
    }

    /*create profile using place object*/
    public Profile(String fName, String lName, String uName, String pswd, String pNumber, String email, Place place){
        this.firstName = fName;
        this.lastName = lName;
        this.userName = uName;
        this.password = pswd;
        this.phoneNumber = pNumber;
        this.emailAddr = email;
        this.place = place;
        this.location = place.getId();
        this.update = false;
    }

    /* gets */
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmailAddr(){
        return emailAddr;
    }

    public String getLocation(){
        return location;
    }

    public Place getPlace() {
        return place;
    }

    /* sets */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        this.update = true;
    }

    public void setUserName(String userName){
        this.userName = userName;
        this.update = true;
    }

    public boolean setPassword(String oldPswd, String newPswd){
        if (oldPswd.equals(this.password)){
            this.password = newPswd;
            this.update = true;
            return true;
        }
        else return false;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        this.update = true;
    }

    public void setEmailAddr(String emailAddr){
        this.emailAddr = emailAddr;
        this.update = true;
    }

    public void setLocation(String location){
        this.location = location;
        //this.place = getPlaceById(, this.location);
        this.update = true;
    }

    public void setPlace(Place place) {
        this.place = place;
        this.location = place.getId();
        this.update = true;
    }

    public void doUpdate(){
        if(this.update){
            /*format data to send as sql info*/
        }
    }
}
