import React from 'react';
import { Router } from 'react-static';
import Routes from 'react-static-routes';

const styles = {
    homeLink: {
        fontWeight: 'bold'
    }
}

export default () => (
    <Router>
        <div>
            <div className="content">
                <Routes />
            </div>
        </div>
    </Router>
);
