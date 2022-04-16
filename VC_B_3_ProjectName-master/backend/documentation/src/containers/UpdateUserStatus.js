import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Update User Status',
    description: 'Updates the user\'s status to correspond to their status on the app',
    url: '/updateUserStatus',
    method: 'POST',
    requestBody: beautify(
        `{
            user: [string],
            status: [string]
        }`
    ),
    successResponse: {
        statusCode: 200
    },
    example: beautify(
        `fetch('/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                sender: 'aaron.johnson@test.com',
                status: 'Online'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
