const { celebrate, Joi} = require('celebrate');

module.exports = celebrate({
    body: Joi.object().keys({
        recipe_id: Joi.number().required(),
        date: Joi.date().min('11-1-2017').required()
    })
});
