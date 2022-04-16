/**
 * @module friend-entry
 */

import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text
} from 'react-native';
import Colors from '../helpers/colors';

/**
 * Friend Entry component
 */
export default class FriendEntry extends Component {
    /**
     * Creates a Friend Entry component
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.getStatusIndicator = this.getStatusIndicator.bind(this);
    }

    /**
     * Gets the correct status indicator for the given status
     * @param {string} status 
     */
    getStatusIndicator(status) {
        switch(status) {
            case 'Online':
                return (
                    <View
                        style={styles.statusIndicatorOnline}
                    />
                );

            case 'Walking':
                return (
                    <View
                        style={styles.statusIndicatorWalking}
                    />
                );

            default:
                return (
                    <View
                        style={styles.statusIndicatorOffline}
                    />
                );
        }
    }

    /**
     * Renders the component
     */
    render() {
        const { status } = this.props,
            name = this.props.name.length > 25 ? `${this.props.name.slice(0, 24)}...` : this.props.name;

        return (
            <View
                style={styles.container}
            >
                <View
                    style={styles.friendSpacer}
                />
                <Text
                    style={styles.friendName}
                >
                    {name}
                </Text>
                <View
                    style={styles.statusSpacer}
                />
                <View
                    style={styles.statusContainer}
                >
                    <View
                        style={styles.statusPadding}
                    />
                    <View
                        style={styles.statusIndicator}
                    />
                    {this.getStatusIndicator(status)}
                    <Text
                        style={styles.friendStatus}
                    >
                        {status}
                    </Text>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        borderBottomColor: Colors.getNeutral(200),
        borderBottomWidth: 2,
        width: 250,
        height: 72
    },
    friendName: {
        fontSize: 18,
        paddingLeft: 12,
        color: Colors.getNeutral(800)
    },
    friendStatus: {
        paddingLeft: 6,
        color: Colors.getNeutral(700),
        lineHeight: 11
    },
    friendSpacer: {
        height: 12
    },
    statusContainer: {
        flex: 1,
        flexDirection: 'row'
    },
    statusIndicatorOnline: {
        height: 12,
        width: 12,
        borderRadius: 6,
        backgroundColor: Colors.getGreen()
    },
    statusIndicatorWalking: {
        height: 12,
        width: 12,
        borderRadius: 6,
        backgroundColor: Colors.getBlue(400)
    },
    statusIndicatorOffline: {
        height: 12,
        width: 12,
        borderRadius: 6,
        backgroundColor: Colors.getNeutral(700)
    },
    statusSpacer: {
        height: 5
    },
    statusPadding: {
        width: 12
    }
})
