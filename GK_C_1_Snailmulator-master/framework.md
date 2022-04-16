# Using the MVC framework

## Adding a web page

- edit `config.json`

- it will look something like this.

```javascript
{
"routes": {
 "path": "",
 "controller": "Index",
 "action": "index",
 "children": [{
   "path": "test",
   "children": [{
     "path": "physics",
     "children": [{
       "path": "abstraction",
       "controller": "Physics",
       "action": "abstraction"
     }]
   }, {
     "path": "socket",
     "controller": "Socket",
     "action": "test"
   }, {
     "path": "theme",
     "controller": "Theme",
     "action": "test"
   }],
 }]
"managerPipeline": ["route", "socket", "controller", "request"],
"static": [
 "matterjs/dist",
 "jquery/dist",
 "bootstrap/dist/css",
 "bootstrap/dist/js"
],
"port": 3000
}
```

- Let's say we want to add a page at /my/first/page. Adding the route in this config file is the first step.

- Add the route and subroutes as a tree like so

```javascript
{
  "routes": {
    "path": "",
    "controller": "Index",
    "action": "index",
    "children": [{
      "path": "test",
      "children": [{
        "path": "physics",
        "children": [{
          "path": "abstraction",
          "controller": "Physics",
          "action": "abstraction"
        }]
      }, {
        "path": "socket",
        "controller": "Socket",
        "action": "test"
      }, {
        "path": "theme",
        "controller": "Theme",
        "action": "test"
      }]
    +}, {
    +  "path": "my",
    +  "children": [{
    +    "path": "first",
    +    "children": [{
    +      "path": "route",
    +      "controller": "MyFirstController",
    +      "action": "myFirstAction"
    +    }]
    +  }]
    +}]
  },
  "managerPipeline": ["route", "socket", "controller", "request"],
  "static": [
    "matterjs/dist",
    "jquery/dist",
    "bootstrap/dist/css",
    "bootstrap/dist/js"
  ],
  "port": 3000
}
```

- We added an object to the children array for the root route, which is the very top with a path "". All other routes are children of the root. Any other route can also have children itself in order to make paths. In this examples the paths are `/`, `/test/physics`, `/test/physics/abstraction`, `/test/socket`, `/test/theme`. While paths don't do anything themselves, if they have a controller and action key in them, then that tells the framework that the there should be a web page at that route. In this example, the web pages the framework creates are for `/`, `/test/physics/abstraction`, `/test/socket`, `/test/theme`, and `/my/first/route`. `/test/physics` doesn't have a web page assigned to it because it doesn't have a controller and action key in it's JSON object. We have told the framework that we will be making web pages for these routes. Which means that it will be looking for the controllers we specified and the actions we specified in them.

- Looking at the controller and views folder, what we should do now is to mimic the path structure with views and place every controller we specified in the controller folder.

```
server/
├── controller
│   ├── Index.js
│   ├── Physics.js
│   ├── Socket.js
│   └── Theme.js
├── manager
│   ├── controller.js
│   ├── request.js
│   ├── route.js
│   └── socket.js
└── views
    ├── index.pug
    ├── partial
    │   └── navbar.pug
    └── test
        ├── physics
        │   └── abstraction.pug
        ├── socket.pug
        └── theme.pug
```

- This means we need to add our view and controller still

```
server/
├── controller
│   ├── Index.js
+│   ├── MyFirstController.js
│   ├── Physics.js
│   ├── Socket.js
│   └── Theme.js
├── manager
│   ├── controller.js
│   ├── request.js
│   ├── route.js
│   └── socket.js
└── views
    ├── index.pug
+    ├── my
+    │   └── first
+    │       └── route.pug
    ├── partial
    │   └── navbar.pug
    └── test
        ├── physics
        │   └── abstraction.pug
        ├── socket.pug
        └── theme.pug
```

- A controller is the first piece of code run when our route is called. It's the primary layer between our view of the page and the backend of our website. Controller actions are the functions in a controller that correspond to a particular route. Since we specified that the controller is MyFirstController and the action is myFirstAction we will need to add an action `myFirstAction` in the js file. So inside of `MyFirstController.js`, put the code below. Every controller should export an object with every action as a key. We only export one key of myFirstAction. In every action we return an object that contains all of the variables we want to send to our view. In this example, the view will be given the variable `test_data`.

```
function myFirstAction() {
  return {
    test_data: "test"
  };
}

module.exports = {
  myFirstAction: myFirstAction
};
```

- Finally we will make our view. Open up `route.pug` in our views folder and put in this code below. You will have to read up on pug to understand the syntax, but it's nearly a 1 to 1 mapping to familiar html to make web pages. This just means we are rendering a simple h1 tag with the innerHTML of our `test_data` variable.

```
h1 #{test_data}
```
