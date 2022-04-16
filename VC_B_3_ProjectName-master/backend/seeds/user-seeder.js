exports.seed = function(knex, Promise) {
    return knex('user').del()
        .then(() => {
            return knex('user').insert([
                {
                    email: 'griffinjared1388@gmail.com',
                    firstName: 'Jared',
                    lastName: 'Griffin',
                    password: 'test1234',
                    status: 'Online'
                },
                {
                    email: 'anothertest@test.co',
                    firstName: 'Amanda',
                    lastName: 'Lee',
                    password: 'worseword',
                    status: 'Walking'
                },
                {
                    email: 'testemail@test.co',
                    firstName: 'Kyle',
                    lastName: 'Long',
                    password: 'testword',
                    status: 'Offline'
                }
            ]);
        });
};
