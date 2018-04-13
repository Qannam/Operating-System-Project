
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
//		int count = 0 ;
//		Node current = head;
//		while(current != null){
//			current = current.next;
//			count++;
//		}
//		return count;
		return size;
	}

	public boolean enqueue(PCB e, int memorySize) {
		
		/* if the the queue is full i will take the node in tail and i will put it in the ArrayOfPCB then i will insert the new one
		 * this is done for the ready queue 
		 */
//		if( (memorySize+sizeSum) > maxMemorySize && maxMemorySize != -1){
//			if(size > 1){
//				Node current = head ;
//				Node beforTail = null;
//				while(!((current.getNext()).getNext() != null))
//					current = current.getNext();
//				
//				PCB trasferedPCB = new PCB((PCB) current.getData());
//				ArrayOfPCB.list.enqueue(trasferedPCB) ;
//				
//				current.getNext().setNext(null);
//			}
//			
//		}
		
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
		sizeSum -= Integer.parseInt(pqe.getMemorySize());
		return pqe;
	}
	
	public PCB serve2(){
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


}
