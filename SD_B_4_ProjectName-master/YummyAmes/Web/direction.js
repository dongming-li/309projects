function initMap() {
	var directionsService = new google.maps.DirectionsService;
	var directionsDisplay = new google.maps.DirectionsRenderer;

	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 10,
		center: {
			lat: 42.030,
			lng: 93.631
		}
	});
	directionsDisplay.setMap(map);
	directionsDisplay.setOptions({
		suppressMarkers: true
	});
	calculateAndDisplayRoute(directionsService, directionsDisplay);
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	directionsService.route({
		origin: start,
		destination: end,
		travelMode: 'DRIVING'
	}, function (response, status) {
		if (status === 'OK') {
			directionsDisplay.setDirections(response);
			var leg = response.routes[0].legs[0];
			var startMarker = new google.maps.Marker({
				position: leg.start_location,
				map: directionsDisplay.map,
				icon: 'restaurant.png'
			});
			var stopMarker = new google.maps.Marker({
				position: leg.end_location,
				map: directionsDisplay.map,
				icon: 'customer.png'
			});
		} else {
			window.alert('Directions request failed due to ' + status);
		}
	});
}
// JavaScript Document