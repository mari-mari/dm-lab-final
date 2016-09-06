import java.util.ArrayList;
import java.util.List;

public class FPHeaders {
	List<FPNode> headers;
	public FPHeaders() {
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
	
	public int calculateTidSum(List<Integer> items){
		int index = this.headers.indexOf(new FPNode(items.get(0),null));
		if (index == -1)
			return 0;
		
		FPNode header = this.headers.get(index);
		int tidSum = 0;
		while(header!=null){
			tidSum+=header.calculateTidSum(items);
			header = header.next;
		}
		
		return tidSum;
	}
}
