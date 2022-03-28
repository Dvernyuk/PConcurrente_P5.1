package servidor;

public class Monitor {
	private boolean cond = true;
	
	synchronized public void P() {
		if(!cond)
			try {
				this.wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		
		cond = false;
	}
	
	synchronized public void V() {
		cond = true;
		
		this.notify();
	}
}
