

import javax.xml.transform.Result;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Enumeration ;

    /**
     *
     * @author Patric
     */
    public class BidirectionalSearcjh {

    public static Stack<State>  Result = new Stack<State>();

        public static void BidirectioanlSearch(State initialState , State Goal){
            Queue<State> frontier1 = new LinkedList<State>();
            Hashtable<String, Boolean>  inFrontier1 = new Hashtable<>();
            Hashtable<String, Boolean>  explored1 = new Hashtable<>();

            frontier1.add(initialState);
            inFrontier1.put(initialState.hash(),true);

            Queue<State> frontier2 = new LinkedList<State>();
            Hashtable<String, Boolean>  inFrontier2 = new Hashtable<>();
            Hashtable<String, Boolean>  explored2 = new Hashtable<>();

            frontier2.add(Goal);
            inFrontier2.put(Goal.hash(),true);


            int interSection = -1;

            while( !frontier1.isEmpty() && !frontier2.isEmpty() && interSection  == -1 ){

                BFSsearch(  frontier1 ,  inFrontier1 , explored1 ) ;
                BFSsearch(  frontier2 ,  inFrontier2 , explored2 ) ;

               boolean in2 = false ;
               String keyGoal ="";
                Enumeration<String> enumeration = inFrontier1.keys();
                while (enumeration.hasMoreElements()){
                    String key =enumeration.nextElement() ;

                    if(inFrontier2.containsKey(key)){
                        keyGoal = key ;
                        in2 = true ;
                        break ;}}

                       if(in2 == true){
                           int c1 = frontier2.size() ;
                        for(int i = 0; i < c1 ; i++){
                            State state1= frontier2.poll() ;
                            if(state1.hash().equals(keyGoal) ){
                                Stack<State>  firstResult = new Stack<State>();
                                while (true){
                                    firstResult.add(state1);
                                    if(state1.getParentState() == null){
                                        break;
                                    }
                                    else {
                                        state1 = state1.getParentState();
                                    }
                                }
                                int c = firstResult.size() ;
                                for(int ui = 0 ; ui < c ; ui ++ ){
                                    Result.add(firstResult.pop()) ;
                                }
                                break ;
                            }



                        }

                    int c2 = frontier1.size() ;
                    for(int i = 0; i < c2 ; i++){
                        State state1= frontier1.poll() ;
                        if(state1.hash().equals(keyGoal)){
                        state1 = state1.getParentState() ;
                            while (true){
                                Result.add(state1);
                                if(state1.getParentState() == null){
                                    break;
                                }
                                else {
                                    state1 = state1.getParentState();
                                }
                            }


                            break ;
                        }

                    }
                        result() ;
                    break ;
                    }

                }

            }







        public static void BFSsearch( Queue<State> frontier , Hashtable<String, Boolean>  inFrontier , Hashtable<String, Boolean> explored ){


            State tempState = frontier.poll();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(),true);
            ArrayList<State> children = tempState.successor();
            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {

                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }

            }
        }



        private static void result(){


            try {
                FileWriter myWriter = new FileWriter("BidirectionalResult.txt");
                System.out.println("initial state : ");
                while (!Result.empty()){
                    State tempState = Result.pop();
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







