import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SRBFS {


public void search(State initialState){
    int bound = Integer.MAX_VALUE ;
    initialState.CostOFThisStateForAStar = 0 ;
    initialState.AStar = initialState.getHuristic() ;
    SRBFS1(initialState , bound) ;
}

int counter ;
public int SRBFS1(State state , int bound) {
    counter +=1 ;
    System.out.println(counter);
    System.out.println(state.hash());
    System.out.println(state.F+"-------------"+bound);
    if (state.F > bound) {
        System.out.println("f > bound");
        return state.F;
    } else {
        if (isGoal(state)) {
            result(state);
            System.out.println("*************************************************************************" +
                    ")))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))");
            System.exit(1);
        } else {
            ArrayList<State> children = state.successorAstar();
            if (children.size() == 0) {
                return Integer.MAX_VALUE;
            } else {
                for (int i = 0; i < children.size(); i++) {
                    children.get(i).F = children.get(i).AStar;
                }
                Comparator<State> StateComparator = new Comparator<State>() {
                    @Override
                    public int compare(State s1, State s2) {
                        if(s1.F > s2.F){
                            return 1 ;
                        }else{
                            return -1 ;
                        }}
                };
                PriorityQueue<State> Frontier = new PriorityQueue<State>(StateComparator);
                for (int u = 0; u < children.size(); u++) {
                    Frontier.add(children.get(u));
                }
                State s1 = Frontier.poll();
                while (s1.F <= bound ) {
                    if (Frontier.size() + 1 == 1) {
                        s1.F = SRBFS1(s1, bound);
                    } else {
                        State s2 = Frontier.poll();
                        int min = s2.F;
                        if (min > bound) {
                            min = bound;
                      }
                        Frontier.add(s2);
                        Frontier.add(s1);
                        s1.F = SRBFS1(s1, min);
                    }
                    Frontier.add(s1);
                    s1 = Frontier.poll();
                }
                State S1 = Frontier.poll() ;
                int F1 = s1.F ;
                Frontier.add(S1) ;
                System.out.println("payany");
                return F1 ;

             }
        }

    }
    return 0 ;
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
            FileWriter myWriter = new FileWriter("SRBFSResult.txt");
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
