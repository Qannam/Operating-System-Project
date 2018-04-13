
public class PCB {
	private String ID;
	private String state;
	private String CPUTime;
	private String memorySize;
	
	private String RunningTime;
	private String RemainingTime;
	private String waitingTime;
	
	private boolean terminatedNormally ;
	
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
	public PCB(String iD, String state, String cPUTime, String memorySize ,String RunningTime , String waitingTime ) {
		ID = iD;
		this.state = state;
		CPUTime = cPUTime;
		this.memorySize = memorySize;
		this.waitingTime  = waitingTime;
		this.RunningTime = RunningTime;
		terminatedNormally = false;
		RemainingTime = CPUTime;
	}
	
	public PCB( PCB PCB) {
		this.ID = PCB.getID();
		this.state = PCB.state;
		this.CPUTime = PCB.getCPUTime();
		this.memorySize = PCB.getMemorySize();
		this.waitingTime  = waitingTime;
		this.RunningTime = RunningTime;
		terminatedNormally = false;
		RemainingTime = CPUTime;
	}
	
	public boolean isTerminatedNormally() {
		return terminatedNormally;
	}
	public void setTerminatedNormally(boolean terminatedNormally) {
		this.terminatedNormally = terminatedNormally;
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
	public String getRemainingTime() {
		return RemainingTime;
	}
	public void setRemainingTime(String remainingTime) {
		RemainingTime = remainingTime;
	}
	
	

}
