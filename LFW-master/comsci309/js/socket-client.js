
/**
 * @file socket-client.JS 
 * @fileOverview Creates the business logic for the chat feature to function
 * @author Ryan Evans <rievans@iastate.edu>
 * @version 1.1
 */
var socket;

/**
 * @module Chatfunctionality
 */

/**
* @function storeUsername
* @param {String} username  Stores username on login for id in chat
* @description Stores username on login
*/
function storeUsername(username) {
  console.log('sending user login ' + username)
  socket.emit('user-login', username);
}
/**
* @function cleanChatIfTooLong
* @description Clears chat based on number of appended divs (messages) 
*/
function cleanChatIfTooLong() {
  if ($('.chatbox').children().length >= 22) {
    $('.chatbox').find('div').first().remove();
  }
}
/**
* @function startSocketConnection
* @description Creates the socket.io server for the chat to run off of (local or isu host)
*/
function startSocketConnection() {
  if (window.location.hostname === 'localhost') {
    socket = io.connect();
  } else {
    socket = io.connect("http://proj-309-vc-b-2.cs.iastate.edu:81");
  }
  /**
  * 
  * @function 'socket.on'
  * @param {String} newmsg user submitted message
  * @description Recieves msg from user and adds to chatbox
  */
  socket.on('newmsg', function (msg) {
    cleanChatIfTooLong();
    console.log('received message:', msg)
    $('.chatbox').append($('<div id="msg' + msg.id + '">').text('[' + msg.time + '] ' + msg.user + ': ' + msg.message));
  });

  /**
  * @function 'socket.on'
  * @param {String} servermsg server submitted message
  * @description Adds a servermsg, whether thats a join or leave notification
  */
  socket.on('server-msg', function (msg) {
    cleanChatIfTooLong();
    console.log('received server message:', msg)
    $('.chatbox').append($('<div class="server-msg" id="server-msg' + msg.id + '">').text(msg.message));
  });

  /**
  * @function 'socket.on'
  * @param {String} userlist list of current users
  * @param {String} data sent list to users
  * @description Displays all current users in a list (userlist) and sends to all users (data)
  */
  socket.on('user-list', function (data) {
    console.log('received user list', data);
    $('.chatbox-users').empty();
    for (var i = 0; i < data.length; i++) {
      $('.chatbox-users').append('<div>' + data[i] + '</div>');
    }
  });
  /**
  * @function 'socket.on'
  * @param {String} userlistadd add users to list function
  * @param {String} data sent user add to users
  * @description adds a user to the userlist object, and displays it (data)
  */
  socket.on('user-list-add', function (data) {
    $('.chatbox-users').append('<div>' + data + '</div>');
  })
}
