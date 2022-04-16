import express from 'express';
import bodyParser from 'body-parser';

import Database from './helpers/database';
import { MessageErrors } from './helpers/error-messages';

const app = express();
const knex = require('knex')(require('./knexfile'));

app.use(bodyParser.json());

app.get('/', (request, response) => {
    response.send('Walk Me Home backend is running!');
});

app.post('/login', (request, response) => {
    knex('user').where({email: request.body.email})
        .then(([user]) => {
            if (user && user.password === request.body.password)
                response.send({email: request.body.email});
            else if (!user)
                response.send({error: 'email'})
            else
                response.send({error: 'password'});
        }).error(error => {
            console.log(error);
        });
});

app.post('/getFriends', (request, response) => {
    knex('friends').select('friend').where({user: request.body.email})
        .then(friends => {
            response.send(friends);
        }).error(error => {
            console.log(error);
            response.send([]);
        });
});

app.post('/getFriendInfo', (request, response) => {
    knex('user').where({email: request.body.email})
        .then(([user]) => {
            const userInfo = {
                email: user.email,
                firstName: user.firstName,
                lastName: user.lastName,
                status: user.status
            }

            response.send(userInfo);
        }).error(error => {
            console.log(error);
        });
});

app.post('/register', (request, response) => {
    knex('user').where({email: request.body.email})
        .then(users => {
            if (users.length)
                response.send({
                    error: true
                });
            else {
                const {
                    firstName,
                    lastName,
                    email,
                    password
                } = request.body;

                knex('user').insert({
                    email,
                    firstName,
                    lastName,
                    password,
                    status: 'Offline'
                }).then(() => {
                    response.send({
                        email,
                        error: false
                    });
                }).error(error => {
                    console.log(error);
                });
            }
        }).error(error => {
            console.log(error);
        });
});

app.post('/userLocation', (request, response) => {
    console.log('userlocation is called');
    console.log(request.body);

    knex('location').insert({
        user: 'testUserIthink',
        lat: request.body.latitude,
        lng: request.body.longitude,
        // endLocationLat : '' + destLat,
        // endLocationLng : '' + destLong
    });
});

app.post('/sendMessage', (request, response) => {
    const {
        sendee,
        type,
        sender,
        message,
        timestamp
    } = request.body;

    knex('messages').where({
        sendee: sendee,
        type: type,
        sender: sender
    }).then(messages => {
        if (messages.length) {
            response.send({
                error: true,
                message: MessageErrors.duplicateMessage
            });
        } else {
            knex('user').where({
                email: sendee
            }).then(users => {
                if (users.length) {
                    knex('messages').insert({
                        sendee,
                        type,
                        sender,
                        message,
                        timestamp
                    }).then(() => {
                        response.send({
                            error: false
                        });
                    }).error(error => {
                        console.log(error);
                    })
                } else
                    response.send({
                        error: true,
                        message: MessageErrors.userDoesNotExist
                    });
            }).error(error => {
                console.log(error);
            });
        }
    }).error(error => {
        console.log(error);
    })
});

app.post('/retrieveMessages', (request, response) => {
    knex('messages').where({sendee: request.body.user})
        .then(messages => {
            response.send(messages);
        }).error(error => {
            console.log(error);
        });
});

app.post('/deleteMessage', (request, response) => {
    const {
        type,
        sender,
        sendee,
        timestamp
    } = request.body;

    knex('messages').where({
        type: type,
        sender: sender,
        sendee: sendee,
        timestamp: timestamp
    }).delete().then(() => {
        response.sendStatus(200);
    }).error(error => {
        console.log(error);
    });
});

app.post('/addFriend', (request, response) => {
    const {
        user,
        friend
    } = request.body;

    knex('friends').insert({
        user,
        friend
    }).then(() => {
        response.sendStatus(200);
    }).error(error => {
        console.log(error);
    });
});

app.post('/updateUserStatus', (request, response) => {
    const {
        user,
        status
    } = request.body;

    knex('user').where({
        email: user
    }).update({
        status
    }).then(() => {
        response.sendStatus(200);
    }).error(error => {
        console.log(error);
    });
});

app.listen(8090, () => {
    console.log('[DEBUG] App listening on port 8090');
})
