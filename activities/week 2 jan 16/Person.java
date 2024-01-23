
public class Person {
	
	private String firstName;
	private String lastName;
	private String email;
	
	public Person(String firstName, String lastName, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
	} //end Person (constructor)
	
	public boolean equals(Person other) {
		
		if (this.firstName.equals(other.firstName)&& this.lastName.equals(other.lastName))
			
			return true;
		
		else
			
			return false;
		
	} //end equals

} //end Person (class)