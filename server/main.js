var bodyParser = require('body-parser');
var express = require('express');
var log = require('./log.js');


log.show('Starting vfdReminder server...');


// create a new server instance
var server = express();

// let's parse json and xxx-form-urlencoded
server.use(bodyParser.json());
server.use(bodyParser.urlencoded({
	extended: true
}));


// If anyone just stumbles upon us directly, we tell them who we are.
server.get('/', function(req, res){
  res.send('I am the a softer space vfd reminder server - do you want to be my friend?');
});


// We are waiting for the vfdReminder to call us with a POST.
server.post('/getUpcomingEvents', function(req, res) {

	if (req && req.body) {

		log.write("Some app requested the list of upcoming events.");
	}

	// Just return nothing - our app does not care.
	return res.end();
});


// Everything is now set up - let's start the server!
server.listen(3001, function() {
	log.show('vfdReminder server successfully started!');
});

