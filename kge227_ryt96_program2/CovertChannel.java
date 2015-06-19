import java.io.*;

public class CovertChannel {
	static private boolean verbose = false;
	static String secretMessage;

	public static void main (String[] args) {
		if (args.length < 1) {
			System.err.println("No filename specified.");
		}
		else {
			// Initialize variables.
			FileOutputStream outputFile = null;
			BufferedWriter logFile = null;
			String fileName = null;

			// Determine whether verbose or not.
			if (args[0].equals("v"))
				if (args.length >= 2) {		// If more than two arguments are passed in,
					verbose = true;			// we ignore args[3] and above.
					fileName = args[1];
				}
				else
					System.err.println("No filename specified.");
			else
				fileName = args[0];

			// Initialize the system/
			SecureSystem sys = new SecureSystem();

			SecurityLevel low = SecurityLevel.LOW;
			SecurityLevel high = SecurityLevel.HIGH;

			sys.createSub("Lyle", low);
			sys.createSub("Hal", high);

			FileInputStream inputFile;
			try {
				outputFile = new FileOutputStream(fileName + ".out");

				if (verbose)
					logFile = new BufferedWriter(new FileWriter("log"));

				long startTime = System.currentTimeMillis();

				inputFile = new FileInputStream(new File(fileName));
				int mybyte;
				
				InstructionObject createHal   =  new InstructionObject("CREATE HAL OBJ");
				InstructionObject createLyle  =  new InstructionObject("CREATE LYLE OBJ");
				InstructionObject writeLyle   =  new InstructionObject("WRITE LYLE OBJ 1");
				InstructionObject readLyle    =  new InstructionObject("READ LYLE OBJ");
				InstructionObject destroyLyle =  new InstructionObject("DESTROY LYLE OBJ");
				InstructionObject runLyle     =  new InstructionObject("RUN LYLE");
				
				while((mybyte = inputFile.read()) != -1) {
					for (int bit = 0; bit <= 7; bit++) {
						if (((mybyte >> (7 - bit)) & 1) == 0) {
							if (verbose) 
								logFile.write("CREATE HAL OBJ");
							sys.execute(createHal, outputFile);
						}
						
						// Lyles runs 
						if (verbose)
							logFile.write("CREATE LYLE OBJ\n"
									+ "WRITE LYLE OBJ 1\n"
									+ "READ LYLE OBJ\n"
									+ "DESTROY LYLE OBJ\n"
									+ "RUN LYLE\n");
						
						sys.execute(createLyle, outputFile);
						sys.execute(writeLyle, outputFile);
						sys.execute(readLyle, outputFile);
						sys.execute(destroyLyle, outputFile);
						sys.execute(runLyle, outputFile);
					}
					
				}
				
				long endTime = System.currentTimeMillis();
				
				long filesize = inputFile.getChannel().size();
				long bandwidth = filesize * 8 / (endTime - startTime);
				
				System.out.println("Document: " + fileName);
				System.out.println("Size: " + filesize);
				System.out.println("Bandwidth: " + bandwidth);
				
				outputFile.close();
				
				if (logFile != null)
					logFile.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e.toString());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.toString());
			}

		}
	}
}
