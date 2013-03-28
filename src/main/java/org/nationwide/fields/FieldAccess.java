package org.nationwide.fields;

import java.util.HashSet;
import java.util.Set;

public class FieldAccess {
	private Set<String> writableFields;
	boolean accessToAll;
	public Set<String> getWritableFields() {
		return writableFields;
	}
	public void addWritableFields(String field) {
		if(this.writableFields == null){
			this.writableFields = new HashSet<String>();
		}
		writableFields.add(field);
	}
	public boolean hasAccessToAll() {
		return accessToAll;
	}
	public void setAccessToAll(boolean accessToAll) {
		this.accessToAll = accessToAll;
	}
	
}
