var fs = require('fs');

var LOG_FILE_NAME = "main.log";

log = {
	
	getTimestamp: function() {
        
		var now = new Date();
		
        var yyyy = now.getFullYear();
        var mm = ('0' + (now.getMonth()+1)).slice(-2);
        var dd = ('0' + now.getDate()).slice(-2);
        var dateStr = yyyy+"-"+mm+"-"+dd;
		
        var hrs = ('0' + now.getHours()).slice(-2);
        var min = ('0' + now.getMinutes()).slice(-2);
        var sec = ('0' + now.getSeconds()).slice(-2);
		var timeStr = hrs+":"+min+":"+sec;
		
		return dateStr + " " + timeStr;
	},

	// write the msg to the log file
	write: function(msg) {

		// We are using a flag for appending, not overwriting.
		var logStream = fs.createWriteStream(LOG_FILE_NAME, {'flags': 'a'});
		
		logStream.write(this.getTimestamp() + ":\n");
		logStream.end(msg + "\n\n");
	},

	// show the msg on standard out and write it to the log file
	show: function (msg) {

		console.log(msg);
		
		this.write(msg);
	},
	
}