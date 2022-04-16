const tableName = 'Users';

module.exports = {
    up: (queryInterface, Sequelize) =>
        queryInterface.createTable(tableName, {
            id: {
                allowNull: false,
                autoIncrement: true,
                primaryKey: true,
                type: Sequelize.INTEGER
            },
            email: {
                type: Sequelize.STRING,
                unique: true,
                allowNull: false
            },
            password: {
                type: Sequelize.STRING
            },
            username: {
                type: Sequelize.STRING,
                unique: true,
                allowNull: false
            },
            userType: {
                type: Sequelize.ENUM,
                values: ['default', 'moderator', 'admin'],
                allowNull: false
            },
            createdAt: {
                allowNull: false,
                type: Sequelize.DATE
            },
            updatedAt: {
                allowNull: false,
                type: Sequelize.DATE
            }
        }),
    down: (queryInterface, Sequelize) =>
        queryInterface.dropTable(tableName),
};
