package a;

import a.ISender;

public abstract class EmailSender implements ISender {

	protected abstract void prepareMessage();
	
	protected void formatEmail() {
		return;
	}

}