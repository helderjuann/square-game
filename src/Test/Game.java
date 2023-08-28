package Test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,MouseListener{
	
	private static final long serialVersionUID = 1L;
	int right = 0,left = 1,top = 2,down = 3;
	int dir = right;
	
	int x = 0,y = 0;
	
	int mx = -100,my = -100;
	
	boolean isPressed = false;
	
	
	public Game() {
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(1280,720));
	}
	
	private void addMouseListener(Game game) {

		
	}

	public void update() {

		if(dir == right) {
			x+=5;
		}else if(dir == left) {
			x-=5;
		}else if(dir == top) {
			y-=5;
		}else if(dir == down) {
			y+=5;
		}

		if(x+50 >= 1280 && y == 0) {
			dir = down;
		}else if(x+50 >= 1280 && y+50 >= 720) {
			dir = left;
		}else if(x <= 0 && y+50 >= 720) {
			dir = top;
		}else if(x<=0 && y<=0) {
			dir = right;
		}
		if(isPressed) {
			isPressed = false;
			detectarClique();
		}
	}
	
	public void detectarClique() {
		if(mx >= x && mx < x+50) {
		if(my >= y && my < y+50) {
			System.out.println("Clicado!");
		}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1280,720);
		

		if(dir == right) {
			g.setColor(Color.GRAY); 
		}else if(dir == left) {
			g.setColor(Color.WHITE);
		}else if(dir == top) {
			g.setColor(Color.DARK_GRAY);
		}else if(dir == down) {
			g.setColor(Color.LIGHT_GRAY);
		}
		
		g.fillRect(x, y, 50, 50);

		g.dispose();
		bs.show();
		
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Jogo do Quadrado");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		new Thread(game).start();
	}

	@Override
	public void run() {
		double fps = 60.0;
		while(true) {
			update();
			render();
			try {
				Thread.sleep((int)(1000/fps));
			} catch (InterruptedException e) {}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		isPressed = true;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
	}


	
}

