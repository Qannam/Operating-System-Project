public class Node<T> {
	public T data;
	public Node<T> next;
	int priority;

	public Node() {
		data = null;
		next = null;
		priority = 0;
	}
	
	public Node(T val) {
		data = val;
		next = null;
	}
	
	public Node(T val, int p) {
		data = val;
		next = null;
		priority = p;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}