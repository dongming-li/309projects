/**
 * @module settings
 */

import React, { Component } from 'react';
import {
    Text,
    TouchableOpacity,
    View,
    StyleSheet
} from 'react-native';

import Colors from '../helpers/colors';

/**
 * Settings menu component
 */
export default class Settings extends Component {
    /**
     * Renders the component
     */
    render() {
        const {
            logout,
            user
        } = this.props;

        return (
            <View>
                <Text
                    style={styles.currentUserText}
                >
                    {`Logged in as ${user}`}
                </Text>
                <TouchableOpacity
                    style={styles.settingsButton}
                    onPress={logout}
                >
                    <Text
                        style={styles.settingsButtonText}
                    >
                        LOG OUT
                    </Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    settingsContainer: {
        display: 'flex'
    },
    settingsButton: {
        backgroundColor: Colors.getBlue(500),
        paddingVertical: 10,
        borderRadius: 5,
        width: 125,
        alignSelf: 'center',
    },
    settingsButtonText: {
        textAlign: 'center',
        color: Colors.getNeutral(100)
    },
    currentUserText: {
        fontSize: 16,
        padding: 12,
        color: Colors.getNeutral(800)
    }
});
