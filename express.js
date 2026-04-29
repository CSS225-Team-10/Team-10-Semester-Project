"use strict";
 
const express = require('express');
const app = express();
 
// Parse JSON request bodies (needed for POST)
app.use(express.json());

let savedNotes = "";

// endpoints go below this line
// General plan is for when they launch the notes frame, that
// will be a GET request
app.get('/savedNotes', (req, res) => {
  res.json({ notes: savedNotes });
});


// and when they click the save button,
// that will be a POST request
app.post('/savedNotes', (req, res) => {
  const { notes } = req.body;

  if (!notes) {
    return res.status(400).json({ error: "Notes are required" });
  }

  savedNotes = notes;
  res.json({ success: true });
})

app.listen(3000, () => {
  console.log("Server running on http://localhost:3000");
});



