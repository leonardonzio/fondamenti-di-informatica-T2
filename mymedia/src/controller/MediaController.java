package controller;

import media.Media;
import media.collection.MediaCollection;
import media.filters.*;

public class MediaController {

	private MediaCollection allMedias = null;
	
	public MediaController() {
		this.allMedias = new MediaCollection();
	}

	public boolean add (Media m) {
		
		int i = allMedias.indexOf(m);
		if(i != -1) return false;
		
		allMedias.add(m);
		return true;
	}
	
	public boolean remove (Media media) {
		
		int i = allMedias.indexOf(media);
		if(i == -1) return false;
		
		allMedias.remove(i);
		return true;
	}
	
	public MediaCollection getAll() {
		
		int size = allMedias.size();
		MediaCollection res = new MediaCollection(size);
		
		for (int i = 0; i < size; i++) {
			res.add(allMedias.get(i));
		}
		
		return res;
	}
	
	public MediaCollection find (Filter f) {
		
		MediaCollection res = new MediaCollection (allMedias.size());
		
		for (int i = 0; i < allMedias.size(); i++) {
			var currMedia = allMedias.get(i);
			if(f.filter(currMedia)) {
				res.add(currMedia);				
			}
		}
		return res;		
	}
	
	
	
}
