/**
 * @module friend-request
 */

import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet,
    TouchableOpacity
} from 'react-native';
import { FontAwesome } from '@expo/vector-icons';
import Moment from 'moment';

import Colors from '../helpers/colors';
import Routes from '../helpers/routes';
import MessageTypes from '../helpers/message-types';

/**
 * Friend request message component
 */
export default class FriendRequest extends Component {
    /**
     * Creates a friend request message
     * @param {Object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            visible: true
        }

        this.changeState = this.changeState.bind(this);
        this.displayAge = this.displayAge.bind(this);
        this.acceptFriendRequest = this.acceptFriendRequest.bind(this);
        this.rejectFriendRequest = this.rejectFriendRequest.bind(this);
    }

    /**
     * Change the given field inside of state with the given value
     * @param {string} field Field inside of state to modify
     * @param {*} value Value to be put inside of state
     */
    changeState(field, value) {
        this.setState(previousState => {
            return Object.assign({}, previousState, {
                [field]: value
            });
        });
    }

    /**
     * Displays how long ago the message was sent
     */
    displayAge() {
        const { timestamp } = this.props;

        return Moment(timestamp, 'x').fromNow();
    }

    /**
     * Accepts and deletes the friend request and sends a friend request accepted message to the sender
     */
    acceptFriendRequest() {
        fetch(Routes.sendMessage, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: MessageTypes.friendRequestAccepted,
                sender: this.props.user,
                sendee: this.props.sender,
                timestamp: Date.now()
            })
        })

        fetch(Routes.deleteMessage, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: MessageTypes.friendRequest,
                sender: this.props.sender,
                sendee: this.props.user,
                timestamp: this.props.timestamp
            })
        });

        fetch(Routes.addFriend, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user: this.props.user,
                friend: this.props.sender
            })
        });

        this.changeState('visible', false);
    }

    /**
     * Deletes the friend request and sends a friend request rejected message to the sender
     */
    rejectFriendRequest() {
        fetch(Routes.sendMessage, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: MessageTypes.friendRequestRejected,
                sender: this.props.user,
                sendee: this.props.sender,
                timestamp: Date.now()
            })
        })

        fetch(Routes.deleteMessage, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: MessageTypes.friendRequest,
                sender: this.props.sender,
                sendee: this.props.user,
                timestamp: this.props.timestamp
            })
        });

        this.changeState('visible', false);
    }

    /**
     * Renders the component
     */
    render() {
        const {
            sender,
            timestamp
        } = this.props;

        if (this.state.visible) {
            return (
                <View
                    style={styles.messageContainer}
                >
                    <Text
                        style={styles.messageAge}
                    >
                        {this.displayAge()}
                    </Text>
                    <Text
                        style={styles.messageText}
                    >
                        {`Friend request from ${sender}`}
                    </Text>
                    <View
                        style={styles.messageButtonContainer}
                    >
                        <TouchableOpacity
                            onPress={this.acceptFriendRequest}
                            style={styles.actionButton}
                        >
                            <FontAwesome
                                name='check'
                                size={24}
                                color={Colors.getGreen()}
                            />
                        </TouchableOpacity>
                        <TouchableOpacity
                            onPress={this.rejectFriendRequest}
                            style={styles.actionButton}
                        >
                            <FontAwesome
                                name='times'
                                size={24}
                                color={Colors.getRed()}
                            />
                        </TouchableOpacity>
                    </View>
                </View>
            );
        }

        return <View/>
    }
}

const styles = StyleSheet.create({
    messageContainer: {
        borderBottomColor: Colors.getNeutral(300),
        borderBottomWidth: 2
    },
    messageAge: {
        fontSize: 10,
        paddingTop: 12,
        paddingLeft: 12
    },
    messageText: {
        paddingVertical: 10,
        paddingLeft: 12,
        color: Colors.getNeutral(800),
        fontSize: 18
    },
    messageButtonContainer: {
        flexDirection: 'row',
        justifyContent: 'space-around',
        paddingBottom: 12
    },
    actionButton: {
        paddingVertical: 6
    }
})
