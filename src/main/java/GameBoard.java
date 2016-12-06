/**
 * Created by rugile on 9/6/16.
 */


public class GameBoard {
    private Player[] players;

    public GameBoard(Player player1, Player player2){
        this.players = new Player[2];
        players[0] = player1;
        players[1] = player2;
    }

    public int playerGo(int which, int x){

        int whoseNextTurn = 1 - which;
        int whoseSideOfTheBoard = which;
        if(x < 6 && x >= 0 && which >= 0 && which < 2){
            if(players[0].seeds[x] == 0){
                return -1;
            }else{
                int s = players[whoseSideOfTheBoard].seeds[x];
                players[whoseSideOfTheBoard].seeds[x] = 0;
                while(s > 0){
                    if(x == 5 && whoseSideOfTheBoard == which){
                        players[which].claimed_seeds += 1;
                        whoseSideOfTheBoard = 1 - whoseSideOfTheBoard;
                        s--;
                        x = 0;
                        if(s == 0){
                            whoseNextTurn = which;
                        }
                    }else if(x == 5){
                        whoseSideOfTheBoard = 1 - whoseSideOfTheBoard;
                        x = 0;
                    }else{
                        x++;
                    }
                    if(s > 0){
                        players[whoseSideOfTheBoard].seeds[x]++;
                        s--;
                    }
                }
                return whoseNextTurn;
            }
        }
        return -1;
    }

    public Player getActivePlayer(int i){
        return players[i];
    }

    public int playerWon(){
        if(players[0].claimed_seeds >= 25){
            return 0;
        }else if(players[1].claimed_seeds >= 25){
            return 1;
        }else if(players[0].seedsEmpty() && (players[1].claimAllSeeds() >= 25)){
            return 1;
        }else if(players[1].seedsEmpty() && (players[0].claimAllSeeds() >= 25)){
            return 0;
        }
        return -1;
    }

    public int score(int player){
        int[] scores = new int[players.length];
        for(int i = 0; i < 2; i++){
            //claimed seeds contribute to score
            scores[i] = players[i].claimed_seeds;

            //see if the other is starved out
            if(players[1-i].seedsEmpty()){
                scores[i] = players[i].claimAllSeeds();
                continue;
            }

            for(int j = 0; j < players[i].seeds.length; j++){
                int seedNumber = players[i].seeds[j];
                int maxClaimed = players[i].seeds.length - j;

                //calculate how many seeds go to you and how many to your opponent
                scores[i] +=  players[i].seeds[j] <= maxClaimed ? players[i].seeds[j]:maxClaimed;
                seedNumber -= maxClaimed;
                int claimingPlayer = 1 - i;
                while(seedNumber > 0){
                    scores[claimingPlayer] += seedNumber >= 6 ? 6:seedNumber;
                    seedNumber -= seedNumber >= 6? 6 : seedNumber;
                    claimingPlayer = 1 - i;
                }
            }
        }
        return scores[player] - scores[1-player];
    }

}
