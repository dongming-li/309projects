
/**
 * @file script.js
 * @fileOverview Opens chat, navbar, lets users send messages
 * @author Huiye Lin, Ryan Evans
 * @version 1.1
 * 
 * @module AjaxDBCalls
 */

/**
* @function ajax
* @param {String} type type of message to send database
* @description checks or creates username and password into database tables
*/
function ajax(type) {

    var xhttp;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var newpassword = document.getElementById("newpassword").value;

    if (username.length == 0 || password.length == 0) {
        window.alert("empty username or password");
        return;
    } else {
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("message").innerHTML = this.responseText;
                if (type === "showStat") {
                    document.getElementById("showstats").innerHTML = this.responseText;
                }
                var mes = document.getElementById("message").innerText;
                if (mes == "login...") {

                    startSocketConnection();

                    storeUsername(username);

                    loged("set", username, password);
                    window.location.hash = "/landing";

                }
                if (type === "create" || type === "delete"
                    || type === "update") {
                    window.alert(mes);
                }

                if (mes == "wrong username or password") {
                    alert("wrong username or password");
                }

            }
        };
        if (type === "check" || type === "create" || type === "delete"
            || type === "updateWin" || type === "updateLose" || type === "showStat") {

            xhttp.open("GET", "User.php?t=" + type + "&u=" + username + "&p=" + password, true);
        } else if (type === "update") {
            xhttp.open("GET", "User.php?t=" + type + "&u=" + username + "&p=" + password
                + "&n=" + newpassword, true);
        }

        xhttp.send();
    }
}
/**
* @function loged
* @param {String} type type of message to send database
* @param {String} username the username input on login
* @param {String} password the password input on login
* @description checks or creates username and password into database tables
*/
function loged(type, username, password) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();

    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {



        }
    };
    if (type === "set") {
        xhttp.open("GET", "session.php?t=" + type + "&u=" + username + "&p=" + password, true);
    } else if (type === "get") {
        xhttp.open("GET", "session.php?t=" + type, true);
    }


    xhttp.send();

}
/**
* @function profile
* @param {String} type type of message to send database
* @description gets profile info and displays on profile page
*/
function profile(type) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();

    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            document.getElementById(type).innerHTML = this.responseText;

        }
    };
    xhttp.open("GET", "profile.php?t=" + type, true);


    xhttp.send();

}
/**
* @function lobby
* @param {String} type type of message to send database
* @param {String} lobbyname lobby name for joinlobby
* @description gets lobby info to display in joinlobby
*/
function lobby(type, lobbyname) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();

    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

        }
    };
    xhttp.open("GET", "lobby.php?t=" + type + "&l=" + lobbyname, true);


    xhttp.send();

}
/**
* @function achievementlist
* @param {String} type type of message to send database
* @description checks achievements based on profile, updates achievement page
*/
function achievementList(type) {
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();

    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");

    }
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById(type).innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "achievement.php?t=" + type, true);


    xhttp.send();

}
