/**
 * @module register
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
import { RegisterFormErrors } from '../helpers/error-messages';

/**
 * Register view component
 */
export default class Register extends Component {
    /**
     * Creates the register view
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            passwordRepeat: '',
            formError: ''
        }

        this.registerUser = this.registerUser.bind(this);
        this.changeState = this.changeState.bind(this);
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
     * Validates the values inside of the register form
     */
    validateForm() {
        const formInfo = {
            ok: false,
            error: ''
        };

        const {
            firstName,
            lastName,
            email,
            password,
            passwordRepeat
        } = this.state;

        if (!firstName.length) {
            formInfo.error = RegisterFormErrors.firstNameMissing;
        } else if (!lastName.length) {
            formInfo.error = RegisterFormErrors.lastNameMissing;
        } else if (!email.length) {
            formInfo.error = RegisterFormErrors.emailMissing;
        } else if (!password.length) {
            formInfo.error = RegisterFormErrors.passwordMissing;
        } else if (!passwordRepeat.length) {
            formInfo.error = RegisterFormErrors.passwordRepeatMissing;
        } else if (!email.includes('@')) {
            formInfo.error = RegisterFormErrors.emailNotValid;
        } else if (password !== passwordRepeat) {
            formInfo.error = RegisterFormErrors.passwordsNotMatching;
        } else {
            formInfo.ok = true;
        }

        return formInfo;
    }

    /**
     * Registers a new user
     */
    registerUser() {
        const formDataValidation = this.validateForm()
        const {
            firstName,
            lastName,
            email,
            password
        } = this.state;
        const { authUser } = this.props;

        if (formDataValidation.ok) {

            fetch(Routes.register, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    firstName,
                    lastName,
                    email,
                    password
                })
            }).then(response => {
                return response.json();
            }).then(body => {
                if (!body.error)
                    authUser(email);
                else {
                    console.log(RegisterFormErrors.userExists);
                    this.changeState('formError', RegisterFormErrors.userExists);
                }
            });
        } else {
            this.changeState('formError', formDataValidation.error);
        }
    }

    /**
     * Renders the component
     */
    render() {
        const { switchToLoginView } = this.props;

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
                <TextInput
                    style={styles.input}
                    placeholder= 'First Name'
                    placeholderTextColor={Colors.getNeutral(100)}
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    onChangeText={value => this.changeState('firstName', value)}
                />
                <TextInput
                    style={styles.input}
                    placeholder= 'Last Name'
                    placeholderTextColor={Colors.getNeutral(100)}
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    onChangeText={value => this.changeState('lastName', value)}
                />
                <TextInput
                    style={styles.input}
                    placeholder= 'Email'
                    placeholderTextColor={Colors.getNeutral(100)}
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    keyboardType='email-address'
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
                <TextInput
                    style={styles.input}
                    placeholder='Repeat Password'
                    placeholderTextColor={Colors.getNeutral(100)}
                    secureTextEntry
                    selectionColor={Colors.getNeutral(100)}
                    underlineColorAndroid={Colors.getNeutral(100)}
                    onChangeText={value => this.changeState('passwordRepeat', value)}
                />
                <View
                    style={styles.registerSpacer}
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
                <View
                    style={styles.registerButtonContainer}
                >
                    <TouchableOpacity
                        style={styles.registerButton}
                        onPress={this.registerUser}
                    >
                        <Text
                            style={styles.registerButtonText}
                        >
                            REGISTER
                        </Text>
                    </TouchableOpacity>
                </View>
                <View
                    style={styles.loginSpacer}
                />
                <View
                    style={styles.registerButtonContainer}
                >
                    <TouchableOpacity
                        onPress={switchToLoginView}
                    >
                        <View
                            style={styles.loginButtonContainer}
                        >
                            <Text
                                style={styles.registerButtonText}
                            >
                                Login?
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
        height: 25
    },
    input: {
        height: 40,
        backgroundColor: Colors.getBlue(900),
        marginBottom: 10,
        paddingHorizontal: 10,
        borderRadius: 5,
        color: Colors.getNeutral(100)
    },
    registerSpacer: {
        height: 15
    },
    registerButtonContainer: {
        flex: 1,
        flexDirection: 'column',
        alignItems: 'center'
    },
    registerButton: {
        backgroundColor: Colors.getBlue(900),
        paddingVertical: 10,
        borderRadius: 5,
        width: 125
    },
    registerButtonText: {
        textAlign: 'center',
        color: Colors.getNeutral(100),
    },
    loginSpacer: {
        height: 45
    },
    loginButtonContainer: {
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
