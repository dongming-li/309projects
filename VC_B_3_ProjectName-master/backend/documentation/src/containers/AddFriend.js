import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Add Friend',
    description: 'Adds a friend to the given user',
    url: '/addFriend',
    method: 'POST',
    requestBody: beautify(
        `{
            user: [string],
            friend: [string]
        }`
    ),
    successResponse: {
        statusCode: 200
    },
    example: beautify(
        `fetch('/deleteMessage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                user: 'aaron.johnson@test.com',
                friend: 'maxtaylor3@test.com'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
