import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class JumpJump extends GameEngine implements KeyListener {
	int WindowX = 700;
	int WindowY = 700;
	
	//Frames
	int currentFrame;
	int JumpFrame;
	int MenuCurrentFrame;
	
	//Images
	Image MainMenuHead;
	Image[] MainMenuGround;
	Image[] RunLeftDino;
	Image[] RunRightDino;
	Image[] IdleDino;
	Image[] DinoJump;
	
	//DINO MOVEMENT
	int DinoX = 50; 
	int DinoY = 450;


	Image BackgroundImage;

	int LeftBound; 
	int RightBound;
	boolean MR = false; //MENU RIGHT
	boolean ML = false; //MENU LEFT
	boolean IDLE = true;
	boolean MU = false;
	boolean MD = false;
	boolean peaked = false;
	boolean GameOver = false;
	

	//Asteroid
	int AsteroidAmount;
	double AsteroidX[], AsteroidY[];
	double AsteroidSX[], AsteroidSY[];
	double AsteroidVX[], AsteroidVY[];
	double AsteroidAngle[];
	Image AsteroidImage;
	boolean going = true;
	
	//Asteroid difficulty
	int AsteroidSpeed = 60;
	int AsteroidDif = 1;
	int counter = 0;
	int pointNum = 100;
	boolean PeakDif = false;
	
	//Health
	int health = 3;
	int AsteroidSpeed = 35;
	int AsteroidDif = 1;
	int counter = 0;
	
	//Health
	int health = 1;
	boolean GameOverManGameOver = false;
	//Scores
	int score = 0;
	int highScore = 0;
	static int importScore;
	//----------------------
	//Difficulty

	//new game
	//----------------------------
	public void initAsteroid() {
		AsteroidAmount = AsteroidDif; //INCREASE ASTEROIDS
		AsteroidX = new double[AsteroidAmount];
		AsteroidY = new double[AsteroidAmount];

		AsteroidSX = new double[AsteroidAmount];
		AsteroidSY = new double[AsteroidAmount];

		AsteroidVX = new double[AsteroidAmount];
		AsteroidVY = new double[AsteroidAmount];
		AsteroidAngle = new double[AsteroidAmount];
		
		AsteroidImage = loadImage("Extras//meteor.png");
		
		for (int i = 0; i<AsteroidAmount; i++) {
			newAsteroid(i);
		}
	}
	public void newAsteroid(int i) {
		AsteroidSX[i] = rand(700);
		AsteroidSY[i] = 0;
		
		AsteroidX[i] = AsteroidSX[i];
		AsteroidY[i] = AsteroidSY[i];
		
		double tx = rand(700);
		double ty = 700;
		AsteroidVX[i] = tx - AsteroidSX[i];
		AsteroidVY[i] = ty - AsteroidSY[i];
		AsteroidAngle[i] = atan2(AsteroidVX[i], -AsteroidVY[i]) - 90;
		
		double l = length(AsteroidVX[i], AsteroidVY[i]);
		AsteroidVX[i] *= AsteroidSpeed/l; 
		AsteroidVY[i] *= AsteroidSpeed/l;
	}
	
	public void updateAsteroid(double dt) {
		for (int i = 0; i <AsteroidAmount; i++) {
			AsteroidX[i] += AsteroidVX[i]*dt;
			AsteroidY[i] += AsteroidVY[i]*dt;
		}
	}
	
	public void drawAsteroid() {
		for (int i =0; i<AsteroidAmount;i++) {
			saveCurrentTransform();
			translate(AsteroidX[i], AsteroidY[i]);
			rotate(AsteroidAngle[i]);
			drawImage(AsteroidImage,0,0,64,64);
			restoreLastTransform();
		}
	}
	//Commented out are for testing
	public void AsteroidCollision() {
		for (int i = 0; i <AsteroidAmount; i++) {

			if ((AsteroidX[i] >= DinoX+15 && AsteroidX[i] <= DinoX+135) && (AsteroidY[i] >= DinoY-10 && AsteroidY[i] <= DinoY+10) ) { //DONT CHANGE NUMBERS, THEY MORE OR LESS WORK

			//System.out.print("AsteroidX: "+ AsteroidX[i]+ "\n");
			//System.out.print("AsteroidY: "+ AsteroidY[i]+ "\n");
			//System.out.print("DinoX: "+ DinoX+"\n");
			//System.out.print("DinoY: "+ DinoY+ "\n");
			if (AsteroidX[i] >= DinoX-50 && AsteroidX[i] <= DinoX+165 && AsteroidY[i] >= DinoY-50 && AsteroidY[i] <= DinoY+50 ) { //DONT CHANGE NUMBERS, THEY MORE OR LESS WORK
				HealthLoss();
			}
		}

	}
	
	public void init() {
		setWindowSize(WindowX,WindowY);
		IdleDino = new Image[30];
		IdleDino[0] = loadImage("IdleSprite\\Idle(1).png");
		IdleDino[1] = loadImage("IdleSprite\\Idle(2).png");
		IdleDino[2] = loadImage("IdleSprite\\Idle(3).png");
		IdleDino[3] = loadImage("IdleSprite\\Idle(4).png");
		IdleDino[4] = loadImage("IdleSprite\\Idle(5).png");
		IdleDino[5] = loadImage("IdleSprite\\Idle(6).png");
		IdleDino[6] = loadImage("IdleSprite\\Idle(7).png");
		IdleDino[7] = loadImage("IdleSprite\\Idle(8).png");
		IdleDino[8] = loadImage("IdleSprite\\Idle(9).png");
		IdleDino[9] = loadImage("IdleSprite\\Idle(10).png");
		
		RunRightDino = new Image[8];
		RunRightDino[0] = loadImage("RunRight\\RunRight(1).png");
		RunRightDino[1] = loadImage("RunRight\\RunRight(2).png");
		RunRightDino[2] = loadImage("RunRight\\RunRight(3).png");
		RunRightDino[3] = loadImage("RunRight\\RunRight(4).png");
		RunRightDino[4] = loadImage("RunRight\\RunRight(5).png");
		RunRightDino[5] = loadImage("RunRight\\RunRight(6).png");
		RunRightDino[6] = loadImage("RunRight\\RunRight(7).png");
		RunRightDino[7] = loadImage("RunRight\\RunRight(8).png");

		RunLeftDino = new Image[8];
		RunLeftDino[0] = loadImage("RunLeft\\RunLeft(1).png");
		RunLeftDino[1] = loadImage("RunLeft\\RunLeft(2).png");
		RunLeftDino[2] = loadImage("RunLeft\\RunLeft(3).png");
		RunLeftDino[3] = loadImage("RunLeft\\RunLeft(4).png");
		RunLeftDino[4] = loadImage("RunLeft\\RunLeft(5).png");
		RunLeftDino[5] = loadImage("RunLeft\\RunLeft(6).png");
		RunLeftDino[6] = loadImage("RunLeft\\RunLeft(7).png");
		RunLeftDino[7] = loadImage("RunLeft\\RunLeft(8).png");

		DinoJump = new Image[12];
		DinoJump[0] = loadImage("DinoJump\\Jump (1).png");
		DinoJump[1] = loadImage("DinoJump\\Jump (2).png");
		DinoJump[2] = loadImage("DinoJump\\Jump (3).png");
		DinoJump[3] = loadImage("DinoJump\\Jump (4).png");
		DinoJump[4] = loadImage("DinoJump\\Jump (5).png");		
		DinoJump[5] = loadImage("DinoJump\\Jump (6).png");
		DinoJump[6] = loadImage("DinoJump\\Jump (7).png");
		DinoJump[7] = loadImage("DinoJump\\Jump (8).png");
		DinoJump[8] = loadImage("DinoJump\\Jump (9).png");		
		DinoJump[9] = loadImage("DinoJump\\Jump (10).png");
		DinoJump[10] = loadImage("DinoJump\\Jump (11).png");
		DinoJump[11] = loadImage("DinoJump\\Jump (12).png");
		
		MainMenuGround = new Image[3];
		MainMenuGround[0] = loadImage("BasicGround\\Ground01.png");
		MainMenuGround[1] = loadImage("BasicGround\\Ground02.png");
		MainMenuGround[2] = loadImage("BasicGround\\Ground03.png");
		drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
		paintMainMenu();

		if (PLAYING) {
			StartGame();
		}
		if (GameOverManGameOver){
			GameOver();
		}

		AsteroidCollision();
		MoveDino();

	}
	

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		MenuCurrentFrame = (MenuCurrentFrame + 1) % 9;
		currentFrame = (currentFrame +2) % 8;
		JumpFrame = (JumpFrame+1)%12;
		updateAsteroid(dt);

	}
	public void StartGame(){

		if (going) {
			initAsteroid();
			going = false;
		}
		//BackgroundImage = loadImage("Extras/BG.png");
		drawImage(BackgroundImage,0,0,WindowX,WindowY);
		drawImage(MainMenuGround[0],25,625,100,100);
		drawImage(MainMenuGround[1],125,625,100,100);
		drawImage(MainMenuGround[1],225,625,100,100);
		drawImage(MainMenuGround[1],325,625,100,100);
		drawImage(MainMenuGround[1],425,625,100,100);
		drawImage(MainMenuGround[2],525,625,100,100);
		PaintScore();
		drawAsteroid();

		if(health == 3) {
			drawImage(IdleDino[0],500,0,100,100);
			drawImage(IdleDino[0],550,0,100,100);
			drawImage(IdleDino[0],600,0,100,100);
		}
		if(health == 2) {
			drawImage(IdleDino[0],500,0,100,100);
			drawImage(IdleDino[0],550,0,100,100);
		}
		if(health == 1) {
			drawImage(IdleDino[0],500,0,100,100);
		}
		if(health == 0) {
			GameOverManGameOver = true;;
		}


		for(int i = 0; i<AsteroidAmount;i++) {
			if (AsteroidY[i] >= 700) {
				score +=pointNum;
				if (score%500 == 0 && !PeakDif) {
					AsteroidDif++;
					AsteroidSpeed += 5;
					if (score == 1500) {
						PeakDif = true;
					}
				}
				score +=100;
				counter++;
				if(counter == AsteroidDif) {
					newAsteroid(i);
					going = true;
					counter = 0;
				}
			}
		}
	}
	private void GameOver() {
		PLAYING = false;
		drawText(30,200,"Sorry but you have lost!","Ariel",24);
		drawText(30,225,"Press shift Y to replay or shift N to exit!","Ariel",24);
		
	}
	@Override
	public void paintComponent() {

				// TODO Auto-generated method stub
		
	}

	public static void main(String args[]) {
		createGame(new JumpJump(importScore));

	}
	
	public JumpJump(int score) {
		JumpJump.importScore = score;
	}
	public void HealthLoss(){
		if (PLAYING) {
			health--;
			DinoX = 50;
			DinoY = 450;
			initAsteroid();
		}
	}
	public void MoveDino() {		
		if (IDLE) {
			drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
		}
		if (MR) {
			DinoX+=10;
			drawImage(RunRightDino[currentFrame],DinoX,DinoY,200,200);
		}
		if (ML) {
			DinoX-=10;
			drawImage(RunLeftDino[currentFrame],DinoX-64,DinoY,200,200); //WILL HAVE TO MINUS 64
												//Flipped in editor so have to compensate for empty space
		}
		if (MU) {
			DinoY-=10;
			drawImage(DinoJump[JumpFrame], DinoX, DinoY,200,200);

			}
		if (MD) {
			DinoY+=5;
			IDLE = true;
			if (DinoY == 450) {
				MD = false;
				MR = false;
				ML = false;
				IDLE = true;
			}
		}
		if (DinoX<LeftBound || DinoX>RightBound) {
			DinoY+=100;
		}
		if (DinoY>700) {
			HealthLoss();
		}
	}
	
	public void paintMainMenu() {
		BackgroundImage = loadImage("Extras/BG.png");
		MainMenuHead = loadImage("Extras/AsteroidAttack.png");
		drawImage(BackgroundImage,0,0,WindowX,WindowY);
		drawImage(MainMenuHead,125,50,450,50);
		drawText(75, 25,"HighScore: ","Arial", 24);
		drawTextHighScore(200, 25, "Arial", 24, importScore);

		
		drawImage(MainMenuGround[0],25,625,100,100);
		drawImage(MainMenuGround[1],125,625,100,100);
		drawImage(MainMenuGround[1],225,625,100,100);
		drawImage(MainMenuGround[1],325,625,100,100);
		drawImage(MainMenuGround[1],425,625,100,100);
		drawImage(MainMenuGround[2],525,625,100,100);

		LeftBound = -40;
		RightBound = 580;
		ShowHelp();		

		//LEFT AND RIGHT BOUND NEED TO BE +- 65 of INTIAL DRAWN POINTS
		// TODO Auto-generated method stub
	}
	public void ShowHelp() {
		if (showHelp) {
			Image tablet;
			tablet = loadImage("Extras/tablet.png");
			drawImage(tablet,150,225,350,300);
			changeColor(DGray);
			drawText(200,275,"Rules","Arial",18);
			drawText(180,300,"1: Use the A and D key to move left or right!","Arial",16);
			drawText(180,325,"2: Use the W key to jump!","Arial",16);
			drawText(180,350,"3: Avoid falling off the edges!","Arial",16);
			drawText(180,375,"4: Avoid the asteroids or any other danger!","Arial",16);
			drawText(180,400,"below will envelop you!","Arial",16);
		}
	}
	public void PaintScore() {
		drawText(25, 25, "Score: ", "Arial", 25);
		drawTextHighScore(100, 25, "Arial", 25, score);
	}
	public void keyPressed(KeyEvent e ) {
		if (e.getKeyCode() == KeyEvent.VK_D && e.getKeyCode() != KeyEvent.VK_W&&PLAYING) {
			IDLE = false;
			ML = false;
			MR = true;
			MU = false;
			if (DinoX>RightBound) {
				DinoY+=10;
			}
			if (DinoY>700) {
				HealthLoss();

			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_A && e.getKeyCode() != KeyEvent.VK_W &&PLAYING) {
			MR = false;
			IDLE = false;
			ML = true;
			MU = false;

			if (DinoX<LeftBound) {
				DinoY+=10;
			}
			if (DinoY>700) {
				HealthLoss();

			}

		}
		if (e.getKeyCode() == KeyEvent.VK_S&&PLAYING) {
			MR = false;
			IDLE = true;
			ML = false;
			MU = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W&&PLAYING) {
			IDLE = false;
			MU = true;
			MR = false;
			ML = false;
		}	
		if (e.getKeyCode() == KeyEvent.VK_Y) {
		if (e.getKeyCode() == KeyEvent.VK_Y&&PLAYING) {
			if(score>importScore) {
				JumpJump.importScore = score;
				
			}

			createGame(new JumpJump(importScore)); //not perfect, find another method maybe
			
		}

			createGame(new JumpJump(importScore));
		}
		//if (e.getKeyCode() == KeyEvent.VK_C) {
			//PLAYING = true;
		//}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W&&PLAYING) {
			MD = true;
			MU = false;
			ML = false;
			MR = false;
			IDLE = false;
		}
	}
	@Override
	protected void PausedGame() {
		initAsteroid();
		
	}

}
