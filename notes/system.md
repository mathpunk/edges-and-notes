Okay, let's think this through one more (jk) time:

# Front End (View, Experience)
## Interaction

The rest interface for neo4j means, I can get new nodes and edges when I want them using
http, or rather, neocons/GET & args. 

To avoid blocking the UI, these should be asynchronous. 
Since these occur exactly when the user clicks something, this is definitely a front-end concern. 

Since that sounds really hard, and I don't have anything working yet, let's back off from
that, and think about how the initial load of a graph visualization ought to work. 

## Inititalization

Using routes and handlers -- I don't know the difference, mind -- we create a map from the
url a client requests into:
  data
  a view showing that data
  
So that comes in two parts.

Consider the "get-data" first. What are the routes even going to be like? 
Well. Think of the route you want the user to take: they're coming from a brief outline
link. When they follow the link, I want them to see:

  a big node representing the chapter
  smaller nodes representing the key words for the chapter radiating off of it
    note: those keywords are handcrafted by me and Kellyn, and placed... somewhere. 
  nodes coming off of THOSE that represent notes that can be explored. 

Let's pause to learn about what client side routing is, ok?

...yeah, I have no idea. 

But, you *do* know now that the goal is:
  take a vector of terms
  find those terms,
    and their relationships, 
    and the nodes they aim at
  Another way, 
    if a section is a node,
    then you are doing the distance-2 walk from that node to get node and edge data,
    and returning those 
    as something suitable for rendering.



## Alchemy 

I found this little snippet example of how alchemy reads in its conf information.

var conf = {
  "dataSource": "sample_data/contrib.json",
  "nodeTypes": {"role": ["Maintainer",
                "Contributor",
                "project"]},
  "edgeTypes": "caption"
};
alchemy.begin(conf)

I have no idea how to adjust the dataSource. Data change: is it a GET or is it an OM? HOW
DO I ASYNC

# Back End (Data Models)

## Migration
None of this does a damn lick of good unless I have data in my data base. Before you call
on the REST interface for data, have some data!

for each note in morgue, morgue archive
  n/create-unique {:caption title :cluster upward-closure-results, :type "note"}
  for each term in :tags note
    n/create-unique term
    r/create {:source note, :target term}
    
later:
  for each term and note, find related terms and notes. 
    

for each chapter,
  ....

