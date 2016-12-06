import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by rugile on 9/10/16.
 */
public class Tree {
    public TreeNode root;
    public int AI;
    public Tree(int whoIsAi){
        Player p1 = new Player();
        Player p2 = new Player();
        GameBoard gb = new GameBoard(p1, p2);
        TreeNode tn = new TreeNode(gb, 0);
        tn.generateList();
        this.AI = whoIsAi;
        this.root = tn;
    }
    public void calculateNextMove(){
        Scanner s = new Scanner(System.in);
        while(true){
            root.generateList();
            if(root.whoseTurn == this.AI){
                long t= System.currentTimeMillis();
                long end = t + 200;
                ArrayList<TreeNode> treenodes = new ArrayList<TreeNode>();
                treenodes.add(root);
                while (!treenodes.isEmpty() && System.currentTimeMillis() < end) {
                    try {
                        treenodes.get(0).generateList();
                        treenodes.addAll(treenodes.get(0).nextBoards);
                    }catch (NullPointerException e){
                    }

                    treenodes.remove(0);
                }
                Pair<Integer, Integer> p = root.getMinMax(AI);
                int i = p.right;

                System.out.println("I do the turn number " + i);
                System.out.println("Which has the score " + p.left);
                root = root.nextBoards.get(i);
            }else{
                root.generateList();
                System.out.println("Enter your move");
                int move = s.nextInt();
                try {
                    root = root.nextBoards.get(move);
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        }

    }
}
