

public class Stack {
	private int maxSize;
	private String[] stackArray;
	private int top;
	
	public Stack(int s) {
		maxSize = s;
		stackArray = new String[maxSize];
		top = -1;
	}
	
	public void push(String j) {
		stackArray[++top] = j;
	}
	
	public String pop() {
		return stackArray[top--];
	}
	
	public String peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}
	public boolean isFull() {
		return (top == maxSize - 1);
	}
	public int getLength() {
		return stackArray.length;
	}

	public void push(Object list) {
		
		
	}
}
