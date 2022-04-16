FROM node:boron
#Set working directory
WORKDIR /toque
#Copy package.json and run npm install
#The cached node_modues will be used if no changes are made
COPY package.json .
RUN npm install
COPY webpack.config.js .babelrc ./
COPY ./app/client ./app/client
RUN npm run webpack
#Copy remaining files over (this is done last so previous commands can be cached)
COPY . .
#Run program when app launches
CMD npm test
