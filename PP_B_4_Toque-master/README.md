# Toque
## Development

### Run the project
1. Make sure you have docker & docker-compose installed and that docker is running.
1. Run `docker-compose -f docker-compose.dev.yml build` (if necessary, see notes)
    - If you get issues while building, try deleting the `node_modules` folder.
1. Run `docker-compose -f docker-compose.dev.yml up`
1. Run `docker-compose -f docker-compose.dev.yml exec web npm run db:migrate`
1. Access the project at `localhost:3000`
1. Run `docker-compose -f docker-compose.dev.yml down` when you are done.

#### Things to note:
- You only have to run `docker-compose -f docker-compose.dev.yml build` when pulling new code or altering `package.json`, not when editing code.
- You only have to run `docker-compose -f docker-compose.dev.yml exec web node_modules/.bin/sequelize db:migrate` when pulling new code or when you've created  migration to be ran
- You must `down`, `up`, and `db:migrate` again after altering server files
- Changes the client side code will cause webpack to recompile (you need to reload the page to see changes)

### Testing
In order to test the project, you should run `docker-compose -f docker-compose.dev.yml exec web npm test`.

### Database
We are using the [`sequelize-cli`](https://github.com/sequelize/cli) for migrations.

#### Editing models
When editing database models, you need to create a migration script so that other users databases reflect your changes. To generate a skeleton migration file (must have node installed and have ran npm install), run the command with a descriptive name:

`node_modules/.bin/sequelize migration:generate --name your-name-here`

Then, start building your migration script. Look at previous examples and [here](http://docs.sequelizejs.com/class/lib/query-interface.js~QueryInterface.html) for information.

