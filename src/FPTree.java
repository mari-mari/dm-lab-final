import java.util.ArrayList;
import java.util.List;

public class FPTree {
	FPNode root;
	List<FPNode> headers;
	public FPTree() {
		this.root = null;
		this.headers = new ArrayList<>();
	}
	
	public FPTree(FPNode root) {
		this.root = root;
		this.headers = new ArrayList<>();
	}
	
	public void add(FPNode node){
		if(!this.headers.contains(node)){
			this.headers.add(node);
			return;
		}
		
		FPNode header = this.headers.get(this.headers.indexOf(node));
		while(header.next != null){
			header = header.next;
		}
		header.next = node;
	}
	
	public int calculateFrequency(List<Integer> items){
		int index = this.headers.indexOf(new FPNode(items.get(0),null));
		if (index == -1)
			return 0;
		
		FPNode header = this.headers.get(index);
		int frequency = 0;
		while(header!=null){
			frequency+=header.calculateFrequency(items);
			header = header.next;
		}
		
		return frequency;
		
	}
	

	
	

}
