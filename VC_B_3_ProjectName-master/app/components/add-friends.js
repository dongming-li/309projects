/**
 * @module add-friends
 */

import React, { Component } from 'react';
import {
    Text,
    StyleSheet,
    View,
    TextInput,
    TouchableOpacity
} from 'react-native';

import Colors from '../helpers/colors';
import Routes from '../helpers/routes';
import MessageTypes from '../helpers/message-types';
import { AddFriendsErrors } from '../helpers/error-messages';

/**
 * Add Friends menu component
 */
export default class AddFriends extends Component {
    /**
     * Creates the Add Friends menu
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            friendEmail: '',
            message: '',
            error: false
        };

        this.changeState = this.changeState.bind(this);
        this.changeMessage = this.changeMessage.bind(this);
        this.searchFriend = this.searchFriend.bind(this);
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
     * Change the message displayed to the user
     * @param {string} message 
     */
    changeMessage(message) {
        this.changeState('message', message);
    }

    /**
     * Attempts to send a friend request to the given user
     */
    searchFriend() {
        this.changeMessage('');

        fetch(Routes.sendMessage, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                type: MessageTypes.friendRequest,
                sender: this.props.user,
                sendee: this.state.friendEmail,
                timestamp: Date.now()
            })
        }).then(response => {
            return response.json();
        }).then(body => {
            if (body.error) {
                switch(body.message) {
                    case AddFriendsErrors.userDoesNotExistCode:
                        this.changeMessage(AddFriendsErrors.userDoesNotExist(this.state.friendEmail));
                        break;
                    case AddFriendsErrors.duplicateMessageCode:
                        this.changeMessage(AddFriendsErrors.duplicateMessage(this.state.friendEmail));
                        break;
                    default:
                        console.log(`[DEBUG] Unrecognized error with adding friends: ${body.message}`);
                }
            } else {
                const successMessage = `${this.state.friendEmail} has been sent a friend request!`;
                this.changeMessage(successMessage);
            }

            this.changeState('error', body.error)
        });
    }

    /**
     * Renders the component
     */
    render() {
        const {
            message,
            error
        } = this.state;

        return (
            <View
                style={styles.addFriendsContainer}
            >
                <Text
                    style={styles.addFriendText}
                >
                    Please enter your friend's email below
                </Text>
                <TextInput
                    style={styles.input}
                    placeholder={`Friend's Email`}
                    placeholderTextColor={Colors.getNeutral(700)}
                    keyboardType='email-address'
                    selectionColor={Colors.getBlue(500)}
                    underlineColorAndroid={Colors.getBlue(500)}
                    onChangeText={value => this.changeState('friendEmail', value)}
                />
                <TouchableOpacity
                    style={styles.searchFriendButton}
                    onPress={this.searchFriend}
                >
                    <Text
                        style={styles.searchFriendButtonText}
                    >
                        ADD FRIEND
                    </Text>
                </TouchableOpacity>
                <View
                    style={styles.addFriendMessageContainer}
                >
                    <Text
                        style={error ? styles.addFriendError : styles.addFriendSuccess}
                    >
                        {this.state.message}
                    </Text>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    addFriendsContainer: {
        flex: 1,
        flexDirection: 'column',
        alignItems: 'center'
    },
    addFriendText: {
        paddingTop: 12,
        color: Colors.getNeutral(900)
    },
    addFriendMessageContainer: {
        margin: 12,
        height: 25
    },
    addFriendError: {
        color: Colors.getRed(),
        textAlign: 'center'
    },
    addFriendSuccess: {
        color: Colors.getGreen(),
        textAlign: 'center'
    },
    input: {
        height: 40,
        width: 225,
        marginBottom: 10,
        paddingHorizontal: 10,
        color: Colors.getBlue(500)
    },
    searchFriendButton: {
        backgroundColor: Colors.getBlue(500),
        paddingVertical: 10,
        borderRadius: 5,
        width: 125
    },
    searchFriendButtonText: {
        textAlign: 'center',
        color: Colors.getNeutral(100)
    }
});
