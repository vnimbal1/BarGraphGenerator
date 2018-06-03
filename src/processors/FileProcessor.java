package processors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Data;

/**
 * The FileRader class provides utility methods to read CSV file and extract the
 * data
 * 
 * @author Viraj
 */
public class FileProcessor {

	public void readInputFile() throws IOException {
		Data data = Data.getInstance();
		/* Find the value names */
		String[] temp = Files.lines(Paths.get("data.csv")).map(s -> s.split(",")).findFirst().get();

		for (String a : temp) {
			data.getLabels().add(a);
		}

		/* Extract the values form file */
		Stream<String> stream1 = Files.lines(Paths.get("data.csv"));
		stream1.filter(x -> isInteger(x)).filter(x -> x != null).forEach(DataProcessor::populateData);
		stream1.close();
	}

	/**
	 * This methods checks if given input string can be converted to an Integer
	 * Perform sanity check on input data
	 * 
	 * @param number
	 * @return
	 */
	public boolean isInteger(String number) {
		String[] temp = number.split(",");
		try {
			for (String s : temp) {
				if (s != null && !s.isEmpty()) {
					Integer.parseInt(s);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
