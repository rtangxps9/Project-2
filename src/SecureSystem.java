import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SecureSystem {

	static private Map<String, SecureSubject> subjects;
	private ReferenceMonitor rm;

	// Constructor
	public SecureSystem() {
		subjects = new HashMap<String, SecureSubject>();
		rm = new ReferenceMonitor();
	}

	public ReferenceMonitor getReferenceMonitor() {
		return rm;
	}

	public void print() {
		System.out.println("The current state is: ");

		rm.print();

		System.out.println("   Lyle has recently read: " + subjects.get("lyle"));
		System.out.println("   Hal has recently read: " + subjects.get("hal"));

		System.out.println();
	}

	public void createSub(String sub, SecurityLevel lvl) {
		sub = sub.toLowerCase();
		subjects.put(sub, new SecureSubject(sub));
		rm.setNewSub(sub, lvl);
	}

	public void execute(InstructionObject op, FileOutputStream output) {
		try {
			rm.executeInstruction(op, subjects, output);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		}
	}
}
