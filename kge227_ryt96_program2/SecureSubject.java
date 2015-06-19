
public class SecureSubject {
	private String name;
	private int val;
	private String byteBuffer;

	public SecureSubject(String name) {
		this(name, 0);
	}
	public SecureSubject(String sName, int val) {
		this.val = val;
		name = sName;
		byteBuffer = "";
	}
	public String getName() { return name; }

	public int getValue() { return val; }

	public void setValue(int val) { this.val = val; }

	public String getByteBuffer() { return byteBuffer; }

	public void setByteBuffer(String arg) { byteBuffer = arg; }

	public void appendBit() {
		byteBuffer = byteBuffer + (Integer.toString(val));
	}

	public boolean equals(Object other) {
		if(other instanceof SecureSubject) {
			return this.name.equals(((SecureSubject)other).getName());
		}
		return false;
	}
}
