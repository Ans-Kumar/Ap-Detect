// Get a reference to the database service
var database = firebase.database();
function writeUserData() {
  firebase.database().ref('users/19900').set({
    'age': 23,
	'name': 'Deep',
    'password': 'deep',
	'pollution': '3.3',
	'url': 'images/deep.jpg'
  }, function(err){
	  if (err){
		  console.log('oops');
		  console.log(err);
	  } else {
		  console.log('done'); 
	  }
  });
}

var data = database.ref('users').orderByChild('pollution');
data.on("value", function(snapshot) {
	var obj = snapshot.val();
	var us = document.getElementById('users');
	var par = document.createElement("p");
	par.setAttribute("class", "user");
	var par1 = document.createElement("p");
	par1.setAttribute("class", "user-age");
	par1.appendChild(document.createTextNode('Age'));
	var par2 = document.createElement("p");
	par2.setAttribute("class", "user-name");
	par2.appendChild(document.createTextNode('Name'));
	var par3 = document.createElement("p");
	par3.setAttribute("class", "user-poll");
	par3.appendChild(document.createTextNode('Pollution-Level'));
	var imgg = document.createElement("p");
	imgg.setAttribute("class", "user-poll");
	imgg.appendChild(document.createTextNode('Profile'));
	par.appendChild(imgg);
	par.appendChild(par1);
	par.appendChild(par2);
	par.appendChild(par3);
	us.appendChild(par);
	var arr = [];
	for(var key in obj){
		arr.push(obj[key]);
	}
	arr.sort(function(a, b){
		return a.pollution - b.pollution;
	});
  arr.forEach(function(item){
	var age = item['age'];
	var name = item['name'];
	var poll = item['pollution'];
	var url = item['url'];
	var par = document.createElement("p");
	par.setAttribute("class", "user");
	var par1 = document.createElement("p");
	par1.setAttribute("class", "user-age");
	par1.appendChild(document.createTextNode(age));
	var par2 = document.createElement("p");
	par2.setAttribute("class", "user-name");
	par2.appendChild(document.createTextNode(name));
	var par3 = document.createElement("p");
	par3.setAttribute("class", "user-poll");
	par3.appendChild(document.createTextNode(poll));
	var imgg = document.createElement("img");
	imgg.setAttribute("class", "user-img");
	imgg.setAttribute("src", url);
	par.appendChild(imgg);
	par.appendChild(par1);
	par.appendChild(par2);
	par.appendChild(par3);
	us.appendChild(par);
  })
}, function (errorObject) {
  console.log("The read failed: " + errorObject.code);
});
writeUserData();