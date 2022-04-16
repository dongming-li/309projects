exports.up = function(knex, Promise) {
      return knex.schema.createTable('friends', t => {
          t.increments('id').primary(),
          t.string('user').notNullable(),
          t.string('friend').notNullable()
      });
};

exports.down = function(knex, Promise) {
    return knex.schema.dropTableIfExists('friends');
};
