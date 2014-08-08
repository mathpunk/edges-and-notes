;; Nodes have a relationships-uri.
;;
;; onClick: 
;;  transact/go/async/whatever -- we don't want this to block the rest of the ui if the
;;  user runs around clicking twenty things
;;    GET relationships-uri of the node
;;    GET those nodes that were related 
;;    put it in the app-atom
;;    put them on the channel for rendering
;;
;;  render
;;    grab new nodes and relationships off the channel
;;    alchemy them
;;
;;  Now, I am nowhere near understanding async + react rendering. But this is the
;;  approximation of the experience that should occur. 
