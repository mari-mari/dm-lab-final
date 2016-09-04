import java.util.List;

public class CETNode {
	List<Integer> items;
	static List<FPNode> tree;
	
	public CETNode(List<Integer> items) {
		this.items = items;
	}
	
	public CETNode(List<Integer> items, List<FPNode> tree) {
		this.items = items;
		CETNode.tree = tree;
	}
	
	public int freq(List<Integer> items){
		
		return tree.get(0).getTree().calculateFrequency(items);
	}

}
