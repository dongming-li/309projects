import React, { Component } from 'react';

const styles = {
    line: {
        borderColor: '#0080FF',
        borderStyle: 'solid'
    },
    title: {
        color: '#0080FF',
        fontFamily: 'Roboto'
    },
    inlineCode: {
        fontFamily: 'Roboto Mono',
        color: '#0080FF'
    },
    indentedText: {
        marginLeft: 20,
        marginRight: 20,
        fontFamily: 'Roboto'
    },
    codeBlock: {
        fontFamily: 'Roboto Mono',
        color: '#0080FF',
        padding: 10,
        marginLeft: 40,
        marginRight: 40,
        marginTop: 10,
        marginBottom: 10,
        borderRadius: 5,
        borderWidth: 2,
        borderColor: '#0080FF',
        borderStyle: 'solid'
    },
    homeLink: {
        fontWeight: 'bold',
        fontFamily: 'Roboto',
        color: '#FFFFFF',
        textDecoration: 'none',
        display: 'block',
        paddingTop: '1rem',
        paddingBottom: '1rem',
        marginLeft: '20%'
    },
    standard: {
        fontFamily: 'Roboto'
    },
    nav: {
        backgroundColor: '#0080FF',
        height: '3.2rem',
        width: '100%'
    },
    container: {
        marginLeft: '30%',
        marginRight: '30%'
    }
};

const printMultilineString = string => {
    return string.split('\n').map(line => {
        return <div>{line.replace(/ /g, '\u00a0')}</div>
    });
};

const displayResponse = response => {
    if (response) {
        return (
            <div style={ styles.indentedText }>
                <p style={ styles.indentedText }>
                    { response.statusCode ? 'Status Code: ' : '' }
                    <span style={ styles.inlineCode }>{ response.statusCode ? response.statusCode : '' }</span>
                </p>
                <p style={ styles.indentedText }>
                    {'Response Body: '}
                    <span style={ styles.inlineCode }>{ !response.body ? 'None' : '' }</span>
                    <div style={ response.body ? styles.codeBlock : {} }>{ response.body ? printMultilineString(response.body) : '' }</div>
                </p>
            </div>
        );
    }

    return '';
};

export default class Documentation extends Component {
    render() {
        const {
            title,
            description,
            url,
            method,
            requestBody,
            successResponse,
            errorResponse,
            example
        } = this.props.docs;

        return (
            <div>
                <nav style={ styles.nav }>
                    <a href="../index.html" style={styles.homeLink}>Walk Me Home API</a>
                </nav>
                <div style={ styles.container }>
                    <h2 style={ styles.title }>{title}</h2>
                    <p style={ styles.standard }>{ description }</p>

                    <hr style={ styles.line } noshade/>

                    <p style={ styles.standard }>
                        {'Endpoint: '}
                        <span style={ styles.inlineCode }>{url}</span>
                    </p>

                    <p style={ styles.standard }>
                        {'Method: '}
                        <span style={ styles.inlineCode }>{method}</span>
                    </p>

                    <p style={ styles.standard }>
                        {'Request Body: '}
                        <span style={ styles.inlineCode }>{ !requestBody ? 'None' : '' }</span>
                    </p>
                    <div style={ requestBody ? styles.codeBlock : {} }>{ requestBody ? printMultilineString(requestBody) : '' }</div>

                    <p style={ styles.standard }>
                        {'Success Response: '}
                        <span style={ styles.inlineCode }>{ !successResponse ? 'None' : '' }</span>
                    </p>
                    { displayResponse(successResponse) }

                    <p style={ styles.standard }>
                        {'Error Response: '}
                        <span style={ styles.inlineCode }>{ !errorResponse ? 'None' : '' }</span>
                    </p>
                    { displayResponse(errorResponse) }

                    <p style={ styles.standard }>{'Example: '}</p>
                    <div style={ styles.codeBlock }>{ printMultilineString(example) }
                    </div>
                </div>
            </div>
        )
    }
}
