import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this
		int a1c=a1.weight;
		int a2c=a2.weight;
		if(a1c>a2c) return -1;
		else if(a1c<a2c) return 1;
		
		
		return 0;
	}
}
