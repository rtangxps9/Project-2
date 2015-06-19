import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReferenceMonitor {

	private class ObjectManager {
		private Map<String, Integer> objects;

		public ObjectManager() {
			objects = new HashMap<String, Integer>();
		}

		public boolean contains(String obj) {
			return objects.containsKey(obj);
		}

		public boolean createObject(String obj) {
			if (!objects.containsKey(obj)) {
				objects.put(obj, 0);
				return true;
			}
			return false;
		}

		public int readObject(String obj) {
			return objects.get(obj);
		}

		public void writeObject(String obj, int val) {
			objects.put(obj, val);
		}

		public void destroyObject(String obj) {
			objects.remove(obj);
		}

		public void printObjects() {
			System.out.println("   LObj has value: " + objects.get("lobj"));
			System.out.println("   HObj has value: " + objects.get("hobj"));
		}
	}

	private Map<String, SecurityLevel> subSecLvl;
	private Map<String, SecurityLevel> objSecLvl;
	private ObjectManager om;

	public ReferenceMonitor() {
		om = new ObjectManager();
		subSecLvl = new HashMap<String, SecurityLevel>();
		objSecLvl = new HashMap<String, SecurityLevel>();		
	}

	public int executeInstruction(InstructionObject op, Map<String, SecureSubject> subjects) throws IOException {
		return executeInstruction(op, subjects, null);
	}

	public int executeInstruction(InstructionObject op,
			Map<String, SecureSubject> subjects, FileOutputStream output) throws IOException {
		if (op.isValid()) {			
			String currSub = op.getSubject();
			String currObj = op.getObject();

			// Check if subject is secure
			if (subjects.containsKey(currSub)) {
				SecureSubject thisSub = subjects.get(currSub);
				SecurityLevel currSubLvl = subSecLvl.get(currSub);
				SecurityLevel currObjLvl = objSecLvl.get(currObj);
				
				switch(op.getOperation()) {
				case READ:
					if(om.contains(currObj) && currSubLvl.compareTo(currObjLvl) >= 0)
						thisSub.setValue(om.readObject(currObj));
					else
						thisSub.setValue(0);
					break;
				case WRITE:
					if(om.contains(currObj) && currSubLvl.compareTo(currObjLvl) <= 0)
						om.writeObject(currObj, op.getValue());
					break;
				case CREATE:
					if(!om.contains(currObj))
						createNewObject(currObj, currSubLvl);
					break;
				case DESTROY:
					if(om.contains(currObj) && currSubLvl.compareTo(currObjLvl) <= 0)
						om.destroyObject(currObj);
					break;
				case RUN:
					thisSub.appendBit();
					if(thisSub.getByteBuffer().length() == 8) {
						if(thisSub.getByteBuffer().equals("00000000")) {
							thisSub.setByteBuffer("");
							return 0;
						}
						int nextChar = Integer.parseInt(thisSub.getByteBuffer(),2);
						output.write(nextChar);
						thisSub.setByteBuffer("");
					}
					thisSub.setValue(0);
					break;
				default:
					System.out.println("You shouldn't be here.");
					break;
				}
			}

		}
		return 0;
	}

	public void setNewSub(String sub, SecurityLevel lvl) {
		subSecLvl.put(sub, lvl);		
	}

	public void createNewObject(String obj, SecurityLevel lvl) {
		obj = obj.toLowerCase();
		om.createObject(obj);
		objSecLvl.put(obj, lvl);
	}

	public void print() {
		om.printObjects();
	}
}
