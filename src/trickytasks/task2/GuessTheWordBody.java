package trickytasks.task2;

import java.util.Scanner;

public class GuessTheWordBody {
    private String[] bank = {"toast","paradise","beer","collection","fridge","parrot","turtle","cannon","rectangle","sting","elevator"};
    private Scanner in = new Scanner(System.in);
    private int attempt = 1;
    private int random = (int) (Math.random() * bank.length);
    private String word = bank[random];
    private char[] wordm = word.toCharArray(); //Сreate an Char array so that we can later check if it contains the entered letter
    private String[] word_ = new String[word.length()]; //Create a word-length array to fill it with "_"

    //start() - start the game
    public void start() {
        //fill word_ with "_" and display it
        for (int i = 0; i < word.length(); i++) {
            word_[i] = "_";
        }
        output(word_);
        //until we have "_" in the word_ array, we will continue to enter letters
        while (contains(word_)) {
            exam(input());
        }
        attempt--; //One attempt should be subtracted because, due to the logic of the program, it increases attempt after checking for a letter.
        System.out.println("Congratulations! You guessed the word with " + attempt + " try!");
        System.out.println("Play again or end the game?");
        decision();

        return;
    }

    //contains() - checks for "_" in the array. If "_" still there, it returns true
    public boolean contains(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == "_") {
                return true;
            }
        }
        return false;
    }

    //decision() - exiting or replaying a game
    public void decision(){
        Scanner in = new Scanner(System.in);
        while (true) {  //repeats if there was an incorrect input
            System.out.print("Enter AGAIN if you want to repeat, or END if you want to end.");
            String decision = in.nextLine();
            switch (decision.toLowerCase()) {
                case "again":
                    override();
                    start();
                    return;
                case "end":
                    exit();
                default:
                    System.out.print("Incorrect input.");
            }
        }
    }

    //override() - overrides values for another game
    public void override() {
        System.out.println(word);
        this.random = (int) (Math.random() * bank.length);
        this.word = bank[random];
        this.word_ = new String[word.length()];
        this.wordm = word.toCharArray(); 
        this.attempt = 1;
    }

    //input() - method for entering a letter or word. Returns the entered value.
    public String input() {
        System.out.printf("%d try: Enter a letter or whole word: ", attempt);
        System.out.println();
        String letter = in.nextLine();
        return letter;
    }

    //output() - displays array by elements
    public void output(String[] array){
        System.out.print("The hidden word: ");
        for (int i = 0; i < word.length(); i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    //output() - processing the entered value
    public void exam(String letter) {

        if (letter.length() > 1) { //check. is it a word or a letter
            if (letter.equals(word)) {
                System.out.println("Congratulations! You guessed the word with " + attempt + " try!");
                System.out.println("Play again or end the game?");
                decision();
            } else {
                System.out.println("You have not guessed the word. You lose.");
                System.out.println("Play again or end the game?");
                decision();
            }
        } else {
            if (!(word.toLowerCase().contains(letter.toLowerCase()))) { //check. does the word contain a letter
                System.out.println("There is no such letter in the word. Try again.");
                output(word_);
                attempt++;
            } else {
                //looks for the presence of a letter in an array wordm. If there is a match, then in the encrypted array word_, at the same index, replaces "_" with the selected letter.
                for (int i = 0; i < word.length(); i++) {
                    char c = letter.toLowerCase().charAt(0);
                    if (wordm[i] == c) {
                        word_[i] = letter.toLowerCase();
                    }
                }
                output(word_);
                attempt++;
            }
        }
    }

    //exit() - just stop the program
    public void exit(){
        System.exit(0);
    }
}
