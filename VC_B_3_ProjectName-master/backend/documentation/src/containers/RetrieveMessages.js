import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Retrieve Messages',
    description: 'Retrieves a user\'s messages',
    url: '/retrieveMessages',
    method: 'POST',
    requestBody: beautify(
        `{
            user: [string]
        }`
    ),
    successResponse: {
        body: beautify(`{ [array (message objects)] }`)
    },
    example: beautify(
        `fetch('/retrieveMessages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                user: 'aaron.johnson@test.com',
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
