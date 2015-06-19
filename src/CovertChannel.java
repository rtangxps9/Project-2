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
				outputFile = new FileOutputStream(fileName + ".OUT");

				if (verbose)
					logFile = new BufferedWriter(new FileWriter("log"));

				long startTime = System.currentTimeMillis();

				inputFile = new FileInputStream(new File(fileName));

				InstructionObject createHal   =  new InstructionObject("CREATE HAL OBJ");
				InstructionObject createLyle  =  new InstructionObject("CREATE LYLE OBJ");
				InstructionObject writeLyle   =  new InstructionObject("WRITE LYLE OBJ 1");
				InstructionObject readLyle    =  new InstructionObject("READ LYLE OBJ");
				InstructionObject destroyLyle =  new InstructionObject("DESTROY LYLE OBJ");
				InstructionObject runLyle     =  new InstructionObject("RUN LYLE");

				sys.execute(createLyle, outputFile);
				sys.execute(writeLyle, outputFile);
				sys.execute(readLyle, outputFile);
				sys.execute(destroyLyle, outputFile);
				sys.execute(runLyle, outputFile);

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
