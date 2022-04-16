/**
 * @module menu
 */

import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    Dimensions,
    TouchableOpacity
} from 'react-native';
import _ from 'lodash';
import { FontAwesome } from '@expo/vector-icons';

import FriendEntry from './friend-entry';
import Toolbar from './toolbar';
import Map from './map';
import Settings from './settings';
import AddFriends from './add-friends';
import Notifications from './notifications';

import Colors from '../helpers/colors';
import Routes from '../helpers/routes';
import Menus from '../helpers/menu';

/**
 * Main view component
 */
export default class Menu extends Component {
    /**
     * Creates the main view
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            isMenuOpen: false,
            hasFriends: false,
            hasFriendDetails: false,
            friends: [],
            friendsData: [],
            menu: Menus.friends
        };

        this.toggleMenu = this.toggleMenu.bind(this);
        this.displayDrawer = this.displayDrawer.bind(this);
        this.getFriends = this.getFriends.bind(this);
        this.getFriendsInfo = this.getFriendsInfo.bind(this);
        this.changeState = this.changeState.bind(this);

        this.getFriends();
    }

    /**
     * Tasks to complete before the component has mounted to the DOM
     */
    componentWillMount() {
        fetch(Routes.updateUserStatus, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user: this.props.email,
                status: 'Online'
            })
        });
    }

    /**
     * Change the given field inside of state with the given value
     * @param {string} property Field inside of state to modify
     * @param {*} value Value to be put inside of state
     */
    changeState(property, value) {
        this.setState(previousState => {
            return Object.assign({}, previousState, {
                [property]: value
            });
        });
    }

    /**
     * Toggle the menu open/close
     */
    toggleMenu() {
        this.changeState('isMenuOpen', !this.state.isMenuOpen);
        this.changeMenu(Menus.friends);
    }

    /**
     * Change which menu is currently being displayed inside of the drawer
     * @param {string} menu 
     */
    changeMenu(menu) {
        this.changeState('menu', menu);
    }

    /**
     * Display the drawer
     */
    displayDrawer() {
        const {
            isMenuOpen,
            menu
        } = this.state;

        if (isMenuOpen) {
            return (
                <View
                    style={styles.menuContainer}
                >
                    <View>
                        <View
                            style={styles.menuHeaderContainer}
                        >
                            <TouchableOpacity
                                onPress={this.changeMenu.bind(this, Menus.friends)}
                            >
                                <FontAwesome
                                    name='users'
                                    size={24}
                                    color={menu === Menus.friends ? Colors.getBlue(500) : Colors.getNeutral(700)}
                                    style={styles.menuIcon}
                                />
                            </TouchableOpacity>
                            <TouchableOpacity
                                onPress={this.changeMenu.bind(this, Menus.addFriends)}
                            >
                                <FontAwesome
                                    name='user-plus'
                                    size={24}
                                    color={menu === Menus.addFriends ? Colors.getBlue(500) : Colors.getNeutral(700)}
                                    style={styles.menuIcon}
                                />
                            </TouchableOpacity>
                            <TouchableOpacity
                                onPress={this.changeMenu.bind(this, Menus.notifications)}
                            >
                                <FontAwesome
                                    name='bell-o'
                                    size={24}
                                    color={menu === Menus.notifications ? Colors.getBlue(500) : Colors.getNeutral(700)}
                                    style={styles.menuIcon}
                                />
                            </TouchableOpacity>
                            <TouchableOpacity
                                onPress={this.changeMenu.bind(this, Menus.settings)}
                            >
                                <FontAwesome
                                    name='cog'
                                    size={24}
                                    color={menu === Menus.settings ? Colors.getBlue(500) : Colors.getNeutral(700)}
                                    style={styles.menuIcon}
                                />
                            </TouchableOpacity>
                        </View>
                        {this.displayMenu()}
                    </View>
                </View>
            );
        }
    }

    /**
     * Display the menu
     */
    displayMenu() {
        const {
            logout,
            email
        } = this.props;

        switch (this.state.menu) {
            case Menus.settings:
                return (
                    <Settings
                        logout={logout}
                        user={email}
                    />
                );
            case Menus.addFriends:
                return (
                    <AddFriends
                        user={email}
                    />
                );
            case Menus.notifications:
                return (
                    <Notifications
                        user={email}
                    />
                );
            default:
                return (
                    <View>
                        {this.displayFriendEntries()}
                        <View
                            style={styles.addFriendsButtonContainer}
                        >
                            <TouchableOpacity
                                style={styles.addFriendsButton}
                                onPress={this.changeMenu.bind(this, Menus.addFriends)}
                            >
                                <Text
                                    style={styles.addFriendsButtonText}
                                >
                                    ADD NEW FRIEND
                                </Text>
                            </TouchableOpacity>
                        </View>
                    </View>
                );
        }
    }

    /**
     * Get a list of the user's friends
     */
    getFriends() {
        const { email } = this.props;

        fetch(Routes.getFriends, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email
            })
        }).then(response => {
            if (response.ok)
                return response.json();
            
            console.log(response.status);
        }).then(friends => {
            this.setState(previousState => {
                return Object.assign({}, previousState, {
                    hasFriends: true,
                    friends
                });
            });
        });
    }

    /**
     * Get a list of the user's friends' detailed information
     */
    getFriendsInfo() {
        const { friends } = this.state;
        friends.forEach((friend, i, friends) => {
            fetch(Routes.getFriendInfo, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: friend.friend
                })
            }).then(response => {
                return response.json();
            }).then(friendDetails => {
                this.setState(previousState => {
                    const friendsData = _.cloneDeep(previousState.friendsData);
                    let containsFriend = false;

                    for (let i = 0; i < friendsData.length; i++) {
                        if (_.isEqual(friendsData[i], friendDetails)) {
                            containsFriend = true;
                            break;
                        }
                    }

                    if (!containsFriend)
                        friendsData.push(friendDetails);

                    return Object.assign({}, previousState, {
                        friendsData,
                        hasFriendDetails: previousState.hasFriendDetails || i === friends.length - 1 ? true : false
                    });
                });

                this.setState(previousState => {
                    const sortedFriendData = this.state.friendsData.sort((a, b) => {
                        if (a.status === b.status) {
                            if (a.firstName === b.firstName) {
                                return a.lastName.localeCompare(b.lastName);
                            } else {
                                return a.firstName.localeCompare(b.firstName);
                            }
                        } else if (a.status === 'Walking' || a.status === 'Online' && b.status === 'Offline') {
                            return -1;
                        } else {
                            return 1;
                        }
                    });

                    return Object.assign({}, previousState, {
                        friendsData: sortedFriendData
                    });
                });
            });
        });
    }

    /**
     * Display the user's friends
     */
    displayFriendEntries() {
        if (this.state.hasFriendDetails) {
            const friendEntries = this.state.friendsData.map(friend => {
                return (
                    <FriendEntry
                        name={`${friend.firstName} ${friend.lastName}`}
                        status={friend.status}
                        key={friend.email}
                    />
                );
            });

            return (
                <View>
                    {friendEntries}
                </View>
            );
        }

        return (
            <Text
                style={styles.noFriendsText}
            >
                It appears you have no friends yet. Try adding some!
            </Text>
        );
    }

    /**
     * Determine whether to update the component
     * @param {object} previousProps The component's props from the previous render
     * @param {object} previousState The component's state from the previous render
     */
    componentDidUpdate(previousProps, previousState) {
        if (this.state.hasFriends && !this.state.hasFriendDetails) {
            this.getFriendsInfo()
        }
    }

    /**
     * Render the component
     */
    render() {
        return (
            <View
                style={styles.container}
            >
                <Toolbar
                    title='Walk Me Home'
                    style={styles.toolbar}
                    titleColor='white'
                    navIconName='navicon'
                    onIconClicked={this.toggleMenu}
                    isMenuOpen={this.state.isMenuOpen}
                />
                {this.displayDrawer()}
                <Map/>
            </View>
        );
    }

    /**
     * Tasks to complete after the component has unmounted from the DOM
     */
    componentWillUnmount() {
        fetch(Routes.updateUserStatus, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user: this.props.email,
                status: 'Offline'
            })
        });
    }
}

