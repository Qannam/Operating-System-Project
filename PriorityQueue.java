
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
		int count = 0 ;
		Node current = head;
		while(current != null){
			current = current.next;
			count++;
		}
		return count;
	}

	public boolean enqueue(PCB e, int memorySize) {
		
		/* if the the queue is full i will take the node in tail and i will put it in the ArrayOfPCB then i will insert the new one
		 * this is done for the ready queue 
		 */
		if( (memorySize+sizeSum) > maxMemorySize && maxMemorySize != -1){
			if(size > 1){
				Node current = head ;
				Node beforTail = null;
				while(!(current.getNext()).getNext().equals(null))
					current = current.getNext();
				current.getNext().setData(null);
			}
			
		}
		
		Node<PCB> tmp = new Node<PCB>(e, memorySize);
		
		 
		if((size == 0) || (memorySize < head.priority)) {
			tmp.next = head;
			head = tmp;
			sizeSum += memorySize;
			size++;
			return true;
		}
		else {
			Node<PCB> p = head;
			Node<PCB> q = null;
			while((p != null) && (memorySize >= p.priority)) {
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


	public PCB serve(){
		PCB pqe = head.getData();
		head = head.next;
		size--;
		return pqe;
	}


}
