import java.util.ArrayList;
import java.util.LinkedList;

public class State {
    public int depthOfState ;
    private Graph graph;
    private int selectedNodeId;
    private State parentState;
    public int CostOFThisState ;
    public int CostOFThisStateForAStar ;
    public int GBFS ;
    public int AStar ;
    public int F ;
    public State(Graph graph, int selectedNodeId, State parentState){
        this.graph= graph.copy();
        this.selectedNodeId= selectedNodeId;
        if(parentState != null){
            this.parentState= parentState;
        }else{
            this.parentState = null;
        }
    }
    public int getCostOFThisState(Graph newGraph , Node newNode){
        int counter = 0 ;
        if(this.graph.getNode(newNode.getId()).getColor() !=
        newGraph.getNode(newNode.getId()).getColor()){
            counter += 1 ;
        }
        LinkedList<Integer> nodeNeighbors = newNode.getNeighborsIds();
        for( int y= 0 ; y < nodeNeighbors.size();y++ ){
            if(this.graph.getNode(nodeNeighbors.get(y)).getColor() !=
                    newGraph.getNode(nodeNeighbors.get(y)).getColor()){
                counter += 1 ;
            }
        }
        int ii = CostOFThisStateForAStar + counter ;
        return ii ;
    }
    public int getHuristic(){
        int counter =0 ;
        Graph graph = this.graph ;
        for(int i = 0  ; i < graph.size()  ; i ++ ){
            if(graph.getNode(i).getColor() != Color.Green  ){
                counter +=1 ;
            }
        }
        return counter ;

    }
//    public int getCostOFThisStateForAStar(){
//
//    }

    public ArrayList<State> successor(){
        ArrayList<State> children= new ArrayList<State>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                State newState = new State(this.graph.copy(), nodeId, this);
                LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
                for (int j = 0; j < nodeNeighbors.size(); j++) {
                    int neighborId=nodeNeighbors.get(j);
                    newState.getGraph().getNode(neighborId).reverseNodeColor();
                }
                if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
                    int greenNeighborsCount=0;
                    int redNeighborsCount=0;
                    int blackNeighborcount=0;
                    for (int j = 0; j < nodeNeighbors.size(); j++) {
                        int neighborId=nodeNeighbors.get(j);
                        switch (newState.getGraph().getNode(neighborId).getColor()) {
                            case Green -> greenNeighborsCount++;
                            case Red -> redNeighborsCount++;
                            case Black -> blackNeighborcount++;
                        }
                    }
                    if(greenNeighborsCount > redNeighborsCount && greenNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Green);
                    }else if(redNeighborsCount > greenNeighborsCount && redNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Red);
                    }
                }else {
                    newState.getGraph().getNode(nodeId).reverseNodeColor();
                }
                children.add(newState);
            }
        }
        return children;
    }

    //successor for GBFS Algorithm ......................

    public ArrayList<State> successorGBFS(){
        ArrayList<State> children= new ArrayList<State>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                State newState = new State(this.graph.copy(), nodeId, this);
                LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
                for (int j = 0; j < nodeNeighbors.size(); j++) {
                    int neighborId=nodeNeighbors.get(j);
                    newState.getGraph().getNode(neighborId).reverseNodeColor();
                }
                if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
                    int greenNeighborsCount=0;
                    int redNeighborsCount=0;
                    int blackNeighborcount=0;
                    for (int j = 0; j < nodeNeighbors.size(); j++) {
                        int neighborId=nodeNeighbors.get(j);
                        switch (newState.getGraph().getNode(neighborId).getColor()) {
                            case Green -> greenNeighborsCount++;
                            case Red -> redNeighborsCount++;
                            case Black -> blackNeighborcount++;
                        }
                    }
                    if(greenNeighborsCount > redNeighborsCount && greenNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Green);
                    }else if(redNeighborsCount > greenNeighborsCount && redNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Red);
                    }
                }else {
                    newState.getGraph().getNode(nodeId).reverseNodeColor();
                }
                int counter = newState.getHuristic() ;
                newState.GBFS = counter;
                children.add(newState);
            }
        }
        return children;
    }



    // .................................................................................
   //successor function for A* ...............
    public ArrayList<State> successorAstar(){
        ArrayList<State> children= new ArrayList<State>();
        for (int i = 0; i < this.graph.size(); i++) {
            int nodeId= this.graph.getNode(i).getId();
            if(nodeId != selectedNodeId){
                State newState = new State(this.graph.copy(), nodeId, this);
                LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
                for (int j = 0; j < nodeNeighbors.size(); j++) {
                    int neighborId=nodeNeighbors.get(j);
                    newState.getGraph().getNode(neighborId).reverseNodeColor();
                }
                if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
                    int greenNeighborsCount=0;
                    int redNeighborsCount=0;
                    int blackNeighborcount=0;
                    for (int j = 0; j < nodeNeighbors.size(); j++) {
                        int neighborId=nodeNeighbors.get(j);
                        switch (newState.getGraph().getNode(neighborId).getColor()) {
                            case Green -> greenNeighborsCount++;
                            case Red -> redNeighborsCount++;
                            case Black -> blackNeighborcount++;
                        }
                    }
                    if(greenNeighborsCount > redNeighborsCount && greenNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Green);
                    }else if(redNeighborsCount > greenNeighborsCount && redNeighborsCount > blackNeighborcount){
                        newState.getGraph().getNode(nodeId).changeColorTo(Color.Red);
                    }
                }else {
                    newState.getGraph().getNode(nodeId).reverseNodeColor();
                }
                int Huristic = newState.getHuristic() ;
                int cost = getCostOFThisState(newState.graph, this.graph.getNode(i)) ;

                newState.CostOFThisStateForAStar = cost + this.CostOFThisStateForAStar ;
                int AStar1 =newState.getHuristic() + newState.CostOFThisStateForAStar ;
                newState.AStar = AStar1 ;
                children.add(newState);
            }
        }
        return children;
    }
