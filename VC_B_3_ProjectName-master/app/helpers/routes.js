/**
 * @module routes
 */

// const rootUrl = 'http://ec2-18-216-1-189.us-east-2.compute.amazonaws.com';
/**
 * Root URL of the Walk Me Home REST API
 * @constant {string}
 */
const rootUrl = 'http://10.31.30.34:8090';

/**
 * Creates a route string
 * @function
 * @param {string} route Endpoint on the Walk Me Home REST API
 */
const createRoute = route => {
    return `${rootUrl}/${route}`;
};

/**
 * Different route strings representing endpoints on the Walk Me Home REST API
 * @readonly
 * @enum {string}
 */
export default {
    /**Test route */
    rootUrl,
    /**Login route */
    login: createRoute('login'),
    /**Get friends route */
    getFriends: createRoute('getFriends'),
    /**Get detailed friend info route */
    getFriendInfo: createRoute('getFriendInfo'),
    /**Register user route */
    register: createRoute('register'),
    /**Save user location route */
    userLocation: createRoute('userLocation'),
    /**Send message route */
    sendMessage: createRoute('sendMessage'),
    /**Retrieve messages route */
    retrieveMessages: createRoute('retrieveMessages'),
    /**Delete message route */
    deleteMessage: createRoute('deleteMessage'),
    /**Add friend route */
    addFriend: createRoute('addFriend'),
    /**Update user status route */
    updateUserStatus: createRoute('updateUserStatus')
};
