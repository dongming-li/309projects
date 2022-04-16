FROM node:boron
#Set working directory
WORKDIR /toque
#Copy package.json and run npm install
#The cached node_modues will be used if no changes are made
COPY package.json .
RUN npm install
#Copy client files over, webpack will not run if no changes
COPY ./app/client ./app/client
COPY webpack.config.js .babelrc ./
RUN npm run webpack

#Make port 80 available to the world outside the container
EXPOSE 80
ENV NODE_ENV production
COPY . .
# Get args
ARG DATABASE_URL
ARG SECRET
#Set environment variables
ENV DATABASE_URL $DATABASE_URL
ENV SECRET $SECRET
RUN node_modules/.bin/sequelize db:migrate --url $DATABASE_URL
RUN npm prune --production

#Copy remaining files over (this is done last so previous commands can be cached)

#Run program when app launches
CMD npm start
