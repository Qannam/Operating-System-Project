
public class PCB {
	private String ID;
	private String state;
	private String CPUTime;
	private String memorySize;
	
	private String readyTime;
	private String RunningTime;
	private String waitingTime;
	
	public String getReadyTime() {
		return readyTime;
	}
	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}
	public String getRunningTime() {
		return RunningTime;
	}
	public void setRunningTime(String runningTime) {
		RunningTime = runningTime;
	}
	public String getWaitingTime() {
		return waitingTime;
	}
	public void setWaitingTime(String waitingTime) {
		this.waitingTime = waitingTime;
	}
	public PCB(String iD, String state, String cPUTime, String memoryTime) {
		ID = iD;
		this.state = state;
		CPUTime = cPUTime;
		this.memorySize = memoryTime;
	}
	
	public PCB( PCB PCB) {
		this.ID = PCB.getID();
		this.state = PCB.state;
		this.CPUTime = PCB.getCPUTime();
		this.memorySize = PCB.getMemorySize();
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCPUTime() {
		return CPUTime;
	}
	public void setCPUTime(String cPUTime) {
		CPUTime = cPUTime;
	}
	public String getMemorySize() {
		return memorySize;
	}
	public void setMemorySize(String memoryTime) {
		this.memorySize = memoryTime;
	}
	
	

}
