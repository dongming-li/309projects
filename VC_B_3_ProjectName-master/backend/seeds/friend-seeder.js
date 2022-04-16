exports.seed = function(knex, Promise) {
    return knex('friends').del()
        .then(() => {
            return knex('friends').insert([
                {
                    user: 'griffinjared1388@gmail.com',
                    friend: 'testemail@test.co'
                },
                {
                    user: 'griffinjared1388@gmail.com',
                    friend: 'anothertest@test.co'
                }
            ]);
        });
};
