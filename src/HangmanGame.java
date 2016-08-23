/*
This is a version of the game "hangman"
This program prompts the user to guess the randomly generated word
Author: Marcus Fong ©2016

Thanks to stackoverflow.com for java tips
Thanks to https://www.thegamegal.com/word-generator/ for the random words and difficulties
Thanks to https://www.vocabulary.com/lists/191545/ for the SAT vocab words 
Thanks to honors biology/chemistry class for supplying me with scientific vocabulary
Thanks to my APCS class for giving me the motivation to take the time to do this
*/

import java.util.Arrays;
import java.util.*;
import java.util.Scanner;

public class HangmanGame {

	static String[] easyDifficulty = new String[]{"orange", "jacket","shirt"
		,"rocket","airplane","circle","balloon","swing","truck","caterpillar"};
	
	static String[] medDifficulty = new String[]{"stomach", "surfboard","dolphin"
		,"penguin","tadpole","shallow","doormat","eagle","lunchbox","lipstick"};
	
	static String[] hardDifficulty = new String[]{"hydrogen", "friable","gasoline"
		,"pharmacist","firefighter","peasant","diagonal","vitamin","calamitous","drought"};
	
	static String[] harderDifficulty = new String[]{"aberration", "apathetic","circumscribe"
		,"iconoclast","insidious","obdurate","tangential","vitamin","pugnacious","zephyr"};
	
	static String[] impossible = new String[]{"beryllium", "californium","platyhelminthes"
		,"echinodermata","insidious","preposterous","tangential","seaborgium","deoxyribose","logorrhea"};
	
	
	
	
	static Random rand = new Random();
	static int randomNum;
	static String hiddenWord = new String("");
	static char[] hiddenWordToChar = hiddenWord.toCharArray();
	static int triesLeft = 6;
	static int length = hiddenWord.length();
	static Scanner input = new Scanner(System.in);
	final static int maxLength = 30;
	static char[] repeatChecker = new char[30]; //this is to help prevent the user from putting the same char multiple times
	static char[] fillWord = new char[length];
	static int var;
	static int var2;
	static char underscore = '_';
	
	static int chooseDifficultyInt;
	
	
	public static String randomWord(int n) {
		randomNum = rand.nextInt(9);
		int difficultyInt = n;
		if (difficultyInt == 1){
			//System.out.println(easyDifficulty[randomNum]);
			return easyDifficulty[randomNum];
		}
		else if (difficultyInt == 2){
			return medDifficulty[randomNum];
		}
		else if (difficultyInt == 3){
			return hardDifficulty[randomNum];
		}
		else if (difficultyInt == 4){
			return harderDifficulty[randomNum];
		}
		else if (difficultyInt == 5){
			return impossible[randomNum];
		}
		
			
		return null;
		
		
	}
	
	
	
	public static boolean contains(char[] arr, char i) {
	      for (char n : arr) {
	         if (i == n) {
	            return true;
	         }
	      }
	      return false;
	   }
	
	public static boolean multiLetter(char[] arr, char i){
		int multiple = 0;
		for (char n : arr){
			if (i == n){
				multiple++;
				
				//continue;
			}
		}
		//System.out.println(multiple);
		if (multiple > 1){
			return true;
		}
		else {
			return false;
		}
		
	}
	public static void createSpaces(int n){
		for (int i = 0; i <= n-1; i++){
			fillWord[i] = underscore;
		}
		System.out.println(fillWord);
	}
	
	public static void tryAgain(){
		System.out.println("Would you like to try again? Enter Y/N to go again or quit!");
		char goAgain = input.next().charAt(0);
		goAgain = Character.toLowerCase(goAgain);
		if (goAgain == 'y') {
			triesLeft = 6;
			repeatChecker = new char[20];
			main(null);
		}
		else if (goAgain == 'n') {
			System.out.println("Bye!");
			System.exit(0);
		}
		else {
			System.out.println("Invalid input");
			tryAgain();
		}
		
	}
	
	
	public static void arrayLetters(){
		System.out.println("This is a " + length + " letter word. Please enter a letter to guess: \n\n");
		char charInput = input.next().charAt(0);
		char[] letters = new char[20];
		//This code only runs if the user inputs a letter that repeats 
		//throughout hiddenWord
		if (multiLetter(hiddenWordToChar, charInput)){
			for (int n = 0; n < length; n++){
				char multiWordLetter = hiddenWord.charAt(n);
				letters[n] = multiWordLetter;
				if (letters[n] == charInput){
					fillWord[n] = charInput;
					
				}

				if (contains(repeatChecker, charInput)){
					System.out.println("You already did that word, try again!");
					arrayLetters();
				}

				if (Arrays.equals(fillWord, hiddenWordToChar)){
					System.out.println("Congratulations, you win! The word was '" + hiddenWord + "'!");
					System.out.println("You completed the challenge in " + triesLeft + " tries! \n\n");
					tryAgain();
				}
			}
			System.out.println(fillWord);
			System.out.println("Nice! There is a(n) " + charInput + " in this word!");
			System.out.println("You have " + triesLeft + " tries left!\n");
			arrayLetters();
		}
		
		
		//This block of code runs when the user input a letter that only occurs once
		//in hiddenWord
		
		for (int i = 0; i < length; i++){
			char wordLetter = hiddenWord.charAt(i);
			letters[i] = wordLetter;
			if (contains(letters, charInput)){
				/*
				if (multiLetter(letters, charInput)){
					System.out.println("aylmao");
				}
				*/
				//System.out.println(multiLetter(hiddenWordArray, charInput));
				if (contains(repeatChecker, charInput)){
					System.out.println("You already did that word, try again!");
					arrayLetters();
				}
				repeatChecker[var] = charInput;
				var++;
				fillWord[i] = charInput;
				if (Arrays.equals(fillWord, hiddenWordToChar)){
					System.out.println("Congratulations, you win! The word was '" + hiddenWord + "'!");
					System.out.println("You completed the challenge in " + triesLeft + " tries! \n\n");
					tryAgain();
				}
				System.out.println(fillWord);
				System.out.println("Nice! There is a(n) " + charInput + " in this word!");
				System.out.println("You have " + triesLeft + " tries left!\n");
				arrayLetters();
				
			}
			if (i == length-1){
				System.out.println("There is no " + charInput + " in this word!");
				System.out.println(fillWord);
				triesLeft--;
				System.out.println("You have " + triesLeft + " tries left!\n");
				if (triesLeft <= 0){ 
					System.out.println("You failed!\nThe hidden word was '" + hiddenWord + "'! \n");
					tryAgain();
					
				}
				arrayLetters();
				
			}
		}
	
		
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to my hangman game!");
		System.out.println("Please input your prefered difficulty level.");
		System.out.println("1. Easy. (All of the words are nouns.)");
		System.out.println("2. Medium. (All of the words are nouns.)");
		System.out.println("3. Hard. (More nouns then adjectives.)");
		System.out.println("4. Harder. (A good mix of some nouns and adjectives.)");
		System.out.println("5. Impossible... Good luck...");
		chooseDifficultyInt = input.nextInt();
		randomWord(chooseDifficultyInt);
		
		HangmanGame.hiddenWord = HangmanGame.randomWord(chooseDifficultyInt);
		//System.out.println(hiddenWord);
		//I redefine all the variables that have to do with hiddenWord
		length = hiddenWord.length();
		fillWord = new char[length];
		hiddenWordToChar = hiddenWord.toCharArray();
		
		createSpaces(length);
		arrayLetters();
	}
	
	


}
