/**
 * Class that represents the players name, position, and jersey number, with methods that can get, set, as well as other methods.
 * @author Bruce lin
 */

public class Player {
    private String name;
    private String position;
    private int jerseyNum;

    public Player(String name, String position, int jerseyNum){
        /*Constructor to initialize instance variables */


        this.name = name;
        this.position = position;
        this.jerseyNum = jerseyNum;
    }

    public String getName(){
        // Get the player's name.
        return name;
    }

    public String getPosition(){
        return position;
    }

    public int getJerseyNum(){
        return jerseyNum;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPosition(String newPosition){
        this.position = newPosition;
    }

    public void setJerseyNum(int newJerseyNum){
        this.jerseyNum = newJerseyNum;
    }

    public String toString() {
        return this.name + ": #" + this.jerseyNum;
    }
    
    public boolean equals(Player other) {
        return this.name.equals(other.name) && this.jerseyNum == other.jerseyNum;
    }   
}