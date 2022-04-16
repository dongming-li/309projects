/**
 * @module notifications
 */

import React, { Component } from 'react';
import {
    Text,
    StyleSheet,
    View,
    ScrollView
} from 'react-native';

import FriendRequest from './friend-request';
import FriendRequestAccepted from './friend-request-accepted';
import FriendRequestRejected from './friend-request-rejected';

import Routes from '../helpers/routes';
import MessageTypes from '../helpers/message-types';
import Colors from '../helpers/colors';

/**
 * Notifications/Messages menu component
 */
export default class Notifications extends Component {
    /**
     * Creates the notifications/messages menu
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            hasMessages: false,
            messages: []
        }

        this.displayMessages = this.displayMessages.bind(this);
        this.retrieveMessages = this.retrieveMessages.bind(this);
        this.changeState = this.changeState.bind(this);
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
     * Retrieves a user's messages
     */
    retrieveMessages() {
        fetch(Routes.retrieveMessages, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user: this.props.user
            })
        }).then(response => {
            return response.json();
        }).then(body => {
            this.changeState('messages', body);
            this.changeState('hasMessages', true);
        });
    }

    /**
     * Display the user's messages
     */
    displayMessages() {
        if (this.state.hasMessages) {
            if (!this.state.messages.length)
                return (
                    <Text
                        style={styles.noMessagesText}
                    >
                        You have no notifications right now!
                    </Text>
                );

            const messages = this.state.messages.map(message => {
                switch (message.type) {
                    case MessageTypes.friendRequest:
                        return (
                            <FriendRequest
                                sender={message.sender}
                                timestamp={message.timestamp}
                                user={this.props.user}
                                key={message.id}
                            />
                        );
                    case MessageTypes.friendRequestAccepted:
                        return (
                            <FriendRequestAccepted
                                sender={message.sender}
                                timestamp={message.timestamp}
                                user={this.props.user}
                                key={message.id}
                            />
                        );
                    case MessageTypes.friendRequestRejected:
                        return (
                            <FriendRequestRejected
                                sender={message.sender}
                                timestamp={message.timestamp}
                                user={this.props.user}
                                key={message.id}
                            />
                        );
                    default:
                        console.log('[ERROR] Unsupported message type');
                }
            });

            return (
                <ScrollView>
                    {messages}
                </ScrollView>
            );
        }

        this.retrieveMessages();

        return (
            <Text>Please wait while we get your messages</Text>
        );
    }

    /**
     * Render the component
     */
    render() {
        return (
            <View
                style={styles.messagesMenuContainer}
            >
                {this.displayMessages()}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    messagesMenuContainer: {
        flex: 1,
        flexDirection: 'column'
    },
    noMessagesText: {
        color: Colors.getNeutral(800),
        fontSize: 18,
        textAlign: 'center',
        paddingHorizontal: 10,
        paddingTop: 12
    }
});
