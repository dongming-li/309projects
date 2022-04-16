/*Connection to Database via ajax calls, might need to do more for move-processing in-game*/

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
    
                    if(mes == "wrong username or password") {
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


function achievement(type){
    var xhttp; 
        if(window.XMLHttpRequest){ 
            xhttp = new XMLHttpRequest(); 
            
        }else{ 
            xhttp = new ActiveXObject("Microsoft.XMLHTTP"); 
            
        } 
        xhttp.onreadystatechange = function(){ 
            if(this.readyState == 4 && this.status == 200){ 
                document.getElementById("achmessage").innerHTML = this.responseText;
                if(type == "FirstGame"){
                    if(document.getElementById("achmessage").innerText == "true"){
                        $('#box1').css('background-color','green');
                    }
                }else if(type == "FirstWinJ"){
                    if(document.getElementById("achmessage").innerText == "true"){
                        $('#box2').css('background-color','green');
                    }
                }else if(type == "FirstWinI"){
                    if(document.getElementById("achmessage").innerText == "true"){
                        $('#box3').css('background-color','green');
                    }
                }
                
                document.getElementById("achmessage").innerHTML = "";
                
            } 
        };
        xhttp.open("GET","achievement.php?t="+type, true);       
            
         
        xhttp.send(); 
        
    }


function position(type, gameID) {
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
    
        } else {
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    
        }
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("inspectorp").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "position.php?t=" + type + "&g=" + gameID, true);
    
    
        xhttp.send();
    
    }

