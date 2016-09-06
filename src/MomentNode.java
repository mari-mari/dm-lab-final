import java.util.*;

public class MomentNode {
	
	int frequency;
	int tidSum;
	MomentNode parent;
	MomentNodeLabel label;
	List<MomentNode> children;
	List<Integer> items;
	
	
	public MomentNode(List<Integer> items) {
		this.children = new ArrayList<>();
		this.items = items;	
		
	}
	
	public MomentNode(List<Integer> items, MomentNode parent){
		this.children = new ArrayList<>();
		this.items = items;	
		this.parent = parent;
		parent.children.add(this);
	}
	
	public List<Integer> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		if(items==null)
			return null;
		return this.items.toString()+" "+this.frequency;
	}
	
	public int compareTo(MomentNode o) {

		return this.items.toString().substring(1, this.items.toString().length() - 1)
				.compareTo(o.items.toString().substring(1, o.items.toString().length() - 1));
	}
	

	

}
