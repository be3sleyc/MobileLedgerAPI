const Joi = require('@hapi/joi');

let validation = {};

validation.register = (data) => {
    const register_schema = Joi.object({
        firstname: Joi.string().min(1).max(30).required(),
        lastname: Joi.string().min(1).max(30).required(),
        email: Joi.string().min(6).max(80).required().email(),
        password: Joi.string().min(6).max(80).required()
    });

    return register_schema.validate(data);
}

validation.login = (data) => {
    const login_schema = Joi.object({
        email: Joi.string().min(6).max(80).required().email(),
        password: Joi.string().min(6).max(80).required()
    });

    return login_schema.validate(data);
}

validation.oneaccount = (data) => {
    const id_schema = Joi.object({
        id: Joi.number().positive().max(4294967295).required()
    });

    return id_schema.validate(data);
}

validation.newaccount = (data) => {
    const account_schema = Joi.object({
        name: Joi.string().max(30).required(),
        balance: Joi.string().pattern(new RegExp('-?[0-9]*(\.[0-9]{2})?')).allow(""),
        notes: Joi.string().max(512).allow("")
    });

    return account_schema.validate(data);
}

validation.editaccount = (data) => {
    const accedit_schema = Joi.object({
        id: Joi.number().positive().max(4294967295).required(),
        name: Joi.string().max(30),
        notes: Joi.string().max(512).allow("")
    });

    return accedit_schema.validate(data);
}

module.exports = validation;