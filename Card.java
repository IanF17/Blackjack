public class Card {
    private String type;
    private String suit;
    private int points;

    // CONSTRUCTOR FOR CARD
    public Card(String type, String suit, int points) {
        this.type = type;
        this.suit = suit;
        this.points = points;
    }

    //GETTER METHODS
    public String getType() {
        return type;
    }

    public String getSuit() {
        return suit;
    }

    public int getPoints() {
        return points;
    }

    //SETTER METHODS
    //ASSIGNS NEW NUMBER OF POINTS TO A CARD
    public void setPoints(int newPoints) {
        points = newPoints;
    }

    //OVERRIDING METHOD
    public String toString() {
        return type + " of " + suit;
    }
}