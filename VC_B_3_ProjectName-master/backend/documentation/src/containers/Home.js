import React from 'react';
import { getSiteProps } from 'react-static';

const styles = {
    title: {
        marginTop: '4rem',
        color: '#0080FF',
        fontFamily: 'Roboto',
        textAlign: 'center'
    },
    subtitle: {
        color: '#0080FF',
        fontFamily: 'Roboto',
        textAlign: 'center',
        fontWeight: 'normal'
    },
    docLink: {
        display: 'block',
        textAlign: 'center',
        textDecoration: 'underline',
        fontFamily: 'Roboto Mono',
        paddingTop: 10,
        color: '#0080FF'
    },
    homeLink: {
        fontWeight: 'bold',
        fontFamily: 'Roboto',
        color: '#FFFFFF',
        textDecoration: 'none',
        display: 'block',
        paddingTop: '1rem',
        paddingBottom: '1rem',
        marginLeft: '20%'
    },
    nav: {
        backgroundColor: '#0080FF',
        height: '3.2rem',
        width: '100%'
    },
    linkContainer: {
        marginLeft: '43%',
        marginRight: '43%'
    }
}

export default getSiteProps(() => (
    <div>
        <nav style={ styles.nav }>
            <a href="./index.html" style={ styles.homeLink }>Walk Me Home API</a>
        </nav>
        <h1 style={ styles.title }>Walk Me Home API</h1>
        <h2 style={ styles.subtitle }>Routes:</h2>
        <div style={ styles.linkContainer }>
            <a href="./running/index.html" style={ styles.docLink }>/</a>
            <a href="./login/index.html" style={ styles.docLink }>/login</a>
            <a href="./getFriends/index.html" style={ styles.docLink }>/getFriends</a>
            <a href="./getFriendInfo/index.html" style={ styles.docLink }>/getFriendInfo</a>
            <a href="./register/index.html" style={ styles.docLink }>/register</a>
            <a href="./userLocation/index.html" style={ styles.docLink }>/userLocation</a>
            <a href="./sendMessage/index.html" style={ styles.docLink }>/sendMessage</a>
            <a href="./retrieveMessages/index.html" style={ styles.docLink }>/retrieveMessages</a>
            <a href="./deleteMessage/index.html" style={ styles.docLink }>/deleteMessage</a>
            <a href="./addFriend/index.html" style={ styles.docLink }>/addFriend</a>
            <a href="./updateUserStatus/index.html" style={ styles.docLink }>/updateUserStatus</a>
        </div>
    </div>
));
