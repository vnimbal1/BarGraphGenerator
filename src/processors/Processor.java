package processors;
import java.io.IOException;

/**
 * Class Processor acts a Facade for FileProcessor and DataProcessor
 * @author Viraj
 */
public class Processor {
	private FileProcessor fileProcessor = new FileProcessor();
	private DataProcessor dataProcessor=new DataProcessor();
	private DisplayProcessor panel ;
	
	public void process() throws IOException{
		
		/*Read the input CVS file and extract the data*/
		this.fileProcessor.readInputFile();

		/*Generate random colors for labels*/
		this.dataProcessor.populateColorForLables();
		
		/*Find the range of input values*/
		this.dataProcessor.findValueRange();
		panel= new DisplayProcessor();
		
	}
}
