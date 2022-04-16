/**
 * @module toolbar
 */

import React, { Component } from 'react';
import {
    TouchableOpacity,
    View,
    TextInput,
    StyleSheet,
    Text
} from 'react-native';
import { FontAwesome } from '@expo/vector-icons';

import Colors from '../helpers/colors';
import Navigation from "./map";

/**
 * Toolbar component
 */
export default class Toolbar extends Component {
    /**
     * Creates the toolbar
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            destination: ''
        };
    }

    /**
     * Updates the user's destination
     * @function
     * @param {string} text User's destination
     */
    handleDestination = text => {
        this.setState({destination: text});
    };

    /**
     * Renders the component
     */
    render() {
        const {
            onIconClicked,
            isMenuOpen
        } = this.props;

        return (
            <View
                style={styles.toolbar}
            >
                <View
                    style={styles.toolbarTopSpacer}
                />
                <View
                    style={styles.toolbarContainer}
                >
                    <TouchableOpacity
                        onPress={onIconClicked}
                    >
                        <FontAwesome
                            name={isMenuOpen ? 'times' : 'navicon'}
                            size={32}
                            color={Colors.getNeutral(100)}
                            style={styles.menuIcon}
                        />
                    </TouchableOpacity>
                    <View
                        style={styles.mapDestinationInputContainer}
                    >
                        <TextInput
                            style={styles.mapDestinationInput}
                            underlineColorAndroid={Colors.getNeutral(100)}
                            selectionColor={Colors.getNeutral(100)}
                            placeholder='Search here'
                            placeholderTextColor={Colors.getNeutral(100)}
                            onChangeText={this.handleDestination}
                        />
                        <Navigation destination = {this.state.destination}/>
                    </View>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    toolbar: {
        backgroundColor: Colors.getBlue(600),
        height: 70,
        display: 'flex',
        flexDirection: 'column'
    },
    toolbarTopSpacer: {
        height: 30
    },
    toolbarContainer: {
        display: 'flex',
        flexDirection: 'row'
    },
    menuIcon: {
        flexBasis: 'auto',
        paddingLeft: 12,
        paddingRight: 8
    },
    mapDestinationInputContainer: {
        marginTop: -4,
        flexBasis: 'auto',
        flexGrow: 1
    },
    mapDestinationInput: {
        height: 40,
        borderColor: Colors.getNeutral(100),
        marginRight: 8,
        paddingHorizontal: 10,
        fontSize: 16,
        paddingVertical: 10,
        color: Colors.getNeutral(100),
        marginBottom: 10,
    }
})
