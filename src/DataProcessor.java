import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataProcessor {
	String REGEX = " ";
	int WINDOW_SIZE = 100000;
	DataEncoder dataEncoder;
	Deque<String[]> dataToUpdate;
	
	public DataProcessor() {
		dataEncoder = new DataEncoder();
		dataToUpdate = new ArrayDeque<>();
	}
	
	
	
	public List<ArrayList<Integer>> prepareData(String fileName) {
		 List < ArrayList<Integer>> data = new ArrayList<>();
		Integer tid = 0;
		try (Scanner scanner = new Scanner(Paths.get(fileName))) {
			while (scanner.hasNextLine()) {
			
				tid++;
				String[] line = scanner.nextLine().split(REGEX);
				

				ArrayList<Integer> transaction = dataEncoder.encodeTransaction(line);
				if (tid <= WINDOW_SIZE) {
					
					
							
							data.add(transaction);
						
					
				} else {
					System.out.println(line.toString());
					dataToUpdate.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
