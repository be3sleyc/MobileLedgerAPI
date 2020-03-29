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

validation.ids = (data) => {
    const id_schema = Joi.object({
        id: Joi.number().positive().max(4294967295).required()
    });

    return id_schema.validate(data);
}

validation.newaccount = (data) => {
    const account_schema = Joi.object({
        name: Joi.string().max(30).required(),
        type: Joi.string().max(20).allow(""),
        balance: Joi.string().pattern(new RegExp('-?[0-9]*(\.[0-9]{2})?')).allow(""),
        notes: Joi.string().max(512).allow("")
    });

    return account_schema.validate(data);
}

validation.editaccount = (data) => {
    const accedit_schema = Joi.object({
        id: Joi.number().positive().max(4294967295).required(),
        name: Joi.string().max(30),
        type: Joi.string().max(20).allow(""),
        notes: Joi.string().max(512).allow("")
    });

    return accedit_schema.validate(data);
}

validation.dates = (data) => {
    const range_schema = Joi.object({
        start: Joi.date().required(),
        stop: Joi.date().required().min(Joi.ref('start')),
        category: Joi.string().max(255),
        payee: Joi.string().max(100),
        account: Joi.string().pattern(new RegExp('[0-9]{1,10}'))
    });

    return range_schema.validate(data);
}

validation.category = (data) => {
    const category_schema = Joi.object({
        category: Joi.string().max(255).required(),
        start: Joi.date(),
        stop: Joi.date()
    });

    return category_schema.validate(data);
}

validation.account = (data) => {
    const account_schema = Joi.object({
        account: Joi.string().pattern(new RegExp('[0-9]{1,10}')).required(),
        start: Joi.date(),
        stop: Joi.date()
    });

    return account_schema.validate(data);
}

validation.payee = (data) => {
    const payee_schema = Joi.object({
        payee: Joi.string().max(100).required(),
        start: Joi.date(),
        stop: Joi.date()
    });

    return payee_schema.validate(data);
}

validation.transactionedit = (data) => {
    const edit_schema = Joi.object({
        accountid: Joi.number().positive().max(4294967295),
        amount: Joi.string().pattern(new RegExp('-?[0-9]*(\.[0-9]{2})?')).allow(""),
        paiddate: Joi.date(),
        payee: Joi.string().max(100),
        category: Joi.string().max(255)
    });

    return edit_schema.validate(data);
}

validation.newtransaction = (data) => {
    const new_schema = Joi.object({
        accountid: Joi.number().positive().max(4294967295).required(),
        amount: Joi.string().pattern(new RegExp('-?[0-9]*(\.[0-9]{2})?')).required(),
        paiddate: Joi.date().required(),
        payee: Joi.string().max(100).required(),
        category: Joi.string().max(255).allow("")
    })

    return new_schema.validate(data);
}

module.exports = validation;