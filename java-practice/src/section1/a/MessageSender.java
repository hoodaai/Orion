package a;


public class MessageSender extends EmailSender {
	public static final String SENDER_NAME = "Varun";
	public void prepareMessage() {
		System.out.println("Preparing message...");
	}
	public void send() {
		prepareMessage();
		System.out.println("Sending message..." + SENDER_NAME);
	}
	public static void main(String a[]) {
		MessageSender ms = new MessageSender();
		ms.send();
	}
}