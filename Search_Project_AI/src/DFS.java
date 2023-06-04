import java.io.FileWriter;
import java.io.IOException;
import java.util.* ;
import java.util.ArrayList ;
import java.lang.* ;

public class DFS {
    public static void Search(State initialState){
        Stack<State> frontier = new Stack<State>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal(initialState)){
            result(initialState);
            return;
        }
        frontier.add(initialState);
        inFrontier.put(initialState.hash(),true);
        while (!frontier.isEmpty()){
            State tempState = frontier.pop();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(),true);
            ArrayList<State> children = tempState.successor();
            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        result(children.get(i));
                        return;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
        }
    }
//    public static void Search(State initialState){
//        Stack<State> Frontier = new Stack<State>() ;
//        LinkedList<String> explored = new LinkedList<>() ;
//        explored.add(initialState.hash()) ;
//
//    //   Iterator <String> m_iterator=explored.iterator();
//
// //       Hashtable<String, Boolean> explored = new Hashtable<>();
//        if(isGoal( initialState)){
//            result(initialState) ;
//            return ;
//
//        }
//        String hashInitioalState = initialState.hash() ;
//        System.out.println(hashInitioalState);
//        Frontier.add(initialState) ;
//
//        int counter =1  ;
//        while(!Frontier.isEmpty()){
//
//            State  tempState = Frontier.pop() ;
//            if(counter>1) {
//                explored.add(tempState.hash());
//
//                State fatherState = tempState.getParentState();
//             // System.out.println(explored.size());//
//                //  System.out.println(fatherState.hash());
//                int fatherStateIndex = explored.indexOf(fatherState.hash());
//             //   System.out.println(fatherStateIndex);
//                if(fatherStateIndex < explored.size()-1){
//                for (int y = fatherStateIndex+1 ; y < explored.size(); y++) {
//                    explored.remove(y);
//                }
//            }
//            }
//
//            explored.add(tempState.hash());
//      //      System.out.println(explored.indexOf("rbgrrgr"));
//            ArrayList<State> children = tempState.successor();
//            for(int i = 0;i<children.size();i++){
//                if(!(explored.contains(children.get(i).hash()))) {
//                    if (isGoal(children.get(i))) {
//                      //  System.out.println("this is goal soroush");
//                        result(children.get(i));
//                        return;
//                    }
//                    Frontier.add(children.get(i));
//
//                }
//            }
//
//
//
//
//
//            counter++;
//        }
//
//    }
//



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
        Stack<State>  states = new Stack<State>();
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
            FileWriter myWriter = new FileWriter("DfsResult.txt");
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