import java.util.ArrayList;

public class Deck {
    static final String[] cardTypes = {"2", "3", "4", "5", "6",
            "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    static final String[] cardSuits = {"Clubs", "Diamonds",
            "Spades", "Hearts"};
    static final int[] cardPoints = {2, 3, 4, 5, 6, 7, 8, 9, 10,
            10, 10, 10, 11};

    //instance variable
    //ArrayList of the cards in a 52-card deck
    private ArrayList<Card> fullDeck;


    //Constructor. Initializes fullDeck with all 52 possible cards.
    public Deck() {
        fullDeck = new ArrayList<Card>();
        for(int i = 0; i < cardTypes.length; i++) {
            for(int p = 0; p < cardSuits.length; p++) {
                fullDeck.add(new Card(cardTypes[i], cardSuits[p], cardPoints[i]));
            }
        }

    }

    //getter method for fullDeck
    public ArrayList<Card> getDeck() {
        return fullDeck;
    }

    //gets individual card from fullDeck (with replacement)
    public Card getCard(int index) {
        return fullDeck.get(index);
    }

    //pulls individual card from fullDeck (without replacement)
    public Card dealCard(int index) {
        return fullDeck.remove(index);
    }


}
    
    
    
    
    
  