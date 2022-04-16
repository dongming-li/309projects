/**
 * @module message-types
 */

 /**
  * Different types of messages that can be sent between users
  * @readonly
  * @enum {string}
  */
export default {
    /**Friend request */
    friendRequest: 'friend-request',
    /**Friend request was accepted */
    friendRequestAccepted: 'friend-request-accepted',
    /**Friend request was rejected */
    friendRequestRejected: 'friend-request-rejected',
    /**General message */
    general: 'general'
}
