
<?php

/**Unused variables present to make altering funtion easier in the future*/
$output = "json"; //or xml
$latitude; // = "42.022339"; //"-33.8670522";
$longitude; // = "-93.650413";//"151.1957362";
$radius = "500"; //instead use rankby=distance. Can't have both
$keyword;
$minprice;
$maxprice;
$opennow;
$rankby = "distance"; //requires one or more of keyword, name, or type, but seems to work without them
$type; //= "restaurant";
$pagetoken;
$region;
$key = "AIzaSyAXBP9NQ7uoOzaWCEaO24VUhzPq3SwPMck";



/** Allows variables $function to be set before HTTP request to this file on the server*/
if(isset($_REQUEST["function"])){
	$function = ''.$_REQUEST["function"];
}

/** Allows variable $latitiude to be set before HTTP request to this file on the server*/

if(isset($_REQUEST["latitude"])){
	$latitude = ''.$_REQUEST["latitude"];
}

/** Allows variable $longitude to be set before HTTP request to this file on the server*/

if(isset($_REQUEST["longitude"])){
	$longitude = ''.$_REQUEST["longitude"];
}

/** The HTTP request format required by the google places API to search for nearby locations*/
$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/$output?location=$latitude,$longitude&rankby=$rankby&type=$type&keyword=$keyword&key=$key";


function getPlaces(){
	global $output; //or xml
	global $latitude;
	global $longitude;
	global $radius; //instead use rankby=distance. Can't have both
	global $keyword;
	global $minprice;
	global $maxprice;
	global $opennow;
	global $rankby;  //requires one or more of keyword, name, or type, but seems to work without them
	global $type; //= "restaurant";
	global $pagetoken;
	global $region;
	global $key;
	global $url;
	global $names;
	global $images;
	global $icons;
	global $placeIds;
	global $all;
	global $array;
	global $contents;
	global $function;


	$contents = file_get_contents($url);
	
	//test if connection worked
	//if($contents !== false){
	   //echo $contents;
	//}

	$array = json_decode($contents, true);
	//gets rid of unnecessary info
	$array = $array['results'];

	/**Puts each needed string into a corresponding array, since we don't need all of the information returned*/
	foreach($array as $component)
	{
	    $names[] = $component['name'];
	    $icons[] = $component['icon'];
	    $placeIds[] = $component['place_id']; //not used

	    $all[] = array($component['name'], $component['icon'], $component['place_id']); 	    
	}


	/**Returns json of all places names*/
	if($function === 'getNames')
	{
		echo json_encode($names);
	}
	/**Returns json of all places icons*/
	if($function === 'getIcons')
	{
		echo json_encode($icons);
	}
	/**Returns json of all places names and icons in an array*/
	if($function === 'getAll')
	{
		echo json_encode($all);
	}
	
}

getPlaces();

?>