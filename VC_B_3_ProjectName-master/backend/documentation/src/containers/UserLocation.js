import React from 'react';
import { getSiteProps } from 'react-static';

const beautify = require('js-beautify').js_beautify;

import Documentaton from './Documentation';

const docs = {
    title: 'Save User Location',
    description: 'Saves the current user\'s location',
    url: '/userLocation',
    method: 'POST',
    requestBody: beautify(`{ latitude: [number], longitude: [number] }`),
    example: beautify(
        `fetch('/userLocation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: {
                latitude: '50.5033',
                longitude: '-0.1276'
            }
        });`
    )
}

export default getSiteProps(() => <Documentaton docs={docs}/>);
