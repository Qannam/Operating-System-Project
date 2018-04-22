public class PriorityQueue<T> {

	private Node<PCB> head;
	private int size;
	private int maxMemorySize;
	private int sizeSum;
	private ArrayOfPCB ArrayOfPCB;

	public PriorityQueue(int maxSize) {
		head = null;
		maxMemorySize = maxSize;
		sizeSum = 0;
		this.ArrayOfPCB = null;
	}
	
	public PriorityQueue(int maxSize , ArrayOfPCB ArrayOfPCB) {
		head = null;
		maxMemorySize = maxSize;
		sizeSum = 0;
		this.ArrayOfPCB = ArrayOfPCB;
	}
	
	public boolean full() {
		return false;
	}

	public int length() {
		return size;
	}

	public boolean enqueue(PCB e, int memorySize) {
		Node<PCB> tmp = new Node<PCB>(e, memorySize);
		
		if((size == 0)) {
			tmp.next = head;
			head = tmp;
			sizeSum += memorySize;
			size++;
			return true;
		}
		else {
			Node<PCB> p = head;
			Node<PCB> q = null;
			while((p != null)) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
			sizeSum += memorySize;
			size++;
			return true;
		}
	}
	

	public PCB serve(){
		if(head == null)
			return null;
		int highestPriority = Integer.MAX_VALUE;
		Node<PCB> current = head;
		while(current != null){
			if(current.priority < highestPriority)
				highestPriority = current.priority;
			current = current.next;
		}
		
		Node<PCB> pre = null;
		current = head ;
		while(current.priority != highestPriority){
			pre = current;
			current = current.next;
		}
		if(pre == null){
			head = head.next;
			size--;
			sizeSum -= Integer.parseInt(current.data.getMemorySize());
			return current.data;
		}
		else{
			pre.next = pre.next.next;
			size--;
			sizeSum -= Integer.parseInt(current.data.getMemorySize());
			return current.data;
		}
			
		
	}
	
	
	
	public Node<PCB> getHead() {
		return head;
	}

	public void setHead(Node<PCB> head) {
		this.head = head;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMaxMemorySize() {
		return maxMemorySize;
	}

	public void setMaxMemorySize(int maxMemorySize) {
		this.maxMemorySize = maxMemorySize;
	}

	public int getSizeSum() {
		return sizeSum;
	}

	public void setSizeSum(int sizeSum) {
		this.sizeSum = sizeSum;
	}



}