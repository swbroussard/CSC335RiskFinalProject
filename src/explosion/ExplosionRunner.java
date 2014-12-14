package explosion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ExplosionRunner {
	private List<SpriteObject> splosions;
	private JFrame frame;
	private JPanel panel;
	private Timer animTimer;
	private java.util.Timer otherTimer;
	
	public ExplosionRunner() {
		splosions = new LinkedList<SpriteObject>();
		
		panel = new JPanel(){
			public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D) g;
				super.paintComponent(g);
				for (SpriteObject explosion : splosions)
					explosion.draw(g2);
			}
		};
		panel.setPreferredSize(new Dimension(800, 600));
		
		otherTimer = new java.util.Timer();
		otherTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				panel.repaint();
			}
		}, 0, 15);
		
		frame = new JFrame();
		frame.add(panel);
		
		frame.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent arg0) {
				Explosion e = new Explosion(0, 0);
				e.setPosition(arg0.getPoint().x, arg0.getPoint().y - e.getSprite().getHeight()/2);
				splosions.add(e);
				e.start();
			}
		});
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new ExplosionRunner();
	}
}