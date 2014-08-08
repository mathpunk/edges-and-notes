# For reference to a previous implementation of a notes-server

from flask import Flask, render_template
from information import *
import edn_format


app = Flask(__name__)
app.debug = True


@app.route('/edges/<keyword>')
def edge_data(keyword):
  return edn_format.dumps(list(edges(keyword)))

@app.route('/neighbors/<keyword>')
def neighbor_data(keyword):
  return edn_format.dumps(neighbors(keyword))

@app.route('/keywords')
def keyword_data():
  # What I would very much like to do is create a keyword*edge matrix, and perhaps a
  # keyword*keyword matrix, format in edn, and query that for display and ui.
  return edn_format.dumps("[[nil 100 200 300] ['math' 1 0 1] ['punk' 0 1 1]]")

# ...but we're not so l33t yet. So here's a simple list of keywords rendered in edn.
@app.route('/')
def keyword_list():
    return render_template("template.html", data=edn_format.dumps(list(keywords['word'])))

# When we finally get around to actually reading a note, it should be grabbed
# out of a database somewhere and run through a template.
@app.route('/notes/<identifier>')
def note(identifier):
  pass

if __name__ == "__main__":
  app.run()
