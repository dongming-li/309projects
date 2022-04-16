import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Register',
    description: 'Registers a new user account',
    url: '/register',
    method: 'POST',
    requestBody: beautify(
        `{
            firstName: [string],
            lastName: [string],
            email: [string],
            password: [string]
        }`
    ),
    successResponse: {
        body: beautify(`{ email: [string], error: false }`)
    },
    errorResponse: {
        body: beautify(`{ error: true }`)
    },
    example: beautify(
        `fetch('/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                firstName: 'Aaron',
                lastName: 'Johnson',
                email: 'aaron.johnson@test.com',
                password: 'pass1234'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
