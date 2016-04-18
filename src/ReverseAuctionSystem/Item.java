package ReverseAuctionSystem;


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
		if(o == null)
                    return false;
                else if(o.getClass() != this.getClass())
                    return false;
		else
                {
                    Item item = (Item)o;
                    return item.id == this.id
                           && item.description.equals(this.description);
                }
	}
	
	// need to override hashcode as well for equality test
	@Override
    public int hashCode() {
		return this.id ^ this.description.hashCode();
	}
}
