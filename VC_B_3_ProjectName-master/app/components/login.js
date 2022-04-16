/**
 * @module login
 */

import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    Image,
    TextInput,
    TouchableOpacity,
    View,
    Button
} from 'react-native';

import Colors from '../helpers/colors';
import Routes from '../helpers/routes';
import { LoginFormErrors } from '../helpers/error-messages';

/**
 * Login view component
 */
export default class Login extends Component {
    /**
     * Creates the login view
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            formError: ''
        }

        this.loginUser = this.loginUser.bind(this);
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
     * Logs the user into the app
     */
    loginUser() {
        const { authUser } = this.props;
        const {
            email,
            password
        } = this.state;

        if (!email.length) {
            this.changeState('formError', LoginFormErrors.emailEmpty);
            return;
        }

        if (!password.length) {
            this.changeState('formError', LoginFormErrors.passwordEmpty);
            return;
        }

        fetch(Routes.login, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: this.state.email,
                password: this.state.password
            })
        }).then(response => {
            return response.json();
        }).then(body => {
            if (body.email)
                authUser(body.email);
            else if (body.error === 'email')
                this.changeState('formError', LoginFormErrors.emailIncorrect);
            else if (body.error === 'password')
                this.changeState('formError', LoginFormErrors.passwordIncorrect);
        }).catch(error => {
            console.log(error);
        });
    }

    /**
     * Renders the component
     */
    render() {
        const { switchToRegisterView } = this.props;

        return (
            <View style = {styles.container}>
                <View
                    style={styles.appNameSpacer}
                />
                <Text
                    style={styles.appName}
                >
                    Walk Me Home
                </Text>
                <View
                    style={styles.userInfoSpacer}
                />
                <View
                    style={styles.formErrorContainer}
                >
                    <Text
                        style={styles.formError}
                    >
                        {this.state.formError}
                    </Text>
                </View>
                <TextInput
                    style={styles.input}
                    placeholder='Email'
                    placeholderTextColor={Colors.getNeutral(100)}
                    keyboardType='email-address'
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    onChangeText={value => this.changeState('email', value)}
                />
                <TextInput
                    style={styles.input}
                    placeholder='Password'
                    placeholderTextColor={Colors.getNeutral(100)}
                    secureTextEntry
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    onChangeText={value => this.changeState('password', value)}
                />
                <View
                    style={styles.loginSpacer}
                />
                <View
                    style={styles.loginButtonContainer}
                >
                    <TouchableOpacity
                        style={styles.loginButton}
                        onPress={this.loginUser}
                    >
                        <Text
                            style={styles.loginButtonText}
                        >
                            LOGIN
                        </Text>
                    </TouchableOpacity>
                </View>
                <View
                    style={styles.registerSpacer}
                />
                <View
                    style={styles.loginButtonContainer}
                >
                    <TouchableOpacity
                        onPress={switchToRegisterView}
                    >
                        <View
                            style={styles.registerButtonContainer}
                        >
                            <Text
                                style={styles.loginButtonText}
                            >
                                Create Account?
                            </Text>
                        </View>
                    </TouchableOpacity>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        padding: 10,
        backgroundColor: Colors.getBlue(500),
        flex: 1
    },
    appNameSpacer: {
        height: 75
    },
    appName: {
        fontSize: 32,
        color: Colors.getNeutral(100),
        textAlign: 'center',
        fontWeight: 'bold'
    },
    userInfoSpacer: {
        height: 55
    },
    input: {
        height: 40,
        backgroundColor: Colors.getBlue(900),
        marginBottom: 10,
        paddingHorizontal: 10,
        borderRadius: 5,
        color: Colors.getNeutral(100)
    },
    loginSpacer: {
        height: 30
    },
    loginButtonContainer: {
        flex: 1,
        flexDirection: 'column',
        alignItems: 'center'
    },
    loginButton: {
        backgroundColor: Colors.getBlue(900),
        paddingVertical: 10,
        borderRadius: 5,
        width: 125
    },
    loginButtonText: {
        textAlign: 'center',
        color: Colors.getNeutral(100)
    },
    registerSpacer: {
        height: 175
    },
    registerButtonContainer: {
        borderBottomColor: Colors.getNeutral(100),
        borderBottomWidth: 1
    },
    formError: {
        color: Colors.getYellow(),
        textAlign: 'center'
    },
    formErrorContainer: {
        height: 20,
        marginBottom: 25
    }
});
