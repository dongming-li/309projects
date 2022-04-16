import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Get Friends',
    description: 'Gets a list of all the friends of a user',
    url: '/getFriends',
    method: 'POST',
    requestBody: beautify(`{ email: [string] }`),
    successResponse: {
        body: beautify(`{ friends: [array (objects)] }`)
    },
    example: beautify(
        `fetch('/getFriends', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                email: 'testuser@test.com'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
