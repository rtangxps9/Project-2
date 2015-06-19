
public class InstructionObject {

	public enum type {
		READ, WRITE, CREATE, DESTROY, RUN, BAD;
	}

	private type op;
	private String sub;
	private String obj;
	private int val;

	public InstructionObject(String line) {
		line = line.toLowerCase();
		try {
			String[] tokens = line.split("\\s");
			String op = tokens[0];
			if ("read".equals(op)) {
				sub = tokens[1];
				obj = tokens[2];
				this.op = type.READ;
			}
			else if ("write".equals(op)) {
				sub = tokens[1];
				obj = tokens[2];
				val = Integer.parseInt(tokens[3]);
				this.op = type.WRITE;
			}
			else if ("create".equals(op)) {
				sub = tokens[1];
				obj = tokens[2];
				this.op = type.CREATE;
			}
			else if ("destroy".equals(op)) {
				sub = tokens[1];
				obj = tokens[2];
				this.op = type.DESTROY;
			}
			else if ("run".equals(op)) {
				sub = tokens[1];
				this.op = type.RUN;
			}
			else {
				System.out.println("Bad Instruction");
				this.op = type.BAD;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Bad Instruction");
			this.op = type.BAD;
		}
	}

	public type getOperation() {
		return op;
	}

	public String getSubject() {
		return sub;
	}

	public String getObject() {
		return obj;
	}

	public int getValue() {
		return val;
	}

	public boolean isValid() {
		return (this.op != type.BAD);
	}
}
