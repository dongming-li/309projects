package com.warscreen.warscreen;

/**
 * Created by krottink on 10/1/2017.
 */

import com.warscreen.warscreen.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Tutorial {
    private String website;
    private String topics;
    private String facebook;
    private String twitter;
    private String pinterest;
    private String youtube;
    private String message;

    public Tutorial(JSONObject object) {
        parseJsonObject(object);
    }

    private void parseJsonObject(JSONObject jsonObject) {
        try {
            website = jsonObject.getString(Constants.KEY_WEBSITE);
            topics = jsonObject.getString(Constants.KEY_TOPICS);
            facebook = jsonObject.getString(Constants.KEY_FACEBOOK);
            twitter = jsonObject.getString(Constants.KEY_TWITTER);
            pinterest = jsonObject.getString(Constants.KEY_PINTEREST);
            youtube = jsonObject.getString(Constants.KEY_YOUTUBE);
            message = jsonObject.getString(Constants.KEY_MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getWebsite() {
        return website;
    }

    public String getTopics() {
        return topics;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getPinterest() {
        return pinterest;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getMessage() {
        return message;
    }
}
