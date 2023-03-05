public class CorrectName extends AbstractUser{
	
	public CorrectName(String userName) {
		this.userName=userName;
	}

	public String NameFound() {
		return "Entered name "+userName+" is valid \n";
	}

	public String notFound() {
		return "searching";
	}
}
