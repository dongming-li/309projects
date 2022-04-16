package com.example.alex.mainmenu;

/**
 * Englobes all kind of status response we can receive by the server.
 * Used mainly by JSON to parse it more easily
 * Created by johan on 08.10.2017.
 */

public final class StatusResponse {
    private final int status;

    public StatusResponse(int status){
        this.status = status;
    }

    /**
     * In our server 0 is a valid code,
     * any other number means its an error
     * @return true iff status == 0
     */
    public boolean isValid(){
        return status==0;
    }
}
