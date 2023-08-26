# Search project
The problem you are facing consists of an undirected graph.This graph has nodes that each node can be one of the three colors green, red or black at any moment. In the beginning, the graph has nodes with one of the above three colors. At each stage, you can choose one of the nodes. and the final goal of the problem is to cover all the nodes of the graph in green color.
## Red or Green node :
By selecting a green or red node, the selected node and each of its neighboring nodes:
<br>
  1.If it is green, it will change color to red. <br>
  2.If it is red, it will change color to green.<br>
  3.If any of the neighboring nodes of the selected node is black, that neighboring node remains unchanged.
  <br>
  ## Black node: 
  In order to achieve the goal of the problem, it is necessary that the black nodes somehow become one of the green or red colors, so that after that the ability to
  change the color is created in a normal way. <br>
  By choosing a black node, the following steps happen:<br>
    1.Each of the neighboring nodes of this node, if it is green, it will change to red, and if it is red, it will change 
    to green, and if it is black, it will remain unchanged.<br>
    2.After changing the color of the neighbors, according to the color of the majority of the neighbors
    , one of the following situations will happen to the selected black node:
      a.If the majority of the neighbors are red, the black node changes to the red rank.<br>
      b.Similarly, if the majority of 
      neighbors are green, the black node changes to green.<br>
      c.Otherwise, the selected black node remains unchanged and black.
      <br>
      ## First Phase
      In this phase of the project, you have to implement and test uninformed search algorithms. For this purpose, write algorithms of DFS , IDS , BDS , UCS to solve the given problem. Also, to implement the UCS algorithm, assume that choosing a red node has a cost equal to one. Choosing a black node has a cost equal to two, and choosing a green node has a cost equal to three
(for the implementation of conscious search algorithms, 
there is no need to consider this assumption).
<br>
## Second phase 
In this phase we have to design a consistent huristic and then implement the A* , GBFS , IDA* , RBFS .
It is also crucial to check the Admissibility and consistency of our heuristic.
      
      
