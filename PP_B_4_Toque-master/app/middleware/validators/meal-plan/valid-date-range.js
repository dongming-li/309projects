const { celebrate, Joi} = require('celebrate');

const startDate = new Date((new Date()).getFullYear(), (new Date()).getMonth(), 1);//Get first Day of the month
startDate.setDate(startDate.getDate() - startDate.getDay());//Get last Sunday closest to beginning of month
const endDate = new Date((new Date()).getFullYear(), (new Date()).getMonth() + 1, 0);//Last Day of the Month

module.exports = celebrate({
    query: Joi.object().keys({
        startDate: Joi.date().default(new Date('11-1-17')).max(Joi.ref('endDate')),
        endDate: Joi.date().default(new Date('12-31-2100'))
    })
});