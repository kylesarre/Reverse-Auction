
package reverseauction;


public class Item {
	private int id;
	private String description;

	public Item(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	// equality test
	@Override
	public boolean equals (Object o) {
		Item bid = (Item) o;
		
		if (bid.id == this.id
				&& bid.description.equals(this.description))
			return true;
		
		return false;
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return this.id ^ this.description.hashCode();
	}
}