//.................sucessor of UCS
public ArrayList<State> successorForUCS(){
    ArrayList<State> children= new ArrayList<State>();
    for (int i = 0; i < this.graph.size(); i++) {
        int nodeId= this.graph.getNode(i).getId();

        if(nodeId != selectedNodeId){
            State newState = new State(this.graph.copy(), nodeId, this);
            //..................... change .....................
            if( this.graph.getNode(i).getColor() == Color.Black){
                newState.CostOFThisState = this.CostOFThisState + 2 ;
            }else{
                if(this.graph.getNode(i).getColor() == Color.Red){
                    newState.CostOFThisState = this.CostOFThisState + 1 ;
                }else{
                    if(this.graph.getNode(i).getColor() == Color.Green){
                        newState.CostOFThisState = this.CostOFThisState + 3 ;
                    }
                }
            }
            //........................................................
            LinkedList<Integer> nodeNeighbors = newState.getGraph().getNode(nodeId).getNeighborsIds();
            for (int j = 0; j < nodeNeighbors.size(); j++) {
                int neighborId=nodeNeighbors.get(j);
                newState.getGraph().getNode(neighborId).reverseNodeColor();
            }
            if(newState.getGraph().getNode(nodeId).getColor() == Color.Black){
                int greenNeighborsCount=0;
                int redNeighborsCount=0;
                int blackNeighborcount=0;
                for (int j = 0; j < nodeNeighbors.size(); j++) {
                    int neighborId=nodeNeighbors.get(j);
                    switch (newState.getGraph().getNode(neighborId).getColor()) {
                        case Green :greenNeighborsCount++;
                        case Red :redNeighborsCount++;
                        case Black : blackNeighborcount++;
                    }
                }
                if(greenNeighborsCount > redNeighborsCount && greenNeighborsCount > blackNeighborcount){
                    newState.getGraph().getNode(nodeId).changeColorTo(Color.Green);
                }else if(redNeighborsCount > greenNeighborsCount && redNeighborsCount > blackNeighborcount){
                    newState.getGraph().getNode(nodeId).changeColorTo(Color.Red);
                }
            }else {
                newState.getGraph().getNode(nodeId).reverseNodeColor();
            }
            children.add(newState);
        }
    }
    return children;
}



    //.................


    public String hash(){
        String result= "";
        for (int i = 0; i < graph.size(); i++) {
            switch (graph.getNode(i).getColor()) {
                case Green -> result += "g";
                case Red -> result += "r";
                case Black -> result += "b";
            }
        }
        return result;
    }

    public String outputGenerator(){
        String result= "";
        for (int i = 0; i < graph.size(); i++) {
            String color = switch (graph.getNode(i).getColor()) {
                case Red -> "R";
                case Green -> "G";
                case Black -> "B";
            };
            result += graph.getNode(i).getId()+color+" ";
            for (int j = 0; j < graph.getNode(i).getNeighborsIds().size(); j++) {
                int neighborId=graph.getNode(i).getNeighborsId(j);
                String neighborColor = switch (graph.getNode(neighborId).getColor()) {
                    case Red -> "R";
                    case Green -> "G";
                    case Black -> "B";
                };
                result += neighborId+neighborColor+" ";
            }
            result += ",";
        }
        return result;
    }

    public Graph getGraph(){
        return graph;
    }

    public State getParentState(){
        return parentState;
    }

    public  int getSelectedNodeId(){
        return selectedNodeId;
    }

}
