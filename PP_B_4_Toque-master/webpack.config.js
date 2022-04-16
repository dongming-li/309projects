module.exports = {
    entry: './app/client/index.jsx',
    output: {
        filename: 'bundle.js',
        path: __dirname +'/app/public/assets'
    },
    module: {
        loaders: [
            {
                test: /\.jsx$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.scss$/,
                loaders: ['style-loader', 'css-loader', 'sass-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.css$/,
                loaders: ['style-loader', 'css-loader'],
                // exclude: /node_modules/
            }
        ]
    }
};
