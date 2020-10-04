import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JumpJump extends GameEngine implements KeyListener {
	int WindowX = 700;
	int WindowY = 700; 
	Image[] frames;
	int currentFrame;
	int JumpFrame;
	//MENU
	int MenuCurrentFrame;
	
	Image MainMenuHead;
	Image[] MainMenuGround;

	//DINO MOVEMENT
	Image[] RunLeftDino;
	Image[] RunRightDino;
	Image[] IdleDino;
	Image[] DinoJump;
	int DinoX = 50; 
	int DinoY = 450;
	int jumpingCheck = DinoY-40;
	int i = 0;


	Image BackgroundImage;

	int LeftBound; 
	int RightBound;
	boolean MR = false; //MENU RIGHT
	boolean ML = false; //MENU LEFT
	boolean IDLE = true;
	boolean MU = false;
	boolean MD = false;
	boolean peaked = false;
	

	
	//----------------------
	///////Load jump png 
	///////FIX JUMP
	
	//----------------------------
	
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
		
		RunRightDino = new Image[5];
		RunRightDino[0] = loadImage("RunRight\\RunRight(1).png");
		RunRightDino[1] = loadImage("RunRight\\RunRight(2).png");
		RunRightDino[2] = loadImage("RunRight\\RunRight(3).png");
		RunRightDino[3] = loadImage("RunRight\\RunRight(4).png");
		RunRightDino[4] = loadImage("RunRight\\RunRight(5).png");

		RunLeftDino = new Image[5];
		RunLeftDino[0] = loadImage("RunLeft\\RunLeft(1).png");
		RunLeftDino[1] = loadImage("RunLeft\\RunLeft(2).png");
		RunLeftDino[2] = loadImage("RunLeft\\RunLeft(3).png");
		RunLeftDino[3] = loadImage("RunLeft\\RunLeft(4).png");
		RunLeftDino[4] = loadImage("RunLeft\\RunLeft(5).png");

		DinoJump = new Image[12];
		DinoJump[0] = loadImage("DinoJump\\Jump (1).png");
		DinoJump[1] = loadImage("DinoJump\\Jump (2).png");
		DinoJump[2] = loadImage("DinoJump\\Jump (3).png");
		DinoJump[3] = loadImage("DinoJump\\Jump (4).png");
		DinoJump[4] = loadImage("DinoJump\\Jump (5).png");		RunLeftDino[0] = loadImage("RunLeft\\RunLeft(1).png");
		DinoJump[5] = loadImage("DinoJump\\Jump (6).png");
		DinoJump[6] = loadImage("DinoJump\\Jump (7).png");
		DinoJump[7] = loadImage("DinoJump\\Jump (8).png");
		DinoJump[8] = loadImage("DinoJump\\Jump (9).png");		RunLeftDino[0] = loadImage("RunLeft\\RunLeft(1).png");
		DinoJump[9] = loadImage("DinoJump\\Jump (10).png");
		DinoJump[10] = loadImage("DinoJump\\Jump (11).png");
		DinoJump[11] = loadImage("DinoJump\\Jump (12).png");
		
		MainMenuGround = new Image[6];
		MainMenuGround[0] = loadImage("BasicGround\\Ground01.png");
		MainMenuGround[1] = loadImage("BasicGround\\Ground02.png");
		MainMenuGround[2] = loadImage("BasicGround\\Ground03.png");
		MainMenuGround[3] = loadImage("BasicGround\\Ground03.png");
		MainMenuGround[4] = loadImage("BasicGround\\Ground03.png");		
		MainMenuGround[5] = loadImage("BasicGround\\Ground03.png");
		drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
		paintMainMenu();

		if (PLAYING) {
			StartGame();
		}
		
		MoveDino();
		//MAKE MAIN GAME HERE

	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		MenuCurrentFrame = (MenuCurrentFrame + 1) % 9;
		currentFrame = (currentFrame +1) % 5;
		JumpFrame = (JumpFrame+1)%12;

		
	}
	public void StartGame(){
		drawImage(BackgroundImage,0,0,WindowX,WindowY);
		drawImage(MainMenuGround[0],25,625,100,100);
		drawImage(MainMenuGround[1],125,625,100,100);
		drawImage(MainMenuGround[2],225,625,100,100);
		drawImage(MainMenuGround[3],325,625,100,100);
		drawImage(MainMenuGround[4],425,625,100,100);
		drawImage(MainMenuGround[5],525,625,100,100);
		
		//MAKE A RANDOMIZER for where to place tiles
		//drawImage(MainMenuGround[0],25,325,100,100);
		//drawImage(MainMenuGround[1],125,325,100,100);
		//drawImage(MainMenuGround[2],225,325,100,100);
	}
	@Override
	public void paintComponent() {

				// TODO Auto-generated method stub
		
	}

	public static void main(String args[]) {
		createGame(new JumpJump());
	}
	
	public JumpJump() {
		
	}
	public void MoveDino() {
		System.out.print(DinoY+"\n" + jumpingCheck+"\n-----\n");
		//System.out.print(tileTop[i-1]);
		
		if (IDLE) {
			drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
		}
		if (MR) {
			DinoX+=5;
			drawImage(RunRightDino[currentFrame],DinoX,DinoY,200,200);
		}
		if (ML) {
			DinoX-=5;
			drawImage(RunLeftDino[currentFrame],DinoX-64,DinoY,200,200); //WILL HAVE TO MINUS 64
												//Flipped in editor so have to compensate for empty space
		}
		if (MU) {
			DinoY-=10;
			drawImage(DinoJump[JumpFrame], DinoX, DinoY,200,200);
			}
		if (MD) {
			DinoY+=5;
			//drawImage(IdleDino[JumpFrame], DinoX, DinoY,200,200);
			if (MU && !MR || !ML) {
				drawImage(IdleDino[JumpFrame], DinoX, DinoY,200,200);
			}
			if (DinoY == 450) {
				MD = false;
				MR = false;
				ML = false;
				IDLE = true;
			}
		}
	}
	
	public void paintMainMenu() {
		BackgroundImage = loadImage("Extras/BG.png");
		MainMenuHead = loadImage("Extras/JumpJumpHead.png");
		drawImage(BackgroundImage,0,0,WindowX,WindowY);
		drawImage(MainMenuHead,25,50,400,50);


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
			drawImage(tablet,50,125,350,300);
			changeColor(DGray);
			drawText(180,175,"Rules","Arial",18);
			drawText(90,200,"1: Use the A and D key to move left or right!","Arial",16);
			drawText(90,225,"2: Use the S and W key to stop or jump!","Arial",16);
			drawText(90,250,"3: Avoid falling off the edge of your tile!","Arial",16);
			drawText(90,275,"4: Climb fast or else the water from","Arial",16);
			drawText(90,300,"below will envelop you!","Arial",16);
		}
	}
	public void keyPressed(KeyEvent e ) {
		if (e.getKeyCode() == KeyEvent.VK_D && e.getKeyCode() != KeyEvent.VK_W) {
			IDLE = false;
			ML = false;
			MR = true;
			MU = false;
			if (DinoX>RightBound) {
				DinoY+=10;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_A && e.getKeyCode() != KeyEvent.VK_W) {
			MR = false;
			IDLE = false;
			ML = true;
			MU = false;
			if (DinoX<LeftBound) {
				DinoY+=10;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			MR = false;
			IDLE = true;
			ML = false;
			MU = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			IDLE = false;
			MU = true;
			MR = false;
			ML = false;
			//if (e.getKeyCode() == KeyEvent.VK_A) {
			//	DinoX-=10;
			//}
			//if (e.getKeyCode() == KeyEvent.VK_D) {
			//	DinoX+=10;
			//}
		}	
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			MD = true;
			MU = false;
			ML = false;
			MR = false;
			IDLE = false;
		}
	}

}