const styles = StyleSheet.create({
    toolbar: {
        height: 56,
        backgroundColor: Colors.getBlue(600)
    },
    menuContainer: {
        flex: 1,
        flexDirection: 'row',
        width: 250,
        backgroundColor: Colors.getNeutral(100),
        position: 'absolute',
        zIndex: 100,
        top: 70,
        height: Dimensions.get('window').height - 70
    },
    container: {
        flex: 1,
        backgroundColor: Colors.getNeutral(300)
    },
    menuHeaderContainer: {
        width: 250,
        height: 34,
        backgroundColor: Colors.getNeutral(100),
        borderBottomColor: Colors.getNeutral(200),
        borderBottomWidth: 2,
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-around'
    },
    friendEntriesContainer: {
        flex: 1,
        flexDirection: 'row'
    },
    menuIcon: {
        paddingVertical: 4
    },
    addFriendsButtonContainer: {
        flex: 1,
        flexDirection: 'column',
        alignItems: 'center',
        paddingTop: 20
    },
    addFriendsButton: {
        backgroundColor: Colors.getBlue(500),
        paddingVertical: 10,
        borderRadius: 5,
        width: 125,
        height: 38
    },
    addFriendsButtonText: {
        textAlign: 'center',
        color: Colors.getNeutral(100)
    },
    noFriendsText: {
        color: Colors.getNeutral(900),
        textAlign: 'center',
        paddingTop: 12
    }
})
