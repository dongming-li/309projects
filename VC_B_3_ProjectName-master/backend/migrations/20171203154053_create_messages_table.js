exports.up = function(knex, Promise) {
    return knex.schema.createTable('messages', t => {
        t.increments('id').primary(),
        t.string('sendee').notNullable(),
        t.string('type').notNullable(),
        t.string('sender').notNullable(),
        t.string('message'),
        t.string('timestamp').notNullable()
    });
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTableIfExists('messages');
};
