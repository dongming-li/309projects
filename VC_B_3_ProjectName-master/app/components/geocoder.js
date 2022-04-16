/**
 * @module geocoder
 */

import React, { Component } from 'react';
import {StyleSheet, Dimensions, Button, View, TextInput } from 'react-native';
import MapView from 'react-native-maps';
import Geocoder from 'react-native-geocoding';

let { width, height } = Dimensions.get('window');
const ASPECT_RATIO = width / height;
const LATITUDE = 0;
const LONGITUDE = 0;
const LATITUDE_DELTA = 0.0922;
const LONGITUDE_DELTA = LATITUDE_DELTA * ASPECT_RATIO;
let curLat = 0;
let curLong = 0;
let destLat = 0;
let destLong = 0;
let dest = '';

/**
 * Geocoder component
 */
export default class WalkMeHome extends Component {
    /**
     * Creates a geocoder
     */
    constructor() {
        super();
        this.state = {
            region: {
                latitude: LATITUDE,
                longitude: LONGITUDE,
                latitudeDelta: LATITUDE_DELTA,
                longitudeDelta: LONGITUDE_DELTA,
            },
            destination: '',

        };
    }

    /**
     * Tasks to complete before the component is mounted to the DOM
     */
    componentDidMount() {
        navigator.geolocation.getCurrentPosition(
            position => {
                this.setState({
                    region: {
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                        latitudeDelta: LATITUDE_DELTA,
                        longitudeDelta: LONGITUDE_DELTA,
                    }
                });
            },
            (error) => console.log(error.message),
            { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 },
        );
        this.watchID = navigator.geolocation.watchPosition(
            position => {
                this.setState({
                    region: {
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude,
                        latitudeDelta: LATITUDE_DELTA,
                        longitudeDelta: LONGITUDE_DELTA,
                    }

                });
                curLat = position.coords.latitude;
                curLong = position.coords.longitude;
            }
        );
    }

    /**
     * Tasks to complete before the component is unmounted from the DOM
     */
    componentWillUnmount() {
        navigator.geolocation.clearWatch(this.watchId);
    }

    /**
     * Get latitude and longitude coordinates from the user's location
     */
    geocodeLocation(){
        Geocoder.setApiKey('AIzaSyBnGTwxpl6Gx-mwMQ2tXiVmlJGcwgrq1ms');

        Geocoder.getFromLocation(dest).then(json=>{
                let location = json.results[0].geometry.location;
                destLat = location.lat;
                destLong = location.lng;
                alert(destLat + "," + destLong);
            },
            error => {
                console.log(error);
            }
        );
    };

    /**
     * Updates the user's destination
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
            <View style={{paddingTop: 20}} >
                <TextInput
                    placeholder="Enter Destination:"
                    onChangeText={this.handleDestination}
                />
                <Button
                    onPress={this.geocodeLocation}
                    title="Geocode Location"
                />
                <MapView
                    style={ styles.container }
                    showsUserLocation={ true }
                    region={ this.state.region }
                    onRegionChange={ region => this.setState({region}) }
                    onRegionChangeComplete={ region => this.setState({region}) }
                >
                    <MapView.Marker
                        coordinate={{latitude: destLat,
                            longitude: destLong}}
                        title={"Destination"}
                        description={"Go Here!"}
                    />
                </MapView>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        height: '100%',
        width: '100%',
    }
});