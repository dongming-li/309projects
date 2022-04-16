/**
 * @module navigation
 */

import React, { Component } from 'react';
import {
    Button,
    StyleSheet,
    Text,
    TextInput,
    TouchableOpacity,
    View,
    Dimensions,
    Alert
} from 'react-native';
import Menu from 'components';

import MapView from 'react-native-maps';
import polyline from '@mapbox/polyline';
import Geocoder from 'react-native-geocoding';

let lat = 0.0;
let destLat = 0.0;
let destLong = 0.0;
let lng = 0.0;
let flag = false;
let dest = "";

/**
 * Navigation component
 */
export default class Navigation extends Component {
    /**
     * Creates the navigation component
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            coords: [],
            destination : "",
            region: {
                latitude: 0.0,
                longitude: 0.0,
            },
        };
        this.onPressFunction = this.onPressFunction.bind(this)
    }

    /**
     * Tasks to do when the component was mounted to the DOM
     */
    componentDidMount() {
        navigator.geolocation.getCurrentPosition(
            (position) => {

                this.setState({
                    region: {

                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude
                    },
                    coords: [],
                    accuracy: 1,

                });
                lat = position.coords.latitude;
                lng = position.coords.longitude;
            });
    }

    /**
     * Tasks to do when the component will update
     */
    componentWillUpdate(){
        if(flag){
            this.getDirections(lat + ',' + lng, destLat + ',' + destLong);
            flag = false;
        }
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
            this.setState({coords: coords})
            return coords
        } catch(error) {
            alert(error);
            return error;
        }
    };

    /**
     * Update the user's destination
     */
    onPressFunction(){
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
     * Update the user's destination in state
     * @function
     * @param {string} text User's destination
     */
    handleDestination = text => {
        this.setState({destination:text});
        dest = this.state.destination;
    };

    /**
     * Renders the component
     */
    render() {
        return (
            <View>

                <MapView.Animated style={styles.map}
                                  ref = {(mapView) => { _mapView = mapView; }}
                                  showsUserLocation={true}
                                  zoomEnabled={true}
                                  pitchEnabled={true}
                                  showsCompass={true}
                                  region={this.state.region}
                                  onRegionChange={ region => this.setState({region})}
                                  onRegionChangeComplete={ region => this.setState({region}) }

                >

                    <MapView.Polyline
                        coordinates={this.state.coords}
                        strokeWidth={3}
                        strokeColor="blue"/>

                </MapView.Animated>

                <TextInput
                    style={{
                        paddingTop: 20,
                        height: 40,
                        backgroundColor: 'white'
                    }}
                    placeholder="Enter Destination:"
                    onChangeText={this.handleDestination}
                />
                <Button onPress={this.onPressFunction} title="Get Directions"/>
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
});