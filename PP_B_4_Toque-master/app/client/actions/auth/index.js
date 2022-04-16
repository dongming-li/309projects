import {post, get} from 'axios';
import Cookies from 'universal-cookie';
const cookie = new Cookies();
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
    AUTH_SUCCESS,
    AUTH_FAILURE
} from '../../actionTypes';

/**
 * Indicates a login was requested
 * @param {*} identifier
 * @return {object}  contains action and identifier
 */
function loginRequest(identifier) {
    return {
        type: LOGIN_REQUEST,
        identifier
    }
}

/**
 * Indicates login success
 * @param {user} user
 * @return {object}  containing action and user
 */
function loginSuccess(user) {
    return {
        type: LOGIN_SUCCESS,
        user
    }
}

/**
 * Indicates login failure
 * @param {error} error
 * @return {object} containing action and error
 */
function loginFailure(error) {
    return {
        type: LOGIN_FAILURE,
        error
    }
}

/**
 * Indicates a registration request
 * @param {string} username
 * @param {string} email
 * @param {string} password
 * @return {object} containing action, username, email, password
 */
function registerRequest(username, email, password) {
    return {
        type: REGISTER_REQUEST,
        username,
        email,
        password
    }
}

/**
 * Indicates registration success
 * @param {string}  username
 * @param {string}  email
 * @param {string}  password
 * @return {object}  contains action, username, email, password
 */
function registerSuccess(username, email, password) {
    return {
        type: REGISTER_SUCCESS,
        username,
        email,
        password
    }
}

/**
 * Indicates registration failure
 * @param {error} error
 * @return {object} contains action and error
 */
function registerFailure(error) {
    return {
        type: REGISTER_FAILURE,
        error
    }
}

/**
 * Indates a logout request
 * @return {object} contains action
 */
function logoutRequest() {
    return {
        type: LOGOUT_REQUEST,
    }
}

/**
 * Indicates logout succeess
 * @return {object} contains action
 */
function logoutSuccess() {
    return {
        type: LOGOUT_SUCCESS,
    }
}

/**
 * Indicates logout failure
 * @param {error} error
 * @return {object} contains action and error
 */
function logoutFailure(error) {
    return {
        type: LOGOUT_FAILURE,
        error
    }
}

/**
 * Indicates an auth request
 * @returns {object} contains action
 */
function authRequest() {
    return {
        type: AUTH_REQUEST,
    }
}

/**
 * Indicates auth success
 * @param {user} user
 * @return {object} contains user and action
 */
function authSuccess(user) {
    return {
        type: AUTH_SUCCESS,
        user
    }
}

/**
 * Indicates auth failure
 * @param {error} error
 * @return {object} contains action and error
 */
function authFailure(error) {
    return {
        type : AUTH_FAILURE,
        error
    }
}
/**
 *
 * @returns {Promise}
 */
export function authenticate() {
    const token = cookie.get('token');
    return (dispatch) => {
        dispatch(authRequest());
        if(!token){
            return Promise.resolve(dispatch(authFailure({error: "There is no auth token!"})));
        }
        return post('/api/authenticate',
            null, //No Body Parameters
            {
                headers: {
                    toque_token: token,
                    'Content-Type': 'application/json'
                }
            })
            .then((response) => {
                dispatch(authSuccess(response.data.user));
            })
            .catch((error) => {
                dispatch(authFailure(error));
            })
    }
}
/**
 * Login a user
 * @param {string}  identifier
 * @param {string}  password
 * @returns {Promise}
 */
export function login(identifier, password) {
    return (dispatch) => {
        dispatch(loginRequest(identifier));
        return post('/api/login', {
                identifier,
                password
            })
            .then((response) => {
                cookie.set('token', response.data.token);
                dispatch(loginSuccess(response.data.user));
            })
            .catch((error) => {
                dispatch(loginFailure(error));
            })
    }
}
/**
 * Register a new user
 * @param {string}  username
 * @param {string}  email
 * @param {string}  password
 * @returns {Promise}
 */
export function register(username, email, password) {
    return (dispatch) => {
        dispatch(registerRequest(username, email, password));
        return post('/api/register', {
            username,
            email,
            password
        })
            .then((response) => {
                dispatch(registerSuccess());
            })
            .catch((error) => {
                dispatch(registerFailure(error));
                console.log(error);
            })
    }
}
/**
 * Logout a logged in user
 * @returns {Promise}
 */
export function logout() {
    return (dispatch) => {
        dispatch(logoutRequest());
        return get('/api/logout')
            .then((response) => {
                cookie.remove('token');
                dispatch(logoutSuccess());
            })
            .catch((error) => {
                dispatch(logoutFailure(error));
            })
    }
}
