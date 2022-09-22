import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame {

	Panel panel = new Panel();
	
	Frame(){
		panel = new Panel();
		this.add(panel);
		this.setTitle("Ball Demo");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		ImageIcon image = new ImageIcon("991070.jpg");	
		this.setIconImage(image.getImage());
	}
	
}