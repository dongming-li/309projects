import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import thunkMiddleware from 'redux-thunk';

import App from './components/App.jsx';
import reducer from './reducers';

const store = createStore(
    reducer,
    applyMiddleware(
        thunkMiddleware
    )
);

import Cookie from 'universal-cookie';

let cookie = new Cookie();
cookie.set('token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiaWF0IjoxNTEyNDIwNjc5fQ.BV6Aa5XA718FcfQQEUXDpPr59e67tqKTvsLEqsFmAKk');

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root'));
