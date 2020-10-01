import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JumpJump extends GameEngine implements KeyListener {
	int WindowX = 450;
	int WindowY = 700; 
	Image[] frames;
	int currentFrame;
	
	//MENU
	int MenuCurrentFrame;
	Image MainMenuHead;
	Image[] MainMenuGround;

	//DINO MOVEMENT
	Image[] RunLeftDino;
	Image[] RunRightDino;
	Image[] IdleDino;
	int DinoX = 50; 
	int DinoY = 447;

	Image BackgroundImage;

	int LeftBound; 
	int RightBound;
	boolean PLAYING = true;
	boolean MR = false; //MENU RIGHT
	boolean ML = false; //MENU LEFT
	boolean IDLE = true;
	
	public void init() {
		setWindowSize(WindowX,WindowY);
		BackgroundImage = loadImage("BG.png");
		MainMenuHead = loadImage("JumpJumpHead.png");
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

		MainMenuGround = new Image[4];
		MainMenuGround[0] = loadImage("BasicGround\\Ground01.png");
		MainMenuGround[1] = loadImage("BasicGround\\Ground02.png");
		MainMenuGround[2] = loadImage("BasicGround\\Ground03.png");

		paintMainMenu();


	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		MenuCurrentFrame = (MenuCurrentFrame + 3) % 9;
		currentFrame = (currentFrame + 1) % 5;

		
	}

	public void MoveRight() {
		while (MR) {
			DinoX++;
			if (DinoX>RightBound) {
				DinoY+=5;
			}
		}
	}
	
	@Override
	public void paintComponent() {
		//changeBackgroundColor(blue);
		//clearBackground(WindowX,WindowY);
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]) {
		createGame(new JumpJump());
	}
	
	public JumpJump() {
		
	}
	
	public void paintMainMenu() {
		drawImage(BackgroundImage,0,0,WindowX,WindowY);
		drawImage(MainMenuHead,25,50,400,50);

		if (IDLE) {
		drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
		}
		if (MR) {
			DinoX+=8;

			drawImage(RunRightDino[currentFrame],DinoX,DinoY,200,200);
		}
		if (ML) {
			DinoX-=8;
			drawImage(RunLeftDino[currentFrame],DinoX-64,DinoY,200,200); //WILL HAVE TO MINUS 64
												//Flipped in editor so have to compensate for empty space
		}
		drawImage(MainMenuGround[0],25,625,100,100);
		drawImage(MainMenuGround[1],125,625,100,100);
		drawImage(MainMenuGround[2],225,625,100,100);
		
		LeftBound = -40;
		RightBound = 280;
		

		//LEFT AND RIGHT BOUND NEED TO BE +- 65 of INTIAL DRAWN POINTS
		// TODO Auto-generated method stub
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			IDLE = false;
			ML = false;
			MR = true;
			if (DinoX>RightBound) {
				DinoY+=10;
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			MR = false;
			IDLE = false;
			ML = true;
			if (DinoX<LeftBound) {
				DinoY+=10;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			MR = false;
			IDLE = true;
			ML = false;
		}
	}
	

}
