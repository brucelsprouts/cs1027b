/**
 * Class that represents the players name, position, and jersey number, with methods that can get, set, as well as other methods.
 * @author Bruce lin
 */

public class Player {
    private String name;
    private String position;
    private int jerseyNum;

    /**
     * Constructor to initialize a Player object with the specified name, position, and jersey number.
     * @param name       The name of the player.
     * @param position   The position the player plays.
     * @param jerseyNum  The jersey number of the player.
     */
    public Player(String name, String position, int jerseyNum) {
        this.name = name;
        this.position = position;
        this.jerseyNum = jerseyNum;
    }

    /**
     * Gets the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        // Get the player's name.

        return name;
    }

    /**
     * Gets the position of the player.
     * @return The position of the player.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets the jersey number of the player.
     * @return The jersey number of the player.
     */
    public int getJerseyNum() {
        return jerseyNum;
    }

    /**
     * Sets the name of the player.
     * @param newName The new name for the player.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the position of the player.
     * @param newPosition The new position for the player.
     */
    public void setPosition(String newPosition) {
        this.position = newPosition;
    }

    /**
     * Sets the jersey number of the player.
     * @param newJerseyNum The new jersey number for the player.
     */
    public void setJerseyNum(int newJerseyNum) {
        this.jerseyNum = newJerseyNum;
    }

    /**
     * Returns a string representation of the player in the format: "name: #jerseyNum".
     * @return A string representation of the player.
     */
    public String toString() {
        return this.name + ": #" + this.jerseyNum;
    }

    /**
     * Checks if the current player is equal to another player based on name and jersey number.
     * @param other The other player to compare.
     * @return True if the players are equal, false otherwise.
     */
    public boolean equals(Player other) {
        return this.name.equals(other.name) && this.jerseyNum == other.jerseyNum;
    }
}