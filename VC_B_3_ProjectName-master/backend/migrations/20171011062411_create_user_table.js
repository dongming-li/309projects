exports.up = function(knex, Promise) {
    return knex.schema.createTable('user', t => {
        t.string('email').unique().primary().notNullable(),
        t.string('firstName').notNullable(),
        t.string('lastName').notNullable(),
        t.string('password').notNullable(),
        t.string('status').notNullable()
    });
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTableIfExists('user');
};
