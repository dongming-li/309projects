
exports.up = function(knex, Promise) {
    return knex.schema.createTable('location', t => {
        t.increments('id').primary(),
            t.string('user').notNullable(),
            t.string('lat').notNullable(),
            t.string('lng').notNullable()
    });
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTableIfExists('location');
};
