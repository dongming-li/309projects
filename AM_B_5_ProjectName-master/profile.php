<?php
   class Profile {
      /* Member variables */
	  var $id;
      var $firstName;
      var $lastName;
	  var $user;
	  var $pswd;
	  var $phone;
	  var $email;
	  
      /* Member functions */
	  // set
	  function setId($par){
		  $this->id = $par;
	  }
      function setFirstName($par){
         $this->firstName = $par;
      }
	  function setLastName($par){
         $this->lastName = $par;
      }
	  function setUser($par){
		  $this->user = $par;
	  }
	  function setPswd($par){
		  $this->pswd = $par;
	  }
	  function setPhone($par){
		  $this->phone = $par;
	  }
	  function setEmail($par){
		  $this->email = $par;
	  }
	  // get
	  function getId(){
		  return $this->id;
	  }
      function getFirstName(){
          return $this->firstName;
      }
	  function getLastName(){
          return $this->lastName;
      }
	  function getUser(){
		  return $this->user;
	  }
	  function getPswd(){
		  return $this->pswd;
	  }
	  function getPhone(){
		  return $this->phone;
	  }
	  function getEmail(){
		  return $this->email;
	  }
   }
?>