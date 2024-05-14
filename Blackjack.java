import java.util.Scanner;
import java.util.ArrayList;

public class Blackjack {
    public static void main (String[] args) {


        double money = 1000;
        boolean game = true;
        int rounds = 0;


        //PUTS 5 DECKS TOGETHER
        ArrayList<Deck> deckList = new ArrayList<Deck>();
        deckList.add(new Deck());
        deckList.add(new Deck());
        deckList.add(new Deck());
        deckList.add(new Deck());
        deckList.add(new Deck());

        Scanner input = new Scanner(System.in);

        // THE GAMEPLAY
        while(game) {

            System.out.println("Click \"Enter\" to start the game");
            String begin = input.nextLine();

            double bet = 0;
            while(bet < 25 || bet > money) {
                System.out.println("You have $" + money + ". How much would you like to bet? (Must be between 25 and " + money + ").");
                bet = input.nextDouble();
            }

            // CREATE PLAYER AND DEALERS' HANDS
            ArrayList<Card> pHand = new ArrayList<Card>();
            ArrayList<Card> pHand2 = new ArrayList<Card>();
            ArrayList<Card> dHand = new ArrayList<Card>();
            ArrayList<Card> discard = new ArrayList<Card>();

            int randDeck1 = (int)(Math.random()*5);
            int deckSize1 = deckList.get(randDeck1).getDeck().size();
            int randCard1 = (int)(Math.random()*deckSize1);

            pHand.add(deckList.get(randDeck1).getCard(randCard1));

            int pPts = deckList.get(randDeck1).getCard(randCard1).getPoints();

            discard.add(deckList.get(randDeck1).dealCard(randCard1));

            int randDeck2 = (int)(Math.random()*5);
            int deckSize2 = deckList.get(randDeck2).getDeck().size();
            int randCard2 = (int)(Math.random()*deckSize2);
            pHand.add(deckList.get(randDeck2).getCard(randCard2));

            pPts += deckList.get(randDeck2).getCard(randCard2).getPoints();

            discard.add(deckList.get(randDeck2).dealCard(randCard2));

            int randDeck3 = (int)(Math.random()*5);
            int deckSize3 = deckList.get(randDeck3).getDeck().size();
            int randCard3 = (int)(Math.random()*deckSize3);
            int dPts = deckList.get(randDeck3).getCard(randCard3).getPoints();

            dHand.add(deckList.get(randDeck3).getCard(randCard3));

            discard.add(deckList.get(randDeck3).dealCard(randCard3));

            boolean bj = ((pHand.get(0).getPoints()==(10) && pHand.get(1).getType().equals("Ace")) || (pHand.get(1).getPoints()==(10) && pHand.get(0).getType().equals("Ace")));
            boolean q = dHand.get(0).getType().equals("Ace");
            if(pHand.get(0).getType().equals("Ace") && pHand.get(1).getType().equals("Ace")) {
                pPts = pPts - 10;
            }

            System.out.println("You have " + pHand + ". That's " + pPts + " points.");
            System.out.println("The dealer has a " + dHand + ". That's " + dPts + " points.");
            System.out.println("");

            // OPTION TO SPLIT IF PLAYERS CARDS ARE THE SAME
            String split = "";
            if(pHand.get(0).getType().equals(pHand.get(1).getType()) && money-bet*2 >= 0 && !pHand.get(0).getType().equals("Ace")){
                System.out.println("Would you like to split cards? (\"yes\"(y) or \"no\"(n))");
                String decoy5 = input.nextLine();
                split = input.nextLine();
            }
            boolean splist = split.equals("yes") || split.equals("y");

            //OPTION TO BUY INSURANCE IF THE DEALER HAS AN ACE
            String insure = "";
            if(q && !bj && money-bet >= bet/2 && !splist){
                System.out.println("The dealer has an Ace and may have blackjack. Would you like to buy insurance? (\"yes\"(y) or \"no\"(n))");
                if(!(split.equals("no") || (split.equals("n")))) {
                    String decoy3 = input.nextLine();
                }
                insure = input.nextLine();
                if(insure.equals("yes") || insure.equals("y")) {
                    money -= bet/2;
                }
            }

            //OPTION TO SURRENDER IF YOU DON'T LIKE YOUR STARTING CARDS
            String surr = "";
            if(!bj && !(insure.equals("yes") || insure.equals("y")) && !splist) {
                System.out.println("Would you like to surrender? This round will be over and you will recieve half of your initial bet. (\"yes\"(y) or \"no\"(n))");
                if(!(insure.equals("no") || insure.equals("n")) && !(split.equals("no") || split.equals("n"))) {
                    String decoy4 = input.nextLine();
                }
                surr = input.nextLine();
            }

            String hitStay = "";
            String dbl = "";

            //OPTION TO DOUBLE DOWN IF PLAYER WOULD LIKE ONLY ONE MORE CARD
            if(!bj && !(surr.equals("yes") || surr.equals("y")) && (money - bet*2 >= 0) && !splist) {
                System.out.println("Would you like to double down? You must double your bet and will get one more card. (\"yes\"(y) or \"no\"(n))");
                dbl = input.nextLine();
            }
            if(dbl.equals("yes") || dbl.equals("y")) {
                money -= bet;

                hitStay = "h";
            }

            boolean surst = (surr.equals("yes") || surr.equals("y"));

            // IF PLAYER SPLITS CARDS
            int pPts2 = 0;
            if(splist) {
                money -= bet;
                pHand2.add(pHand.remove(1));
                pPts -= pHand2.get(0).getPoints();
                pPts2 += pHand2.get(0).getPoints();

                int randDeck4 = (int)(Math.random()*5);
                int deckSize4 = deckList.get(randDeck4).getDeck().size();
                int randCard4 = (int)(Math.random()*deckSize4);

                pPts = pPts + deckList.get(randDeck4).getCard(randCard4).getPoints();

                pHand.add(deckList.get(randDeck4).getCard(randCard4));
                discard.add(deckList.get(randDeck4).dealCard(randCard4));

                int randDeck6 = (int)(Math.random()*5);
                int deckSize6 = deckList.get(randDeck6).getDeck().size();
                int randCard6 = (int)(Math.random()*deckSize6);
                pPts2 = pPts2 + deckList.get(randDeck6).getCard(randCard6).getPoints();
                pHand2.add(deckList.get(randDeck6).getCard(randCard6));
                discard.add(deckList.get(randDeck6).dealCard(randCard6));

                boolean ac = true;
                boolean ac2 = true;
                System.out.println("In one hand you have " + pHand + ". That's " + pPts + " points.");
                System.out.println("In your other hand you have " + pHand2 + ". That's " + pPts2 + " points.");
                System.out.println("Would you like to hit on your first hand? (\"yes\"(y) or \"no\"(n))");
                String ht = input.nextLine();
                boolean hist = ht.equals("yes") || ht.equals("y");
                while(hist == true && pPts <= 21) {
                    int randDeck5 = (int)(Math.random()*5);
                    int deckSize5 = deckList.get(randDeck5).getDeck().size();
                    int randCard5 = (int)(Math.random()*deckSize5);
                    pPts += deckList.get(randDeck5).getCard(randCard5).getPoints();
                    pHand.add(deckList.get(randDeck5).getCard(randCard5));
                    discard.add(deckList.get(randDeck5).dealCard(randCard5));
                    while(ac) {
                        for(int p = 0; p < pHand.size(); p++) {
                            if(pHand.get(p).getType().equals("Ace") && pPts > 21) {
                                pPts = pPts - 10;
                            }
                        }
                        ac = false;
                    }
                    System.out.println("In your first hand you have " + pHand + ". That's " + pPts + " points.");
                    if(pPts < 21) {
                        System.out.println("Would you like to hit again on your first hand? (\"yes\"(y) or \"no\"(n))");
                        String agan = input.nextLine();
                        if(agan.equals("no") || agan.equals("n")) {
                            hist = false;
                            //System.out.println("In your first hand you have " + pHand + ". That's " + pPts + " points.");
                        }
                    }
                    if(pPts >= 21) {
                        hist = false;
                    }
                }

                System.out.println("In your second hand you have " + pHand2 + ". That's " + pPts2 + " points.");
                System.out.println("Would you like to hit on your second hand? (\"yes\"(y) or \"no\"(n))");
                String ht2 = input.nextLine();
                boolean hist2 = ht2.equals("yes") || ht2.equals("y");

                while(hist2 == true && pPts2 <= 21) {
                    int randDeck5 = (int)(Math.random()*5);
                    int deckSize5 = deckList.get(randDeck5).getDeck().size();
                    int randCard5 = (int)(Math.random()*deckSize5);
                    pPts2 += deckList.get(randDeck5).getCard(randCard5).getPoints();
                    pHand2.add(deckList.get(randDeck5).getCard(randCard5));
                    discard.add(deckList.get(randDeck5).dealCard(randCard5));
                    while(ac2) {
                        for(int p = 0; p < pHand2.size(); p++) {
                            if(pHand2.get(p).getType().equals("Ace") && pPts2 > 21) {
                                pPts2 = pPts2 - 10;
                            }
                        }
                        ac2 = false;
                    }
                    System.out.println("In your second hand you have " + pHand2 + ". That's " + pPts2 + " points.");
                    if(pPts2 < 21) {
                        System.out.println("Would you like to hit again on your second hand? (\"yes\"(y) or \"no\"(n))");
                        String agan = input.nextLine();
                        if(agan.equals("no") || agan.equals("n")) {
                            hist2 = false;
                        }
                    }
                }

                hitStay = "s";
            }

            //IF PLAYER DOESN'T SPLIT, SURRENDER, OR DOUBLE DOWN
            if(!bj && !surst && !(dbl.equals("yes") || dbl.equals("y")) && !splist) {
                System.out.println("Would you like to hit(h) or stay(s)?");
                if(!(insure.equals("yes") || insure.equals("y")|| insure.equals("no") || insure.equals("n")) && surst) {
                    String decoy = input.nextLine();
                }
                hitStay = input.nextLine();
            }

            boolean hs = true;

            // IF PLAYER GETS BLACKJACK, THEY WIN 1.5 OF THEIR BET
            if(bj) {
                System.out.println("Congrats! You got blackjack!");
                money += bet*1.5;
                System.out.println("Your total is $" + money + ".");
                hs = false;
            }

            if(surst) {
                hs = false;
            }

            //DEALER TURN
            while(hs) {
                boolean pl = true;
                boolean pk = true;
                if(hitStay.equals("s") || hitStay.equals("stay")) {
                    int randDeck4 = (int)(Math.random()*5);
                    int deckSize4 = deckList.get(randDeck4).getDeck().size();
                    int randCard4 = (int)(Math.random()*deckSize4);
                    dPts = dPts + deckList.get(randDeck4).getCard(randCard4).getPoints();

                    dHand.add(deckList.get(randDeck4).getCard(randCard4));

                    discard.add(deckList.get(randDeck4).dealCard(randCard4));
                    while(pl) {
                        for(int p = 0; p < dHand.size(); p++) {
                            if(dPts > 21) {
                                if(dHand.get(p).getType().equals("Ace") && dPts > 21) {
                                    dPts = dPts - 10;
                                }
                            }
                        }
                        pl = false;
                    }
                    System.out.println("The dealer has " + dHand + ". That's " + dPts + " points.");
                    while(dPts < 17) {
                        int randDeck5 = (int)(Math.random()*5);
                        int deckSize5 = deckList.get(randDeck5).getDeck().size();
                        int randCard5 = (int)(Math.random()*deckSize5);
                        //dPts += deckList.get(randDeck5).getCard(49).getPoints();
                        dPts += deckList.get(randDeck5).getCard(randCard5).getPoints();
                        //dHand.add(deckList.get(randDeck5).getCard(49));
                        dHand.add(deckList.get(randDeck5).getCard(randCard5));
                        discard.add(deckList.get(randDeck5).dealCard(randCard5));
                        if(dPts < 17) {
                            System.out.println("The dealer has " + dHand + ". That's " + dPts + " points.");
                        }
                        while(pk) {
                            if(dPts > 21) {
                                for(int p = 0; p < dHand.size(); p++) {
                                    if(dHand.get(p).getType().equals("Ace") && dPts > 21) {
                                        dPts = dPts - 10;
                                    }
                                }
                            }
                            pk = false;
                        }
                    }
                    boolean dbj = ((dHand.get(0).getPoints()==(6) && dHand.get(1).getType().equals("Ace")) || (dHand.get(1).getPoints()==(6) && dHand.get(0).getType().equals("Ace")));
                    if(dbj) {
                        int randDeck6 = (int)(Math.random()*5);
                        int deckSize6 = deckList.get(randDeck6).getDeck().size();
                        int randCard6 = (int)(Math.random()*deckSize6);
                        dPts += deckList.get(randDeck6).getCard(randCard6).getPoints();
                        dHand.add(deckList.get(randDeck6).getCard(randCard6));
                        discard.add(deckList.get(randDeck6).dealCard(randCard6));
                    }

                    if(dPts >=17 && dPts <= 21) {
                        System.out.println("The dealer has " + dHand + ". That's " + dPts + " points. The dealer must stay.");
                        hs = false;
                    }
                    if(dPts > 21) {
                        System.out.println("The dealer has " + dHand + ". That's " + dPts + " points. The dealer busts.");
                        hs = false;
                    }
                }

                //PLAYER TURN
                boolean ps = true;
                if((hitStay.equals("h") || hitStay.equals("hit")) && !surst) {
                    boolean hit = true;
                    while(hit) {
                        int randDeck5 = (int)(Math.random()*5);
                        int deckSize5 = deckList.get(randDeck5).getDeck().size();
                        int randCard5 = (int)(Math.random()*deckSize5);

                        pPts += deckList.get(randDeck5).getCard(randCard5).getPoints();

                        pHand.add(deckList.get(randDeck5).getCard(randCard5));
                        discard.add(deckList.get(randDeck5).dealCard(randCard5));
                        while(ps) {
                            if(pPts > 21) {
                                for(int p = 0; p < pHand.size(); p++) {
                                    if(pHand.get(p).getType().equals("Ace") && pPts > 21) {
                                        pPts = pPts - 10;
                                    }
                                }
                            }
                            ps = false;
                        }
                        //PLAYER STATUS AFTER HITTING
                        System.out.println("You have " + pHand + ". That's " + pPts + " points.");
                        if(pPts <= 21 && !(dbl.equals("yes") || dbl.equals("y"))) {
                            System.out.println("Would you like to hit again? (Answer \"yes\" or \"no\").");
                            String hitting = input.nextLine();
                            if(hitting.equals("yes") || hitting.equals("y")) {
                                hit = true;
                            }
                            if((hitting.equals("no") || hitting.equals("s") || hitting.equals("n")) || (dbl.equals("y") || dbl.equals("yes"))) {
                                hit = false;
                                hitStay = "s";
                            }
                        }
                        if(dbl.equals("yes") || dbl.equals("y")) {
                            hit = false;
                            hitStay = "s";
                        }


                        if(pPts > 21) {
                            System.out.println("You bust!");
                            hit = false;
                            hitStay = "s";
                        }

                    }
                }
            }
            boolean d = true;

            if(bj){
                d = false;
            }

            //COMPARISON AND CONCLUSION
            while(d && !(surst) && !splist) {
                System.out.println("You have " + pPts + ". The dealer has " + dPts + ".");
                if((pPts > dPts && pPts <= 21) || (pPts <= 21 && dPts > 21)) {
                    System.out.println("Congrats! You won this round.");
                    money += bet;
                }


                if((pPts < dPts && dPts <= 21) || pPts > 21) {
                    System.out.println("Oh no! You lost! Better luck next time ;)");
                    money -= bet;
                }
                if(((dPts < pPts && pPts <= 21) || (dPts > 21 && pPts <= 21)) && (dbl.equals("yes") || dbl.equals("y"))) {
                    money += bet*2;
                }
                if((q == true && dHand.get(1).getPoints() == 10) && (insure.equals("yes") || insure.equals("y"))) {
                    money += bet*1.5;
                }
                else if(pPts == dPts) {
                    System.out.println("You tied with the dealer! You neither win nor lose.");
                }
                System.out.println("Your total is $" + money + ".");
                d = false;
            }


            // COMPARISON AND CONCLUSION IF PLAYER SPLIT
            if(splist) {
                System.out.println("");
                System.out.println("In your first hand you have " + pPts + ". The dealer has " + dPts + ".");
                if((pPts > dPts && pPts <= 21) || (pPts <= 21 && dPts > 21)) {
                    System.out.println("Congrats! This hand won.");
                    money += bet;
                }
                if((pPts < dPts && dPts <= 21) || pPts > 21) {
                    System.out.println("Oh no! This hand lost! Better luck next time ;)");
                    money -= bet;
                }
                if(pPts == dPts) {
                    System.out.println("This hand tied with the dealer! You neither win nor lose.");
                }

                System.out.println("In your second hand you have " + pPts2 + ". The dealer has " + dPts + ".");
                if((pPts2 > dPts && pPts2 <= 21) || (pPts2 <= 21 && dPts > 21)) {
                    System.out.println("Congrats! This hand won.");
                    money += bet*2;
                }
                if((pPts2 < dPts && dPts <= 21) || pPts2 > 21) {
                    System.out.println("Oh no! This hand lost! Better luck next time ;)");
                }
                if(pPts2 == dPts) {
                    System.out.println("This hand tied with the dealer! You neither win nor lose.");
                    money += bet;
                }
                System.out.println("Your total is $" + money + ".");
            }

            if(surst) {
                money -= bet/2;
                System.out.println("You have surrendered. You now have $" + money + ".");
            }

            System.out.println("");
            System.out.println("");

            // GAME ENDS IF PLAYER HAS <$25 OR IF PLAYERS CHOOSES TO QUIT
            if(money < 25) {
                game = false;
                System.out.println("You don't have enough money to play. Come back later.");
            }
            if(money >= 25) {
                System.out.println("Would you like to play again? \"yes\"(y) or \"no\"(n)");
                if((bj)) {
                    String decoy2 = input.nextLine();
                }
                String replay = input.nextLine();
                if(replay.equals("y") || replay.equals("yes")) {
                    game = true;
                }
                if(replay.equals("n") || replay.equals("no")) {
                    game = false;
                    System.out.println("You ended with $" + money + ". Thanks for playing!");
                }
            }

            System.out.println("");
            System.out.println("");
            System.out.println("");
            rounds++;

            if(rounds == 50) {
                for(int i = 0; i < 5; i++) {
                    deckList.remove(0);
                }
                for(int i = 0; i < 5; i++) {
                    deckList.add(new Deck());
                }
            }

            if(rounds > 50) {
                rounds = 0;
            }
        }
        input.close();
    }
}