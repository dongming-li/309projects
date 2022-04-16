/**
 * @module map
 */

import React, { Component } from 'react';
import {
    Button,
    StyleSheet,
    View,
    Dimensions,
    Alert
} from 'react-native';
import MapView from 'react-native-maps';
import polyline from '@mapbox/polyline';
import Geocoder from 'react-native-geocoding';
import Routes from '../helpers/routes';

let { width, height } = Dimensions.get('window');
const ASPECT_RATIO = width / height;
const LATITUDE = 0;
const LONGITUDE = 0;
const LATITUDE_DELTA = 0.0922;
const LONGITUDE_DELTA = LATITUDE_DELTA * ASPECT_RATIO;
let lat = 0.0;
let destLat = 0.0;
let destLong = 0.0;
let lng = 0.0;
let flag = false;
let dest = "";

/**
 * Map component
 */
export default class Navigation extends Component {
    /**
     * Creates the map component
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            coords: [],
            destination: this.props.destination,
            latitude: 0.0,
            longitude: 0.0
        };
        this.onPressFunction = this.onPressFunction.bind(this)
    }

    /**
     * Tasks to complete after the component has mounted to the DOM
     */
    componentDidMount() {
        navigator.geolocation.getCurrentPosition(
            (position) => {

                this.setState({
                    region: {

                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                    },
                    coords: [],
                    accuracy: 1,

                });
                lat = position.coords.latitude;
                lng = position.coords.longitude;
            });
    }

    /**
     * Tasks to complete when the component will update
     */
    componentWillUpdate(){
        if (flag) {
            this.getDirections(lat + ',' + lng, destLat + ',' + destLong);
            flag = false;
            this.userLocation();
        }
    }

    /**
     * Tasks to complete when the component will receive new props
     * @param {object} nextProps Incoming props from the new render
     */
    componentWillReceiveProps(nextProps) {
        this.setState({
            destination: nextProps.destination,
        });

        dest = this.state.destination;
    }

    /**
     * Get route from user's locaton to their destination
     * @async
     * @param {string} startLoc User's starting location
     * @param {string} destinationLoc User's destination
     */
    async getDirections(startLoc, destinationLoc) {
        const mode = 'walking';
        try {
            let resp = await fetch(`https://maps.googleapis.com/maps/api/directions/json?origin=${ startLoc }&destination=${ destinationLoc }&mode=${mode}`);
            let respJson = await resp.json();
            let points = polyline.decode(respJson.routes[0].overview_polyline.points);
            let coords = points.map((point, index) => {
                return  {
                    latitude : point[0],
                    longitude : point[1]
                }
            });
            this.setState({coords: coords});
            return coords;
        } catch(error) {
            alert(error);
            return error;
        }
    };

    /**
     * Update the user's destination
     */
    onPressFunction(){
        if(dest !== "") {
            Geocoder.setApiKey('AIzaSyBnGTwxpl6Gx-mwMQ2tXiVmlJGcwgrq1ms');

            Geocoder.getFromLocation(dest).then(json => {
                    let location = json.results[0].geometry.location;
                    destLat = location.lat;
                    destLong = location.lng;
                    this.setState({destination:dest});
                    flag = true;
                },
                error => {
                    Alert.alert("Invalid Location");
                }
            );
        }
        this.onPressFunction2();
    }

    /**
     * Save the user's location
     */
    userLocation(){
        const {latitude, longitude} = this.state;
        this.setState({
                latitude: lat,
                longitude: lng
            });
        fetch(Routes.userLocation,{

            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                latitude, longitude
            })
        });

    }

    /**
     * Update the user's destination
     */
    onPressFunction2(){
        if(dest != "") {
            Geocoder.setApiKey('AIzaSyBnGTwxpl6Gx-mwMQ2tXiVmlJGcwgrq1ms');

            Geocoder.getFromLocation(dest).then(json => {
                    let location = json.results[0].geometry.location;
                    destLat = location.lat;
                    destLong = location.lng;
                    this.setState({destination:dest});
                    flag = true;
                },
                error => {
                    Alert.alert("Invalid Location");
                }
            );
        }
    }

    /**
     * Renders the component
     */
    render() {
        return (
            <View>
                <MapView.Animated style={styles.map}
                                  ref = {(mapView) => { _mapView = mapView; }}
                                  showsUserLocation={true}
                                  showsCompass={true}
                                  region={{
                                      latitude: lat,
                                      longitude: lng,
                                      latitudeDelta: 0.06,
                                      longitudeDelta: 0.01
                                  }}

                >

                    <MapView.Marker
                        coordinate={{latitude: destLat,
                            longitude: destLong}}
                        title={"Destination"}
                        description={"Go Here!"}
                    />


                    <MapView.Polyline
                        coordinates={this.state.coords}
                        strokeWidth={3}
                        strokeColor="blue"/>

                </MapView.Animated>
                <View style = {styles.submitButton}>
                <Button onPress={this.onPressFunction} title="Get Directions"/>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    map: {
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        width: Dimensions.get('window').width,
        height: Dimensions.get('window').height
    },
    submitButton: {
        zIndex: 50
    }
});
