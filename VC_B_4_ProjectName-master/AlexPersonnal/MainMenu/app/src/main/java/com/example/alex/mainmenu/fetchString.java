package com.example.alex.mainmenu;

/**
 * Created by Alex on 10/7/2017.
 */

public class fetchString {

    String string;
    private boolean ready;

    public fetchString(String string){
        this.ready = false;
        this.string = string;

    }


    public void setString (String string1){
        string = string1;
        this.ready=true;

    }

    /**
     * Add idle waiting. In the case the string has not bee
     * @return
     */
    public String getString(){
        while(!ready){};
        return string;
    }




}
