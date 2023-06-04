public class Main {

    public static void main(String[] args) {

        //-----------------------------------------------> test1 :
//        Graph initialGraph= new Graph(5);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Red));
//        initialGraph.addNode(new Node(2, Color.Red));
//        initialGraph.addNode(new Node(3, Color.Green));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(4));
//
//
//
//
//        Graph initialGraphG= new Graph(5);
//        initialGraphG.addNode(new Node(0, Color.Green));
//        initialGraphG.addNode(new Node(1, Color.Green));
//        initialGraphG.addNode(new Node(2, Color.Green));
//        initialGraphG.addNode(new Node(3, Color.Green));
//        initialGraphG.addNode(new Node(4, Color.Green));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(0), initialGraphG.getNode(1));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(0), initialGraphG.getNode(2));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(0), initialGraphG.getNode(3));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(1), initialGraphG.getNode(2));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(3), initialGraphG.getNode(4));

        //-----------------------------------------------> test2 :
        Graph initialGraph= new Graph(7);
        initialGraph.addNode(new Node(0, Color.Red));
        initialGraph.addNode(new Node(1, Color.Black));
        initialGraph.addNode(new Node(2, Color.Green));
        initialGraph.addNode(new Node(3, Color.Red));
        initialGraph.addNode(new Node(4, Color.Red));
        initialGraph.addNode(new Node(5, Color.Green));
        initialGraph.addNode(new Node(6, Color.Red));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(4));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(4));
        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(6));

        //-----------------------------------------------> test3 :
//        Graph initialGraph= new Graph(15);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Black));
//        initialGraph.addNode(new Node(2, Color.Black));
//        initialGraph.addNode(new Node(3, Color.Black));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addNode(new Node(5, Color.Green));
//        initialGraph.addNode(new Node(6, Color.Green));
//        initialGraph.addNode(new Node(7, Color.Red));
//        initialGraph.addNode(new Node(8, Color.Red));
//        initialGraph.addNode(new Node(9, Color.Green));
//        initialGraph.addNode(new Node(10, Color.Red));
//        initialGraph.addNode(new Node(11, Color.Red));
//        initialGraph.addNode(new Node(12, Color.Red));
//        initialGraph.addNode(new Node(13, Color.Green));
//        initialGraph.addNode(new Node(14, Color.Red));
//
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(14));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(5));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(6));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(7));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(13));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(14));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(7));
//        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(6));
//        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(11));
//        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(10));
//        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(12));
//        initialGraph.addLinkBetween(initialGraph.getNode(6), initialGraph.getNode(11));
//        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(8));
//        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(9));
//        initialGraph.addLinkBetween(initialGraph.getNode(8), initialGraph.getNode(14));
//




//
//        Graph initialGraphG= new Graph(15);
//        initialGraphG.addNode(new Node(0, Color.Green));
//        initialGraphG.addNode(new Node(1, Color.Green));
//        initialGraphG.addNode(new Node(2, Color.Green));
//        initialGraphG.addNode(new Node(3, Color.Green));
//        initialGraphG.addNode(new Node(4, Color.Green));
//        initialGraphG.addNode(new Node(5, Color.Green));
//        initialGraphG.addNode(new Node(6, Color.Green));
//        initialGraphG.addNode(new Node(7, Color.Green));
//        initialGraphG.addNode(new Node(8, Color.Green));
//        initialGraphG.addNode(new Node(9, Color.Green));
//        initialGraphG.addNode(new Node(10, Color.Green));
//        initialGraphG.addNode(new Node(11, Color.Green));
//        initialGraphG.addNode(new Node(12, Color.Green));
//        initialGraphG.addNode(new Node(13, Color.Green));
//        initialGraphG.addNode(new Node(14, Color.Green));
//
//        initialGraphG.addLinkBetween(initialGraphG.getNode(0), initialGraphG.getNode(1));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(0), initialGraphG.getNode(2));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(1), initialGraphG.getNode(14));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(1), initialGraphG.getNode(2));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(1), initialGraphG.getNode(3));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(2), initialGraphG.getNode(5));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(2), initialGraphG.getNode(6));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(2), initialGraphG.getNode(7));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(3), initialGraphG.getNode(13));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(3), initialGraphG.getNode(14));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(3), initialGraphG.getNode(7));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(4), initialGraphG.getNode(6));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(4), initialGraphG.getNode(11));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(5), initialGraphG.getNode(10));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(5), initialGraphG.getNode(12));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(6), initialGraphG.getNode(11));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(7), initialGraphG.getNode(8));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(7), initialGraphG.getNode(9));
//        initialGraphG.addLinkBetween(initialGraphG.getNode(8), initialGraphG.getNode(14));

//
//
//
//
//


        State initialState= new State(initialGraph, -1, null);
     //   State initialState1= new State(initialGraphG, -1, null);
    // DFS.Search(initialState);
   //  BFS.search(initialState);
        ids.IDS(initialState);
        //  ids.IDS(initialState);
       // IDAStar.search(initialState);
     SRBFS g = new SRBFS() ;
        //GBFS.Search(initialState);
        //g.search(initialState ); ;
    //    BidirectionalSearcjh.BidirectioanlSearch(initialState ,initialState1 );
//AStar.Search(initialState);
//UCS.Search(initialState);
    }
}
