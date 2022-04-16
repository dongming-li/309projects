
/**
 * @file app.config.JS 
 * @fileOverview navigation logic for web app
 * @author Ryan Evans <rievans@iastate.edu>
 * @version 1.1
 */
/**  
* App.Config is the javascript file for creating and maintaining navigation in our
* web application. This file is the logic that takes users to each page.
*/
angular.module('navigation', [
    'ngRoute'
]).config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('');
        /** 
        * @file App.Config.JS 
        * @fileOverview Creates the navigation for the web app
        * @author Ryan Evans <rievans@iastate.edu>
        * @version 1.1
        *  Router module takes the part of the URL after #/ and routes
        *  the html pages based on which page the user wants to access
        * 
        * @module NavigationRouter
        */
        $routeProvider
            /**
             * @function '$routeProvider.when'
             * @param {String} /  Displays pages/login.html for user
             * @description Takes the user to the login page initially
             * @return {String} templateUrl:pages/login.html  
             */
            .when('/', {
                templateUrl: 'pages/login.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /landing  Displays pages/landing.html for user
             * @description Takes the user to the landing page after successful login
             * @return {String} templateUrl:pages/landing.html  
             */
            .when('/landing', {
                templateUrl: 'pages/landing.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /profile  Displays pages/landing.html for user
             * @description Takes the user to the profile page on clicking tab
             * @return {String} templateUrl:pages/profile.html  
             */
            .when('/profile', {
                templateUrl: 'pages/profile.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /ach Displays pages/ach.html for user
             * @description Takes the user to the achievements page on clicking tab
             * @return {String} templateUrl:pages/ach.html  
             */
            .when('/ach', {
                templateUrl: 'pages/ach.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /gameboard Displays pages/gameboard.html for user
             * @description Takes the user to the gameboard page on clicking tab
             * @return {String} templateUrl:pages/gameboard.html  
             */
            .when('/game', {
                templateUrl: 'pages/gameboard.html'
            })
            /**
            * @function '$routeProvider.when'
            * @param {String} /joinlobby Displays pages/joinlobby.html for user
            * @description Takes the user to the joinlobby page on clicking tab
            * @return {String} templateUrl:pages/joinlobby.html  
            */
            .when('/joinlobby', {
                templateUrl: 'pages/joinlobby.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /createlobby Displays pages/createlobby.html for user
             * @description Takes the user to the createlobby page on clicking tab
             * @return {String} templateUrl:pages/createlobby.html  
             */
            .when('/createlobby', {
                templateUrl: 'pages/createlobby.html'
            })
            /**
            * @function '$routeProvider.when'
            * @param {String} /docs Displays pages/docs.html for user
            * @description Takes the user to the docs page on clicking tab
            * @return {String} templateUrl:pages/docs.html  
            */
            .when('/docs', {
                templateUrl: 'pages/docs.html'
            })
            /**
             * @function '$routeProvider.when'
             * @param {String} /404 Displays pages/404.html for user
             * @description Takes the user to the 404 page when the url is not found
             * @return {String} templateUrl:pages/404.html  
             */
            .when('/404', {
                templateUrl: 'pages/404.html'
            })
    }
]);
