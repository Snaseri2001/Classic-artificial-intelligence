import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AStar {


    public static void Search(State initialState){
        Comparator<State> StateComparator = new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                if(s1.AStar >= s2.AStar){
                    return 1 ;
                }else{
                    return -1 ;
                }}
        };
        PriorityQueue<State> Frontier = new PriorityQueue<State>(StateComparator) ;
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal(initialState)){
            result(initialState);
            return;
        }
        initialState.CostOFThisStateForAStar = 0 ;
        initialState.AStar = initialState.getHuristic() ;
        Frontier.add(initialState) ;
        inFrontier.put(initialState.hash(),true);
        while(!Frontier.isEmpty()){
            State tempState = Frontier.poll();
            if( !(explored.containsKey(tempState.hash()))) {


                explored.put(tempState.hash(), true);
                ArrayList<State> children = tempState.successorAstar();

                if (isGoal(tempState)) {
                    result(tempState);
                    return;
                }

                for (int i = 0; i < children.size(); i++) {
                    if (!(explored.containsKey(children.get(i).hash()))) {

                        Frontier.add(children.get(i));

                    }
                }
            }
        }



























    }
    private static boolean isGoal(State state){
        for (int i = 0; i < state.getGraph().size(); i++) {
            if(state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false;
            }
        }
        return true;
    }



    private static void result(State state){
        Stack<State> states = new Stack<State>();
        while (true){
            states.push(state);
            if(state.getParentState() == null){
                break;
            }
            else {
                state = state.getParentState();
            }
        }
        try {
            FileWriter myWriter = new FileWriter("AStarResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                State tempState = states.pop();
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId()+" ,");
                myWriter.write(tempState.outputGenerator()+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }








}