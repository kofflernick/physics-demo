import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {

	static final int WIDTH = 300;
	static final int HEIGHT = 300;
	static final Dimension SCREEN_SIZE = new Dimension(WIDTH,HEIGHT);
	static final int BALL_DIAMETER = 20;
	Thread thread;
	Image image;
	Graphics graphics;
	Random random;
	Ball ball;
	Ball2 ball2;
	Ball3 ball3;
	
	Panel(){
		newBall();
		newBall2();
		newBall3();
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);		
		thread = new Thread(this);
		thread.start();
	}
	public void newBall() {
		random = new Random();
		ball = new Ball(random.nextInt(WIDTH-BALL_DIAMETER),random.nextInt(HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newBall2() {
		random = new Random();
		ball2 = new Ball2(random.nextInt(WIDTH-BALL_DIAMETER),random.nextInt(HEIGHT-BALL_DIAMETER),BALL_DIAMETER*2,BALL_DIAMETER*2);
	}
	public void newBall3() {
		random = new Random();
		ball3 = new Ball3(random.nextInt(WIDTH-BALL_DIAMETER),random.nextInt(HEIGHT-BALL_DIAMETER),BALL_DIAMETER*3,BALL_DIAMETER*3);
	}
	
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		ball.draw(g);
		ball2.draw(g);
		ball3.draw(g);
	}
	public void move() {
		ball.move();
		ball2.move();
		ball3.move();
	}
	public void checkCollision() {
		//ball3
		//
		//bounces ball3 off top and bottom window edges 
		if(ball3.y <= 0 ) {
			ball3.setYDirection(-ball3.yVelocity);
		}
		if(ball3.y >= HEIGHT-BALL_DIAMETER) {
			ball3.setYDirection(-ball3.yVelocity);
		}
				
		//bounces ball2 off side walls  
		if(ball3.x <= 0) {
			ball3.xVelocity = Math.abs(ball3.xVelocity);
			ball3.setXDirection(ball3.xVelocity);
			ball3.setYDirection(ball3.yVelocity);
		}
		if(ball3.x >= WIDTH-BALL_DIAMETER) {
			ball3.xVelocity = Math.abs(ball3.xVelocity);
			ball3.setXDirection(-ball3.xVelocity);
			ball3.setYDirection(ball3.yVelocity);
		}
		
		//ball2
		//
		//bounces ball2 off top and bottom window edges 
		if(ball2.y <= 0 ) {
			ball2.setYDirection(-ball2.yVelocity);
		}
		if(ball2.y >= HEIGHT-BALL_DIAMETER) {
			ball2.setYDirection(-ball2.yVelocity);
		}
		
		//bounces ball2 off side walls  
		if(ball2.x <= 0) {
			ball2.xVelocity = Math.abs(ball2.xVelocity);
			ball2.setXDirection(ball2.xVelocity);
			ball2.setYDirection(ball2.yVelocity);
		}
		if(ball2.x >= WIDTH-BALL_DIAMETER) {
			ball2.xVelocity = Math.abs(ball2.xVelocity);
			ball2.setXDirection(-ball2.xVelocity);
			ball2.setYDirection(ball2.yVelocity);
		}
		
		//ball
		//
		//bounces ball off top and bottom window edges 
		if(ball.y <= 0 ) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		//bounces ball off side walls 
		if(ball.x <= 0) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.x >= WIDTH-BALL_DIAMETER) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

		//collisions between balls 2 and 1 
		if(ball.intersects(ball2)) {
			ball.setYDirection(-ball.yVelocity-ball2.yVelocity+1);
			ball.setXDirection(-ball.xVelocity-ball2.xVelocity+1);
			ball2.setYDirection(-ball2.yVelocity-ball.yVelocity+1);
			ball2.setXDirection(-ball2.xVelocity-ball.xVelocity+1);
		}
		
		//collisions between balls 3 and 2 
		if(ball3.intersects(ball2)) {
			ball3.setYDirection(-ball3.yVelocity-ball2.yVelocity+1);
			ball3.setXDirection(-ball3.xVelocity-ball2.xVelocity+1);
			ball2.setYDirection(-ball2.yVelocity-ball3.yVelocity+1);
			ball2.setXDirection(-ball2.xVelocity-ball3.xVelocity+1);
		}
		
		//collisions between balls 3 and 1 
		if(ball3.intersects(ball)) {
			ball3.setYDirection(-ball3.yVelocity-ball.yVelocity+1);
			ball3.setXDirection(-ball3.xVelocity-ball.xVelocity+1);
			ball.setYDirection(-ball.yVelocity-ball3.yVelocity+1);
			ball.setXDirection(-ball.xVelocity-ball3.xVelocity+1);
		}
		
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

}
