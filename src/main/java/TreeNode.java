import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rugile on 9/6/16.
 */
public class TreeNode {
    private GameBoard gameBoard;
    public int whoseTurn;
    //nextBoards is arraylist of length 6 and each list element is a board that results in doing that move.
    public ArrayList<TreeNode> nextBoards;
    public TreeNode(GameBoard b, int wt){
        gameBoard = b;
        whoseTurn = wt;
        nextBoards = new ArrayList<TreeNode>();
    }
    public void generateList(){
        if(gameBoard.playerWon() != -1){
            return;
        }
        if(this.nextBoards.size() != 0){
            return;
        }
        Player p1 = gameBoard.getActivePlayer(0);
        Player p2 = gameBoard.getActivePlayer(1);
        for(int i = 0; i < 6; i++){
            GameBoard newg = new GameBoard(new Player(p1), new Player(p2));
            int res = newg.playerGo(whoseTurn, i);
            if(res != -1){
                TreeNode next = new TreeNode(newg, res);
                nextBoards.add(next);
            }else{
                nextBoards.add(null);
            }
        }
    }
    public Pair<Integer, Integer> getMinMax(int playerAi){
        Pair<Integer, Integer> retval = new Pair<Integer, Integer>(0, 0);
        if(this.nextBoards.size() == 0){
            retval.left = this.gameBoard.score(playerAi);
            retval.right = -1;
        }else{
            ArrayList<Integer> scores = new ArrayList<Integer>();
            ArrayList<Integer> allScores = new ArrayList<Integer>();
            for(int i = 0; i < nextBoards.size(); i++){
                try {
                    scores.add(nextBoards.get(i).getMinMax(playerAi).left);
                    allScores.add(nextBoards.get(i).getMinMax(playerAi).left);
                }catch(NullPointerException e){
                    allScores.add(null);
                }
            }
            if(whoseTurn==playerAi){
                retval.left = Collections.max(scores);
                retval.right = allScores.indexOf(retval.left);
            }else{
                retval.left = Collections.min(scores);
                retval.right = allScores.indexOf(retval.left);
            }
        }
        return retval;
    }
}





//so we need to find 2 elements with the biggest difference, where the 2nd one has to be a bigger integer
//[2 3 2 5 9 2 6]
//[2


// 3 2 1

// out of the three digits we take the biggest one - this is going to be our starting point - n
// then we say that he was there n days

//then on the arrival day, he could have omitted all the meals that appeaar before the biggeest digit meal
//so in this case - 0
//and on the leaving day he omitted all the meals afterwards - so in this case 2
//so we get (3-2)+(3-1)-2 = 1+2-2=1

//2 0 2 => 1
//first day: supper
//second day: breakfast and suppe
//last day:breakfast

//he was there 100 days
//he omitted


// 10.245
//we want to get each digit to max possible value and how many turns it takes to do this
//and then 
//so the recurrence relation will be max_grade(i) = max(