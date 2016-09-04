import java.util.*;

public class FPNode {

	Integer item;
	int frequency, tidSum;
	FPNode parent, next;
	List<FPNode> children;
	List<Integer> tidlist;
	static FPTree tree = new FPTree();

	public FPNode(Integer item, FPNode parent) {
		this.item = item;
		this.parent = parent;
		this.children = new ArrayList<>();
		this.tidlist = new ArrayList<>();
		this.frequency = 1;
		
	}

	public void add(List<Integer> items, int tid) {
		if (items.size() == 0)
			return;
		if ((items.get(0)).equals(this.item)) {
			this.frequency++;
			this.tidSum += tid;
			this.tidlist.add(tid);
			items.remove(0);
		}
		if (items.size() == 0)
			return;

		boolean isNode = false;

		for (FPNode child : this.children) {
			if (child.item.equals(items.get(0))) {
				isNode = true;
				child.add(items, tid);
			}
		}
		if (!isNode) {
			
			FPNode node = new FPNode(items.get(0), this);
			this.children.add(node);
			tree.add(node);
			items.remove(0);
			node.add(items, tid);
			node.tidSum+=tid;
			node.tidlist.add(tid);
		}
	}

	public int calculateFrequency(List<Integer> items) {
		if (items.size() == 0)
			return -1;
		List<Integer> itemsCopy = new ArrayList<>(items);
		
		if (itemsCopy.get(0).equals(this.item)) {
			if (itemsCopy.size() == 1)
				return this.frequency;
			itemsCopy.remove(0);
		}

		int frequency = 0;
		
		for(FPNode child:children){
			int freq = child.calculateFrequency(itemsCopy);
			if(freq!=-1)
				frequency+= freq;
		}

		return frequency;
	}

	@Override
	public String toString() {
//		if(children.isEmpty())
//			return this.item + ": f="+this.frequency+" t="+this.tidSum;
//			
		return this.item + ": f="+this.frequency+" t="+this.tidSum+" " + tidlist.toString();
	}

	public static void printTree(FPNode node) {
		if (node != null)
			System.out.println(node.toString());
		if (!node.children.isEmpty())
			for (FPNode c : node.children)
				printTree(c);

	}
	
	@Override
	public boolean equals(Object obj) {
		FPNode other = (FPNode) obj;
		return this.item.equals(other.item);
	}
	
	public static FPTree getTree() {
		return tree;
	}

	public void remove(List<Integer> items) {
		// TODO
	}

}
