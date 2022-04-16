import React from 'react';

const styles = {
    homeLink: {
        fontWeight: 'bold'
    }
}

export default () => (
    <div>
        <nav>
            <a href="../index.html" style={styles.homeLink}>Walk Me Home API</a>
        </nav>
        <h1>Error 404!</h1>
        <h3>The page you are looking for does not exist</h3>
    </div>
);
