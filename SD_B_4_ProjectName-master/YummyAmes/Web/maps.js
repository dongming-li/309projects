function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 15,
		center: {
			lat: 42.030,
			lng: 93.631
		}
	});
	var geocoder = new google.maps.Geocoder();


	geocodeAddress(geocoder, map);
}

function geocodeAddress(geocoder, resultsMap) {
	geocoder.geocode({
		'address': address
	}, function (results, status) {
		if (status === 'OK') {
			resultsMap.setCenter(results[0].geometry.location);

			var contentString = description;
			var infowindow = new google.maps.InfoWindow({
				content: contentString
			});
			var marker = new google.maps.Marker({
				map: resultsMap,
				position: results[0].geometry.location
			});
			infowindow.open(map, marker);
			marker.addListener('click', function () {
				infowindow.open(map, marker);
			});
			var panorama = new google.maps.StreetViewPanorama(
				document.getElementById('pano'), {
					position: results[0].geometry.location,
					pov: {
						heading: 34,
						pitch: 10
					}
				});
			map.setStreetView(panorama);




		} else {
			alert('Geocode was not successful for the following reason: ' + status);
		}
	});
}
