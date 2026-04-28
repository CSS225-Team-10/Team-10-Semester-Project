"use strict";
 
const express = require('express');
const app = express();
 

// Serve static files from the 'public' folder
app.use(express.static('public'));
 
// Parse JSON request bodies (needed for POST)
app.use(express.json());

// endpoints go below this line
// General plan is for when they launch the notes frame, that
// will be a GET request
app.get('/savedNotes', (req, res)=>{
  res.type('json').send({
    messages
  });
});


// and when they click the save button,
// that will be a POST request
app.post('/savedNotes', (req, res) => {
  if(!text || !author){
    res.status(400).json({error: "IDK SOME ERROR MESSAGE"});
  }else{
    messages.push(
        //idrk
      {notes: savedNotes}
    )
  }
});





