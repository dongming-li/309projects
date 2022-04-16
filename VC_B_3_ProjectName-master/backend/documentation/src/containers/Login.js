import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Login',
    description: 'Authenticates a user',
    url: '/login',
    method: 'POST',
    requestBody: beautify(`{ email: [string], password: [string] }`),
    successResponse: {
        body: beautify(`{ email: [string] }`)
    },
    errorResponse: {
        statusCode: 401
    },
    example: beautify(
        `fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                email: 'testuser@test.com',
                password: 'simplepassword1'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
