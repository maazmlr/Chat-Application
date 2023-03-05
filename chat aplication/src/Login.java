import javax.swing.JOptionPane;

public class Login {
	
	
    private String nickname;


    public String getNickname() {
        return this.nickname;
    }
	
	public void LoginGui() {
		
		this.nickname = JOptionPane.showInputDialog("Enter your name: ");
		JOptionPane.showMessageDialog(null,"Welcome to the server "+this.nickname);
		
	}
	
}