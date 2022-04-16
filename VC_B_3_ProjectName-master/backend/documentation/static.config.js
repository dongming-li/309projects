import React, { Component } from 'react';

export default {
    getSiteProps: () => ({
        title: 'WalkMeHome API',
    }),
    getRoutes: async () => {
        return [
            {
                path: '/',
                component: 'src/containers/Home',
            },
            {
                path: '/running',
                component: 'src/containers/Running'
            },
            {
                path: '/login',
                component: 'src/containers/Login'
            },
            {
                path: '/getFriends',
                component: 'src/containers/GetFriends'
            },
            {
                path: '/getFriendInfo',
                component: 'src/containers/GetFriendInfo'
            },
            {
                path: '/register',
                component: 'src/containers/Register'
            },
            {
                path: '/userLocation',
                component: 'src/containers/UserLocation'
            },
            {
                path: '/sendMessage',
                component: 'src/containers/SendMessage'
            },
            {
                path: '/retrieveMessages',
                component: 'src/containers/RetrieveMessages'
            },
            {
                path: '/deleteMessage',
                component: 'src/containers/DeleteMessage'
            },
            {
                path: '/addFriend',
                component: 'src/containers/AddFriend'
            },
            {
                path: '/updateUserStatus',
                component: 'src/containers/UpdateUserStatus'
            },
            {
                is404: true,
                component: 'src/containers/404',
            },
        ]
    },
    Document: class CustomHtml extends Component {
        render () {
            const { Html, Head, Body, children, renderMeta } = this.props
        
            return (
                <Html>
                    <Head>
                        <meta charSet="UTF-8" />
                        <meta name="viewport" content="width=device-width, initial-scale=1" />
                        {renderMeta.styleTags}
                        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
                        <link href="https://fonts.googleapis.com/css?family=Roboto+Mono" rel="stylesheet"/>
                    </Head>
                    <Body style={ { margin: 0 } }>{children}</Body>
                </Html>
            )
        }
      }
}
