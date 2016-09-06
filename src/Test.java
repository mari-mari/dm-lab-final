import java.util.*;

public class Test {
	public static void main(String[] args) {
		FPNode root = new FPNode(null, null);
		FPHeaders headers = new FPHeaders();
		ArrayList<Integer> t1 = new ArrayList<>();
		t1.add(15);
		t1.add(17);
		t1.add(23);
//		root.add(t1, 1, headers);
//		t1.add(1);
//		t1.add(2);
//		t1.add(3);
//		root.add(t1, 2, headers);
//		t1.add(1);
//		t1.add(2);
//		t1.add(3);
//		root.add(t1, 3, headers);
//		t1.add(3);
//		t1.add(4);
//		root.add(t1, 4, headers);
//		
//		
//		t1.add(1);
//		t1.add(3);
//		t1.add(4);
//		root.add(t1, 5, headers);
//		FPNode.printTree(root);
//		t1.add(1);
//		t1.add(2);
//		t1.add(3);
//		System.out.println(headers.calculateTidSum(t1));
//		

		DataProcessor dp = new DataProcessor();
		
		List<ArrayList<Integer>> data = dp.prepareData("data.txt");
		System.out.println(data.size());
		
		int tid = 0;
		for(List<Integer> l:data){
			++tid;
			root.add(l,tid,headers);
		}
		
		MomentAlgorithm ma = new MomentAlgorithm();
		MomentNode mroot = new MomentNode(null);

		for(Integer i:dp.dataEncoder.getEncodedAlphabet().keySet()){
			List<Integer> l = new ArrayList<>();
			l.add(i);
			MomentNode ch = new MomentNode(l,mroot);
			
			ch.frequency = headers.calculateFrequency(ch.items);
			ch.tidSum = headers.calculateTidSum(ch.items);
		}
		System.out.println(headers.calculateFrequency(t1));
		long time1 = System.currentTimeMillis();
		for(MomentNode c:mroot.children){
			ma.explore(c, headers, 1000);
		}

		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);		
		System.out.println(ma.closedSize());	
		
	}
}
