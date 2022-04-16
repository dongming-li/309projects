import {
    LOGIN_REQUEST,
    LOGIN_SUCCESS,
    LOGIN_FAILURE,
    REGISTER_REQUEST,
    REGISTER_SUCCESS,
    REGISTER_FAILURE,
    LOGOUT_REQUEST,
    LOGOUT_SUCCESS,
    LOGOUT_FAILURE,
    AUTH_REQUEST,
    AUTH_FAILURE,
    AUTH_SUCCESS } from '../../actionTypes';

const INITIAL_STATE = {
    isAuthenticating: false,
    isAuthenticated: false,
    isLoggingIn: false,
    isLoggedIn: false,
    isRegistered: false,
    isRegistering: false,
    user: null
};

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case LOGIN_REQUEST:
            return {
                ...state,
                isLoggingIn: true
            };
        case AUTH_REQUEST:
            return {
                ...state,
                isAuthenticating: true,
            };
        case LOGIN_SUCCESS:
            return {
              ...state,
              isLoggedIn: true,
              isLoggingIn: false,
              user: action.user
            };
        case AUTH_SUCCESS:
            return {
                ...state,
                isAuthenticated: true,
                isAuthenticating: false,
                user: action.user
            };
        case LOGIN_FAILURE:
            return {
              ...state,
              isLoggedIn: false,
              isLoggingIn: false,
              user: null
            };
        case AUTH_FAILURE:
            return {
                ...state,
                isAuthenticating: false
            };
        case LOGOUT_REQUEST:
            return state;
        case LOGOUT_SUCCESS:
            return {
                ...state,
                isAuthenticated: false,
                user: undefined
            };
        case LOGOUT_FAILURE:
            return state;
        //TODO Registering doesn't alter the state currently
        case REGISTER_REQUEST:
        return {
            ...state,
            isRegistering: true
        };
        case REGISTER_SUCCESS:
        return {
            ...state,
            isRegistered: true,
            isRegistering: false
        };
        case REGISTER_FAILURE:
        return {
          ...state,
          isRegistered: false,
          isRegistering: false
        };
        default:
            return state;

    }
}
