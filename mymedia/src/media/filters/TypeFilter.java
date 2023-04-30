package media.filters;

import media.*;

public class TypeFilter implements Filter{

	private Type typeToFind;
	
	public TypeFilter(Type t) {
		setType(t);
	}
	
	public void setType(Type t) {
		this.typeToFind = t;
	}

	@Override
	public boolean filter(Media media) {
		if(media instanceof HasType m) {
			return m.getType().equals(typeToFind);
		}
		return false;
	}

	
}
