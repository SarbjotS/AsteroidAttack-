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
	
	int MenuCurrentFrame;
	Image MainMenuHead;
	Image[] IdleDino;
	int DinoX = 50; 
	int DinoY = 447;
	Image[] MainMenuGround;

	Color blue = Color.BLUE;
	Image BackgroundImage;

	int LeftBound; 
	int RightBound;
	
	public void init() {
		setWindowSize(WindowX,WindowY);
		BackgroundImage = loadImage("BG.png");
		MainMenuHead = loadImage("JumpJumpHead.png");
		IdleDino = new Image[10];
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

		MainMenuGround = new Image[4];
		MainMenuGround[0] = loadImage("BasicGround\\Ground01.png");
		MainMenuGround[1] = loadImage("BasicGround\\Ground02.png");
		MainMenuGround[2] = loadImage("BasicGround\\Ground03.png");

	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		MenuCurrentFrame = (MenuCurrentFrame + 1) % 9;
		currentFrame = (currentFrame + 2) % 3;

		
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
		drawImage(IdleDino[MenuCurrentFrame],DinoX,DinoY,200,200);
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
			DinoX++;
			if (DinoX>RightBound) {
				DinoY+=5;
			}
			
			paintMainMenu();

		}
	}
	

}
