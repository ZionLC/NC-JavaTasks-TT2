package trickytasks.task2;

public class GuessTheWordStart extends GuessTheWordBody{
    public static void main(String[] args) {
        System.out.println("Welcome to GUESS THE WORD!");
        System.out.println("Your task is to guess the next word:");
        GuessTheWordBody game = new GuessTheWordBody();
        game.start();
    }
}
