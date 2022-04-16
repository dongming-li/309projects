/* @flow */
const bcrypt = require('bcrypt');

module.exports = (sequelize, DataTypes) => {
    const User = sequelize.define('User', {
        username: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isEmail: true
            }
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        userType: {
            type: DataTypes.ENUM,
            values: ['default', 'moderator', 'admin'],
            allowNull: false
        },
        createdAt: {
            allowNull: false,
            type: DataTypes.DATE
        },
        updatedAt: {
            allowNull: false,
            type: DataTypes.DATE
        }
    });

    User.associate = (models) => {
        User.hasMany(models.Recipe, {
            foreignKey: 'user_id',
            onDelete: 'CASCADE',
        });

        User.hasMany(models.Status, {
            foreignKey: 'user_id',
            onDelete: 'CASCADE',
        });

        User.hasMany(models.MealPlan, {
            foreignKey: 'user_id',
            onDelete: 'CASCADE',
        });

        User.hasMany(models.GroceryList, {
            foreignKey: 'user_id',
            onDelete: 'CASCADE',
        });
    };

    User.generatePasswordHash = (password) => {
        return bcrypt.hashSync(password, bcrypt.genSaltSync(10), null);
    };

    User.prototype.validPassword = function(password) {
        return bcrypt.compareSync(password, this.password);
    };

    return User;
};

