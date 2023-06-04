import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ids {

    static boolean BFS( int  depth , State initialState) {
        initialState.depthOfState = 0;
        Queue<State> frontier = new LinkedList<State>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if (isGoal(initialState)) {
            result(initialState);
            return false;
        }
        frontier.add(initialState);
        inFrontier.put(initialState.hash(), true);
        while (!frontier.isEmpty()) {
            State tempState = frontier.poll();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(), true);
            ArrayList<State> children = tempState.successor();
            for (int o = 0; o < children.size(); o++) {
                children.get(o).depthOfState = children.get(o).getParentState().depthOfState + 1;
            }
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).depthOfState > depth) {
                    return false;
                }
                if (!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        result(children.get(i));
                        return true;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
        }
        return false ;
    }




    public static boolean DFS(int depth , State initialState){
        initialState.depthOfState = 0 ;
        Stack<State> Frontier = new Stack<State>() ;
        LinkedList<String> explored = new LinkedList<>() ;
        int counter1 = 0 ;
        //       Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal( initialState)){
            result(initialState) ;
            return true;

        }
        String hashInitioalState = initialState.hash() ;
        System.out.println("Exapande node in this level :");
        Frontier.add(initialState) ;

        int counter =1  ;
        while(!Frontier.isEmpty()){
counter1 += 1 ;
            State  tempState = Frontier.pop() ;
            if(counter>1) {
                explored.add(tempState.hash());

                State fatherState = tempState.getParentState();
                //   System.out.println(explored.size());//
                //System.out.println(fatherState.hash());
                int fatherStateIndex = explored.indexOf(fatherState.hash());
                //   System.out.println(fatherStateIndex);
                if(fatherStateIndex < explored.size()-1){
                    for (int y = fatherStateIndex+1 ; y < explored.size(); y++) {
                        explored.remove(y);
                    }
                }
            }

            explored.add(tempState.hash());
            //      System.out.println(explored.indexOf("rbgrrgr"));
            ArrayList<State> children = tempState.successor();
            for (int o = 0; o < children.size(); o++) {
                children.get(o).depthOfState = children.get(o).getParentState().depthOfState + 1;
            }
            if(children.get(0).depthOfState <= depth){
            for(int i = 0;i<children.size();i++){
                if(!(explored.contains(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        //  System.out.println("this is goal soroush");
                        result(children.get(i));
                        return true;
                    }
                    Frontier.add(children.get(i));

                }
            }}





            counter++;
        }
        System.out.println(counter1);
        return false ;

    }





    static void IDS(State initialState){
        int level = 1 ;
        while (true){
    System.out.println("level : " + level);
       boolean recieve = DFS(level , initialState) ;
        if(recieve){
            return ;
        }

            level+=1 ;
        }}






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
                FileWriter myWriter = new FileWriter("IDSResult1.txt");
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

