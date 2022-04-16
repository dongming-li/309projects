/**
 * @module app
 */

import React, { Component } from 'react';
import {
    View,
    StyleSheet
} from 'react-native';

import Menu from './components/menu';
import Login from './components/login';
import Register from './components/register';

import AppView from './helpers/view';

/**
 * App container component
 */
export default class App extends Component {
    /**
     * Creates the app
     * @param {object} props Props passed to the component
     */
    constructor(props) {
        super(props);

        this.state = {
            user: '',
            view: AppView.login
        };

        this.changeState = this.changeState.bind(this);
        this.chooseView = this.chooseView.bind(this);
        this.makeAuthed = this.makeAuthed.bind(this);
        this.logout = this.logout.bind(this);
        this.changeView = this.changeView.bind(this);
        this.switchToLoginView = this.switchToLoginView.bind(this);
        this.switchToRegisterView = this.switchToRegisterView.bind(this);

        console.log('[DEBUG] Starting app');
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
     * Authenticates the user
     * @param {string} userEmail User's email
     */
    makeAuthed(userEmail) {
        this.changeState('user', userEmail);
        this.changeView(AppView.main);
    }

    /**
     * Logs the user out of the app
     */
    logout() {
        this.changeView(AppView.login);
        this.changeState('user', '');
    }

    /**
     * Changes the view the app is displaying
     * @param {string} view View to display
     */
    changeView(view) {
        this.changeState('view', view);
    }

    /**
     * Switch the app to the register view
     */
    switchToRegisterView() {
        this.changeView(AppView.register);
    }

    /**
     * Switch the app to the login view
     */
    switchToLoginView() {
        this.changeView(AppView.login);
    }

    /**
     * Display the current view inside of app's state
     */
    chooseView() {
        switch(this.state.view) {
            case AppView.main:
                return (
                    <Menu
                        email={this.state.user}
                        logout={this.logout}
                    />
                );
            case AppView.register:
                return (
                    <Register
                        authUser={this.makeAuthed}
                        switchToLoginView={this.switchToLoginView}
                    />
                );
            default:
                return (
                    <Login
                        authUser={this.makeAuthed}
                        switchToRegisterView={this.switchToRegisterView}
                    />
                );
        }
        
    }

    /**
     * Renders the component
     */
    render() {
        return this.chooseView();
    }
}
