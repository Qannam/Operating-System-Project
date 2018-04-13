
public class Queue<T> {
	private Node<PCB> head, tail;
	private int size;
	
	
	public Queue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public boolean full() {
		return false;
	}
	
	public int length (){
		return size;
	}

	public void enqueue(PCB e) {
		if(tail == null){
			head = tail = new Node<PCB>(e);
		}
		else {
			tail.next = new Node<PCB>(e);
			tail = tail.next;
		}
		size++;
	}

	public PCB serve() {
		PCB x = head.data;
		head = head.next;
		size--;
		if(size == 0)
			tail = null;
		return x;
	}
	
}
