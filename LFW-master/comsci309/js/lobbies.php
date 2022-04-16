
<?php
    
    class lobby{
        private  $lobbyID ="0" ;
        private  $lobbyName = "gamelobby";
        private  $timer = "30";
        private  $players = "5";
        private  $tips = "on";
        private  $privacy = "public";
        #upon using lobby class, construct a default / template lobby object with the default values listed above
        
        function __construct($lobbyID,$lobbyName, $hostName,$lobbyType,$timer,$players,$tips,$privacy)
        {
            $this->lobbyID=$lobbyID;
            $this->lobbyName = $lobbyName;
            $this->hostname = $hostName;
            $this->lobbyType = $lobbyType;
            $this->timer = $timer;
            $this->players = $players;
            $this->tips = $tips;
            $this->privacy =$public;
        }
        
        #getters
        function get_lobbyID(){
            return $this->lobbyID;
        }
        function get_lobbyName(){
            return $this->lobbyName;
        }
        function get_hostName(){
            return $this->hostName;
        }
        function get_lobbyType(){
            return $this->lobbyType;
        }
        function get_timer(){
            return $this->timer;
        }
        function get_players(){
            return $this->players;
        }
          function get_privacy(){
            return $this->privacy;
        }
        function get_tips(){
            return $this->tips
        }
        #setters
        function set_lobbyName($lobbyName){
            $this->lobbyName='$lobbyName';
        }
        function set_hostName($hostName){
            $this->hostName=$hostName;
        }
        function get_lobbyType($lobbyType){
            $this->lobbyType=$lobbyType;
        }
        function set_timer($timer){
            $this->timer=$timer;
        }
        function set_players($players){
            $this->players=$players;
        }
        function set_privacy($privacy){
            $this->privacy=$privacy;
        }
        function set_tips($tips){
            $this->tips=$tips;
        }
        
           function displayInfo($obj)
{
foreach (get_object_vars($obj) as $prop => $val) {
    echo "\t$prop = $val\n";
}
}
    
    }
        


    ?>
 
        
        