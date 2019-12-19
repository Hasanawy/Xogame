package xo;

import java.util.Scanner;

public class Xo {
	int xo[][]; 
	int size ; // Size Is Always 3
	int winner ; // Who Is The Winner
	int player ; // 1 or 2
	int playerSign[] ; // 0 for (O) 1 for (X)
	String playerName[]; //The Name Of Player
	
	Scanner read = new Scanner(System.in); 

	
	// Constructor When called create 
	public Xo() {
		// Intionlization Values When The Game Start
		size = 3; 
		xo = new int [size][size];
		player = -1;
		winner = -1;
		playerName = new String [2];
		playerSign = new int [2]; // o-> 0 x-> 1 
		
	}
	public void fill() {
		//Fill The Array of xo[][] With -1 
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < size ; j ++) {
				xo[i][j] = -1;
			}
		}
	}
	public void start() {
		/*
		 * Welcome Method 
		 * Welcome Player One And Get His name ,,, Save The Name
		 * Get His Sign And Save It 0 -> 0 OR 1-> X
		 * */
		
		System.out.println("Hello Player One What is Your Name ?");
		playerName[0] = read.next();
		System.out.println("Hello "+playerName[0]+ " Please Choose your sign 0 for -> o OR 1 for -> x");
		playerSign[0] = read.nextInt();
		
		// When Player 1 Choose His Sign Player 2 Take The Other Sign Automaticly
		if(playerSign[0] == 0) {
			playerSign[1] = 1; 
		}else {
			playerSign[1] = 0;
		}
		
		//Welcome Player 2 -- Save His Name -- Show Him His Sign 
		System.out.println("Hello Player Two What is Your Name ?");
		playerName[1] = read.next();
		System.out.print("Hello "+playerName[1]+ " Your Sign Is "  );
		// Show The Sign Of Player 2 Because the signPlayer is integer and we need to show x and o
		showSign(1);
		
		//Printing The xo Game
		printXo();
		

		
		
	}
	
	public void showSign(int i) {
		//If player sign  = 0 print o
		if(playerSign[i] == 0) {
			System.out.print("o");
		}else if(playerSign[i] == 1) {
			//If player sign = 1 print x
			System.out.print("x");
		}
		//create new line
		System.out.println("");
	}
	
	
	
	
	
	
	
	
	public void printXo() {

			//	1	2	3 at first line
			System.out.print("\t  ");
			for(int i = 0 ; i<3 ; i++) {
				System.out.print((i+1)+"\t");
			}
			System.out.println("");
			for(int i =0 ; i< 3 ; i++) {
				// 1 2 3 vartical
				System.out.print("\t"+(i+1) + " ");
				
				for(int j = 0 ; j < size ; j++ ) {
					//Printing the main array --> if xo == -1 print "-" and if xo == 0 print "O" and if xo == 1 print "x" 
					if(xo[i][j] == -1) {
						System.out.print("-"+"\t");
					}else
						if(xo[i][j] == 0) {
						System.out.print("o"+"\t");
						}else if(xo[i][j] == 1) {
							System.out.print("x"+"\t");
						}
				
				}
				System.out.println("");
			}
			
			
			// After Printing The first array we change the player from -1 to 0-> player 1
			if(player == -1) {
				player = 0; //Set Player 1
			}
			
			//Get The Player Choice To Put His Sign
			//Must Have no winner to run the method

			if(winner == -1) {
				getPlayerChoice();
			}
			
			
		}
		
	
	public void getPlayerChoice() {
		// row , col to check if the place is already taken
		int row ;
		int col ;
		
		System.out.print(playerName[player]+" Please Select The Row Of Your " );
		showSign(player);
		row = read.nextInt();
		System.out.print(playerName[player]+" Please Select The Coluom Of Your "  );
		showSign(player);
		col = read.nextInt();
		
		//Check if the place is already taken
		checkEmptyPlace( row , col);
		
		
	}
	
	public void checkEmptyPlace(int row , int col) {
		if(xo[row-1][col-1] == 0 || xo[row-1][col-1] == 1  ) {
			// if the place == o -> O or 1 -> X So this is taken
			System.out.println("The Place Is Already Taken");
			//go back and make the player choose anthor place
			getPlayerChoice();
		}else {
			
			//If the place is not taken set the place to the sign of player 0 or 1
			xo[row-1][col-1] = playerSign[player];
			
			//Test The Xo Game If There Is A Winner If Not Compelete
			
			if(testXo()==true) {
				//Change To The Next Player
				nextPlayer();
				//Print Xo Again and get anthor place 
				printXo();
			}
			
			
			
			
		}
		
	}
	
	public void nextPlayer() {
		if(player == 0) {
			player = 1;
		}else if(player == 1) {
			player =0;
		}
		
	}
	
	
	public boolean testXo() {
		//Check if there is a line
		if(checkRow() || checkColoum() || checkDiagonals())
			winner = player;
		// if check winner true the game will end
		if(checkWinner()) {
			gameOver();
			return false;
		}
		return true;	
		
	}
	
	public boolean checkRow() {
		int rowSum = 0 ;
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < size ; j++) {
				if(xo[i][j] == -1) {
					//to execlude -1 + 0 + 0 == 0
					rowSum = rowSum - 7;
				}else {
					
					rowSum = rowSum + xo[i][j];
				}
				
			}
			
			if(rowSum == 3 || rowSum == 0) {
				//We Have a line here of X
				return true;
			}
			//After Every row rowSum = 0
			rowSum=0;
				
		}
		//No Winner 
		return false;
	}
	
	
	
	public boolean checkColoum() {
		int colSum = 0 ;
		for(int i = 0 ; i < size ; i ++) {
			for(int j = 0 ; j < size ; j++) {
				if(xo[j][i] == -1) {
					//to execlude -1 + 0 + 0 == 0
					colSum = colSum - 7;
				}else {
					
					colSum = colSum + xo[j][i];
				}				
				
			}
			
			if(colSum == 3 || colSum == 0) {
				//We Have a line here of X
				return true;
			}
			//After Everey Col Make colSum = 0
			colSum=0;
			
		}
		//No Winner 
		return false;
	}
	
	public boolean checkDiagonals() {
		int sum = 0;
		for(int i = 0 ; i < size ; i++) {
			if(xo[i][i] == -1) {
				//to execlude -1 + 0 + 0 == 0
				sum = sum - 7;
			}else {
				
				sum = sum + xo[i][i];
			}	
		}
		
		if(sum == 0 || sum == 3) {
			return true;
		}
		sum = 0;
		
		for(int i = 0 ; i < size ; i ++ ) {
			
			if(xo[i][size-i-1] == -1) {
				//to execlude -1 + 0 + 0 == 0
				sum = sum - 7;
			}else {
				
				sum = sum + xo[i][size-i-1];
			}
			
			
		}
		if(sum == 0 || sum == 3) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean checkWinner() {
		if(winner == 0) {
			printXo();
			System.out.println("Congratulation "+playerName[0] + " You Win!!!");
			return true;
		}else 
			if(winner == 1) {
				printXo();
				System.out.println("Congratulation "+playerName[1] + " You Win!!!");
				return true;
			}
		return false;
	}
	
	public void gameOver() {
		System.out.println("Do You Want To Play Again ?? 1-> Yes 2-> No");
		int choice = read.nextInt();
		if(choice == 1) {
			 new Xo();
			 System.out.println(winner);
		}else {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
