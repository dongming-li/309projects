import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Test Route',
    description: 'Test route used by developers to see if backend is online',
    url: '/',
    method: 'GET',
    successResponse: {
        statusCode: 200,
        body: 'Walk Me Home backend is running!'
    },
    example: beautify(`fetch('/', { method: 'GET' });`)
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
