'use strict';
module.exports = (sequelize, DataTypes) => {
    const Status = sequelize.define('Status', {
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            type: DataTypes.TEXT,
            allowNull: false
        }
    });
    Status.associate = (models) => {
        Status.belongsTo(models.User, {
            foreignKey: 'user_id',
            //When user is deleted, so are their recipes.
            onDelete: 'CASCADE',
            allowNull: false
        });
    };
    return Status;
};