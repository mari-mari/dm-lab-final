import java.util.ArrayList;import java.util.List;

public class Test {
	public static void main(String[] args) {
		FPNode root = new FPNode(null, null);
		ArrayList<Integer> t1 = new ArrayList<>();
		t1.add(1);
		t1.add(2);
		root.add(t1, 1);
		t1.add(1);
		t1.add(2);
		t1.add(3);
		root.add(t1, 2);
		t1.add(1);
		t1.add(2);
		t1.add(3);
		root.add(t1, 3);
		t1.add(3);
		t1.add(4);
		root.add(t1, 4);
		
		FPNode.printTree(root);
		ArrayList<Integer> t3 = new ArrayList<>();
		t3.add(1);
		t3.add(2);
		
		System.out.println(root.calculateFrequency(t3));
		CETNode n = new CETNode(t3, root.getTree().headers);
		System.out.println(n.freq(t3));
		
		
		
		
		
	}
}
