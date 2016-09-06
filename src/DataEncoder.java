import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*maps string values in transaction to integers*/

public class DataEncoder {
	private Map<Integer, String> encodedAlphabet;
	public Map<String, Integer> decodedAlphabet;
	private int alphabetSize;

	public DataEncoder() {
		this.encodedAlphabet = new HashMap<>();
		this.decodedAlphabet = new HashMap<>();
		this.alphabetSize = 0;
	}

	public ArrayList<Integer> encodeTransaction(String[] line) {
		for (String str : line)
			if (!this.decodedAlphabet.containsKey(str)) {
				alphabetSize++;
				encodedAlphabet.put(alphabetSize, str);
				decodedAlphabet.put(str, alphabetSize);
			}
		ArrayList<Integer> transaction = new ArrayList<>();
		for (int i = 0; i < line.length; i++)
			transaction.add( decodedAlphabet.get(line[i]));
		Collections.sort(transaction);
		return transaction;
	}
	
	public Map<Integer, String> getEncodedAlphabet() {
		return encodedAlphabet;
	}

}
