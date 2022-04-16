const { celebrate, Joi} = require('celebrate');

module.exports = celebrate({
    params: Joi.object().keys({
        id: Joi.number().required(),
    })
});
