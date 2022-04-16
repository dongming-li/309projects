FROM node:boron

WORKDIR /toque

COPY package.json /toque

RUN npm install
EXPOSE 80
ENV NODE_ENV development

CMD npm start & npm run webpack-dev
