import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Send Message',
    description: 'Sends a message from one user to another',
    url: '/sendMessage',
    method: 'POST',
    requestBody: beautify(
        `{
            sendee: [string],
            type: [string],
            sender: [string],
            message: [string (optional)],
            timestamp: [string]
        }`
    ),
    successResponse: {
        body: beautify(`{ error: false }`)
    },
    errorResponse: {
        body: beautify(`{ error: true, message: [string] }`)
    },
    example: beautify(
        `fetch('/sendMessage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                sendee: 'aaron.johnson@test.com,
                type: 'friend-request',
                sender: 'maxtaylor3@test.com',
                timestamp: '1512603196753'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
