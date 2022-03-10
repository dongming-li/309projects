<?php

$latitude = NULL;
$longitude = NULL;
$radius = NULL;
$place_type = NULL;
$keyword = NULL;

$function = NULL;

$response = http_get("http://www.example.com/", array("timeout"=>1), $info);
print_r($info);

die("here");

if(isset($_REQUEST["function"])){
	$function = ''.$_REQUEST["function"];
}else{
    die("Function not specified.");
}

if(isset($_REQUEST["latitude"])){
	$latitude = ''.$_REQUEST["latitude"];
}
if(isset($_REQUEST["longitude"])){
	$longitude = ''.$_REQUEST["longitude"];
}
if(isset($_REQUEST["radius"])){
	$radius = ''.$_REQUEST["radius"];
}
if(isset($_REQUEST["place_type"])){
	$place_type = ''.$_REQUEST["place_type"];
}
if(isset($_REQUEST["keyword"])){
	$keyword = ''.$_REQUEST["keyword"];
}

if($function === "getNearbyLocations")
    getNearbyLocations();

function getNearbyLocations(){
    global $latitude, $longitude, $radius, $place_type, $keyword;
    
    $api_key = AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck;
    $url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$latitude,$longitude&radius=$radius&type=$place_type&keyword=$keyword&key=$api_key";
    
    $response = http_get($url, array("timeout"=>1), $info);
    print_r($info);
}

?>