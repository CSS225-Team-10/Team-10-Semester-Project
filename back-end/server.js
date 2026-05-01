"use strict";

const express = require('express');
const app = express();

// Parse JSON request bodies (needed for POST)
app.use(express.json());

const Database = require('better-sqlite3');
const db = new Database('pawffice.db');

db.exec(`CREATE TABLE IF NOT EXISTS notes (                                                                                                                           
    id INTEGER PRIMARY KEY,                  
    content TEXT NOT NULL                                                                                                                                                        
  )`);

// endpoints go below this line
// General plan is for when they launch the notes frame, that
// will be a GET request
app.get('/savedNotes', (req, res) => {
  const row = db.prepare('SELECT content FROM notes WHERE id = 1').get();
  res.json({ notes: row ? row.content : "" });
});


// and when they click the save button,
// that will be a POST request
app.post('/savedNotes', (req, res) => {
  const { notes } = req.body;

  if (!notes) {
    return res.status(400).json({ error: "Notes are required" });
  }

  db.prepare(
    'INSERT INTO notes (id, content) VALUES (1, ?) ON CONFLICT(id) DO UPDATE SET content = ?'
  ).run(notes, notes);

  res.json({ success: true });
});

app.listen(8080, () => {
  console.log("Server running on http://localhost:8080");
});
