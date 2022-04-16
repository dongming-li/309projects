
<?php


$latitude = "-33.8670522";
$longitude = "151.1957362";
$radius = "500";

//Gives INVALID REQUEST
//$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-$latitude,$longitude&radius=$radius&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";

//Gives INVALID REQUEST
//$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=-$latitude,$longitude&radius=$radius&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";

//WORKS
//$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";

//WOrks
//$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=" . $latitude . "," . $longitude . "&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";

//Also works
$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=$latitude,$longitude&radius=500&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";

//Gives parse error at file_get_contents($url) NOW WORKS
//$url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&rankby=distance&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck';

//WORKS
//$url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=$radius&rankby=prominence&type=restaurant&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck';

//Gives parse error at file_get_contents($url) NOW WORKS
//$url = 'https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&rankby=distance&type=establishment&keyword=cruise&key=AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck';

//$response = file_get_contents($url, $info);
//print_r($info);


$contents = file_get_contents($url);
 
//If $contents is not a boolean FALSE value.
if($contents !== false){
    //Print out the contents.
    echo $contents;
}


//$curl = curl_init('$url');

?>