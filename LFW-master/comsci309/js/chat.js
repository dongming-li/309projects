

/**
 * @file Chat.JS 
 * @fileOverview Opens chat, navbar, lets users send messages
 * @author Ryan Evans <rievans@iastate.edu>
 * @version 1.1
 */

/**  
* Chat.js is the javascript file that shows navbar, chatbox, and allows for users to submit messages
*/


/**
* @function start
* @description Initializes chatbox and navbar on page load
*/
$(document).ready(function start() {
    $("#chatcontainer").show();
    $(".nav1").show();
    $('.nav1').animate({ opacity: 1 }, 200);
    $('.nav1').animate({ height: 20 }, 600);
    $("#chatcontainer").animate({ right: "0px" }, 1000);

});

/**
        * @function sendMessage
        * @description Creates var to store message, then emits message to server
        * @return {String} message:msg
        */
function sendMessage() {
    var msg = $('#send-message-input').val();
    console.log('sending message', msg);
    if (msg) {
        socket.emit('msg', {
            message: msg
        });
        $('#send-message-input').val("");

        $('#send-message-input').focus();
    }
}