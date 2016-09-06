import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeSet;

public class MomentAlgorithm {
	private final String REGEX;
	private Map<Integer, MomentNode> closedNodes;
	private ArrayDeque<String[]> dataToUpdate;
	private int WINDOW_SIZE = 100000;

	public MomentAlgorithm() {
		REGEX = " ";
		closedNodes = new HashMap<>();
	}

	private boolean leftcheck(MomentNode node) {

		for (MomentNode n : closedNodes.values())
			if (n.items.containsAll(node.items) && (node.compareTo(n) > 0) && n.frequency == node.frequency)
				return true;

		return false;
	}

	public int closedSize() {
		return closedNodes.size();
	}

	public void explore(MomentNode currentNode, FPHeaders headers, int minsup) {

		// List<Integer> set1 = new ArrayList<>();
		// set1.add(15);
		// set1.add(17);
		// set1.add(23);
		// if (currentNode.items.equals(set1))
		// System.out.println(currentNode.toString() + currentNode.frequency);
		if (currentNode.frequency < minsup)
			currentNode.label = MomentNodeLabel.INFREQUENT;
		else if (leftcheck(currentNode)) {
			currentNode.label = MomentNodeLabel.UNPROMISING;
		} else {

			// siblings that are lexicographically larger
			int index = currentNode.parent.children.indexOf(currentNode);
			int size = currentNode.parent.children.size();

			List<MomentNode> rightSiblings = new ArrayList<>(currentNode.parent.children.subList(index, size));
			rightSiblings.remove(0);// remove node itself
			rightSiblings.forEach((y) -> {
				if (y.frequency >= minsup) {
					// Problem - creating not valid child
					Set<Integer> set = new TreeSet<>();
					set.addAll(currentNode.items);
					set.addAll(y.items);
					List<Integer> items = new ArrayList<>(set);

					int freq = headers.calculateFrequency(items);

					if (freq > 0) {
						MomentNode child = new MomentNode(items, currentNode);
						child.frequency = freq;
						child.tidSum = headers.calculateTidSum(items);
					}
				}
			});

			currentNode.children.forEach(x -> {
				explore(x, headers, minsup);
			});
			boolean hasChildWithHigherFrequency = false;
			for (MomentNode child : currentNode.children) {
				if (child.frequency >= currentNode.frequency) {
					hasChildWithHigherFrequency = true;
					break;
				}
			}
			if (hasChildWithHigherFrequency)
				currentNode.label = MomentNodeLabel.INTERMEDIATE;
			else {
				currentNode.label = MomentNodeLabel.CLOSED;
				closedNodes.put(calculateHash(currentNode), currentNode);
			}
		}
	}

	private Integer calculateHash(MomentNode node) {

		String key = Integer.toString(node.frequency) + Integer.toString(node.tidSum);

		return key.hashCode();
	}

	public void printClosed() {
		System.out.println(this.closedNodes.toString());
	}

	public void printNode(MomentNode node) {
		System.out.println(node.toString());
		for (MomentNode c : node.children)
			printNode(c);
	}

}
