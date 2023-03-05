import java.io.BufferedReader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import javax.swing.*;
import java.io.*;

class Server  extends JFrame{
	ServerSocket server;
	Socket socket;
	
	BufferedReader br;
	PrintWriter out;
	
	//Declare Components
		private JLabel heading;
		private JTextArea msgArea = new JTextArea();
		private JTextField msgIn = new JTextField();
		private Font font = new Font("Roboto", Font.PLAIN, 20);	
	 	String name;
	public Server() {
		
		    gettingStarted();
			createGUI();
			handleEvents();
			startWriting();
			startReading();
			
		} 
	
public void gettingStarted()
{
	Login login = new Login();
	login.LoginGui();
	name = login.getNickname();
	heading = new JLabel(name);
	try {
		server = new ServerSocket(9899);
		System.out.println("Server is ready to accecptconnection");
		System.out.println("waiting...");
		socket = server.accept();
		
		br = new BufferedReader(new InputStreamReader(socket.
				getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
	}
		catch (Exception e) {
			e.printStackTrace();
		}		
}

	private void createGUI() {
		
		this.setTitle("Server Messenger");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// coding for components
		heading.setFont(font);
		msgArea.setFont(font);
		msgIn.setFont(font);
		
		//heading Component
		heading.setIcon(new ImageIcon("D:\\Eclipse Work\\Chat Application\\src\\icon.png"));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //Top, Right, Bottom, Left
		
		//Message Input Components
		msgArea.setEditable(false);
		msgIn.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Frame Layout
		this.setLayout(new BorderLayout());
		
		//adding the component of frame
		this.add(heading, BorderLayout.NORTH);
		JScrollPane  jsp = new JScrollPane(msgArea);
		this.add(jsp, BorderLayout.CENTER);
		this.add(msgIn, BorderLayout.SOUTH);
		NullFactory nu = new NullFactory();
		AbstractUser ab = NullFactory.UserInfo(name);
		IncorrectName in = new IncorrectName();
		for(int i=0; i<nu.userStoredName.length; i++) {
			if(nu.userStoredName[i].equalsIgnoreCase(name)) {
				this.setVisible(true);
				System.out.println(ab.NameFound());
				break;
			}
			else if(nu.userStoredName[i] != (name)){
				System.out.println(ab.notFound());
			}
			else {
				System.out.println(in.notFound());
			}
				
			
		}	
	}

	
	public void handleEvents() {
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
					out.println(contentToSend);
					out.flush();
					msgIn.setText("");
					msgIn.requestFocus();
				}
			}
			
		});
	}
	
	
	public void startWriting() {
		Runnable r1 = ()->{
			System.out.println("Writer started.....");
			try {
			while(!socket.isClosed()) {
				
					BufferedReader br1 = new BufferedReader(new 
							InputStreamReader(System.in));
					String content = br1.readLine();
					out.println(content);
					out.flush();
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
	
	public void startReading() {
		Runnable r2 = ()->{
			System.out.println("Reader started.....");
			try {
			while(true) {
				

					String msg = br.readLine();
					if(msg.equals("exit")) {
						System.out.println("Clients terminated the chat");
						JOptionPane.showMessageDialog(this, "Server Terminated "
								+ "the chat");
						msgIn.setEnabled(false);
						socket.close();
						break;
					}
					//System.out.println("Client : " + msg);
					msgArea.append(" Message:  " + msg + "\n");
				
				}
			}catch(Exception e) {
				System.out.println("Connection is closed");
			}
		};
		
		new Thread(r2).start();
	}
	
	public static void main(String[] args) {
		new Server();
		
	}

}
