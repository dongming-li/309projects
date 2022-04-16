import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Delete Message',
    description: 'Deletes a user\'s message',
    url: '/deleteMessage',
    method: 'POST',
    requestBody: beautify(
        `{
            type: [string],
            sender: [string],
            sendee: [string],
            timestamp: [string]
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
                type: 'friend-request-accepted',
                sender: 'aaron.johnson@test.com',
                sendee: 'maxtaylor3@test.com',
                timestamp: '1512603207788'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
