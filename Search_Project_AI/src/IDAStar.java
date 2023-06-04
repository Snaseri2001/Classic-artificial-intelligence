import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class IDAStar {



    public static int DFS(int cuttOff , State initialState){
        int min = Integer.MAX_VALUE ;
        Stack<State> Frontier = new Stack<State>() ;
        LinkedList<String> explored = new LinkedList<>() ;
        int counter1 = 0 ;
        //       Hashtable<String, Boolean> explored = new Hashtable<>();
        if(isGoal( initialState)){
            result(initialState) ;
            return 0;

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
            ArrayList<State> children = tempState.successorAstar();


                for(int i = 0;i<children.size();i++){
                    if(!(explored.contains(children.get(i).hash()))) {
                        if(children.get(i).AStar <= cuttOff) {
                            if (isGoal(children.get(i))) {
                                //  System.out.println("this is goal soroush");
                                result(children.get(i));
                                return 0;
                            }
                            Frontier.add(children.get(i));
                        }else {
                            int astar = children.get(i).AStar ;
                            if(astar < min){
                                min = astar ;
                        }
                    }
                }}





            counter++;
        }
        System.out.println(counter1);
        return min ;

    }






    public static  void search(State initialState){


    int  CuttOff = initialState.getHuristic() ;

     while (true){
        CuttOff = DFS(CuttOff ,initialState);
        if(CuttOff == 0) {
        return ;
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
            FileWriter myWriter = new FileWriter("IDSStarResult1.txt");
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











