var database;

function init(){
	console.log("Init called");
	var config = {
	    apiKey: "AIzaSyCaoYae_vKOE4pRU-8QRsvy72OWXYHgNJI",
	    authDomain: "aptrek-47b57.firebaseapp.com",
	    databaseURL: "https://aptrek-47b57.firebaseio.com",
	    projectId: "aptrek-47b57",
	    storageBucket: "aptrek-47b57.appspot.com",
	    messagingSenderId: "443127269033"
	  };
	firebase.initializeApp(config);
	database=firebase.database();
}

function writeUserData() {
	console.log("writeUserData called");
	  firebase.database().ref('users/'+document.myform.username.value).set({
	    'UserName':document.myform.usernamer.value,
		'City': document.myform.city.value,
	    'State': document.myform.state.value,
		'Email': document.myform.email.value,
		'Password': document.myform.password.value
	  }, function(err){
		  if (err){
			  console.log('oops');
			  console.log(err);
		  } else {
			  console.log('done'); 
		  }
	  });
	  console.log('successfully Submitted');
	}