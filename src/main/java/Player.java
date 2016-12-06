/**
 * Created by rugile on 9/6/16.
 */

public class Player {
    public int[] seeds;
    public int claimed_seeds;
    public Player(int[] seeds, int claimed_seeds){
        this.seeds = seeds;
        this.claimed_seeds = claimed_seeds;
    }
    public Player(){
        this.seeds = new int[6];
        for(int i = 0; i < 6; i++){
            seeds[i] = 4;
        }
        this.claimed_seeds = 0;
    }
    public Player(Player p){
        this.seeds = p.seeds.clone();
        this.claimed_seeds = p.claimed_seeds;
    }
    public boolean seedsEmpty(){
        boolean ans = true;
        for(int i = 0; i < seeds.length; i++){
            ans &= (seeds[i] == 0);
        }
        return ans;
    }
    public int claimAllSeeds(){
        int ans = this.claimed_seeds;
        for(int x: seeds){
            ans += x;
        }
        return ans;
    }
}
