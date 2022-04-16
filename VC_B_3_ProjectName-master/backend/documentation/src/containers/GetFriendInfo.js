import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Get Friend Information',
    description: 'Retrieves detailed user information',
    url: '/getFriendInfo',
    method: 'POST',
    requestBody: beautify(`{ email: [string] }`),
    successResponse: {
        body: beautify(
            `{
                email: [string],
                firstName: [string],
                lastName: [string],
                status: [string]
             }`
        )
    },
    example: beautify(
        `fetch('/getFriendInfo', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                email: 'testfriend@test.com'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
