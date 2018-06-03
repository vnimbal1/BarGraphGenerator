
import java.io.IOException;

import processors.Processor;

public class Driver {

	public static void main(String args[]) throws IOException {
		
		/*Invoke process method on processor object*/
		Processor processor = new Processor();
		processor.process();	
	}
}