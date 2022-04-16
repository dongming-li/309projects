/**
 * @module error-messages
 */

/**
 * Various error messages for when there are register form errors
 * @readonly
 * @property {string} firstNameMissing - Message for when the first name field is empty
 * @property {string} lastNameMissing - Message for when the last name field is empty
 * @property {string} emailMissing - Message for when the email field is empty
 * @property {string} passwordMissing - Message for when the password field is empty
 * @property {string} passwordRepeatMissing - Message for when the password repeat field is empty
 * @property {string} emailNotValid - Message for when the email provided is not valid
 * @property {string} passwordsNotMatching - Message for when the passwords do not match
 * @property {string} userExists - Message for when the user tries to register an account with an email that has already been registered
 */
export const RegisterFormErrors = {
    firstNameMissing: 'First name field is missing',
    lastNameMissing: 'Last name field is missing',
    emailMissing: 'Email field is missing',
    passwordMissing: 'Password field is missing',
    passwordRepeatMissing: 'Please repeat your password',
    emailNotValid: 'Please provide a valid email address',
    passwordsNotMatching: 'Your passwords do not match',
    userExists: 'A user with this email already exists'
}

/**
 * Various error messages for when there are login form errors
 * @readonly
 * @property {string} emailEmpty - Message for when the email field is empty
 * @property {string} passwordEmpty - Message for when the password field is empty
 * @property {string} emailIncorrect - Message for when the email provided is incorrect
 * @property {string} passwordIncorrect - Message for when the password provided is incorrect
 */
export const LoginFormErrors = {
    emailEmpty: 'Please enter your email address',
    passwordEmpty: 'Please enter your password',
    emailIncorrect: 'Your email is incorrect. Please enter the correct email',
    passwordIncorrect: 'Your password is incorrect. Please enter the correct password'
}

/**
 * Varous error messaging logic for when there are errors trying to add a friend to a user
 * @readonly
 * @property {function} userDoesNotExist - Creates a message for when a user does not exist
 * @property {function} duplicateMessage - Creates a message for when a user already has a friend request
 * @property {string} userDoesNotExistCode - User does not exist flag
 * @property {string} duplicateMessageCode - Duplicate message flag
 */
export const AddFriendsErrors = {
    userDoesNotExist: user => {
        return `A user with an email of ${user} does not exist. Please try another email`;
    },
    duplicateMessage: user => {
        return `You have already sent ${user} a friend request`;
    },
    userDoesNotExistCode: 'user-does-not-exist',
    duplicateMessageCode: 'duplicate-message'
}
