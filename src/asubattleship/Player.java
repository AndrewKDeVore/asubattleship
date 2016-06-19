package asubattleship;

/**
 * Created by rnealis on 6/13/16.
 */
public class Player {
	
	// Instance variables
	private int hits;
	private int misses;
	private String name;
	
	
	/**
	 * Set the name.
	 */
	public void setName(String newname){
		name = newname;
	}
	
	/**
	 * Return the name.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Increment the number of hits.
	 */
	public int incrementHits(){
		hits++;
		return hits;
	}
	
	/**
	 * Return the number of hits.
	 */
	public int getHits(){
		return hits;
	}
	
	/**
	 * Increment the number of misses.
	 */
	public int incrementMisses(){
		misses++;
		return misses;
	}
	
	/**
	 * Return the number of misses.
	 */
	public int getMisses(){
		return misses;
	}
}
