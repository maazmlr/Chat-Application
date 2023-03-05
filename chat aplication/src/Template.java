import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public interface Template {
	JTextArea msgArea = new JTextArea();
	JTextField msgIn = new JTextField();
	Font font = new Font("Roboto", Font.PLAIN, 20);	

public default void br()
{
	BufferedReader br;
}

	

	
	public default   void  Template()
	{
		gettingStarted();
		createGUI();
		handleEvents();
		startWriting();
		startWriting();
	}
	public  abstract void	gettingStarted();
	
	
	public abstract void	createGUI();
		
	public default   void	handleEvents() {
		msgIn.addKeyListener(new KeyListener() { //KeyListener() is a interface

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Key released : " + e.getKeyCode());
				if(e.getKeyCode()==10) {
					//System.out.println("You have pressed enter btn");
					String contentToSend = msgIn.getText();
					msgArea.append("Me :" + contentToSend + "\n");
					br().out.println(contentToSend);
					br().out.flush();
					msgIn.setText("");
					msgIn.requestFocus();
				}
			}
			
		});
	}
	
	

	public default  void	startWriting() {
		Runnable r1 = ()->{
			System.out.println("Writer started.....");
			try {
			while(!socket.isClosed()) {
				
					BufferedReader br1 = new BufferedReader(new 
							InputStreamReader(System.in));
					String content = br1.readLine();
					br().out.println(content);
					br().out.flush();
					if(content.equals("exit")) {
						socket.close();
						break;
					}

			}
			}catch(Exception e) {
				System.out.println("Connection is closed");
			}
		};

		new Thread(r1).start();
	}
		
	
		
	public default void	startReading() {
		Runnable r2 = ()->{
			System.out.println("Reader started.....");
			try {
			while(true) {
				
					String msg = br.readLine();
					if(msg.equals("exit")) {
						System.out.println("Server terminated the chat");
						JOptionPane.showMessageDialog(new JFrame(), "Server Terminated "
								+ "the chat");
						msgIn.setEnabled(false);
						socket.close();
						break;
					}
					
					
					//System.out.println("Server : " + msg);
					msgArea.append("MESSAGE : " + msg + "\n");
				
			}
			}catch(Exception e) {
				System.out.println("Server not found");
			}
		};
		
		new Thread(r2).start();
	}
	}
	


