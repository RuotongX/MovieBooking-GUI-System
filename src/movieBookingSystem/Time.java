package movieBookingSystem;
/**
 * This class creates the attributes for time and have the geter and setter
 * The comparable interface is overrided in this class to calculate which time is earlyer
 * @author 16921494
 *
 */

public class Time implements Comparable{
	private int hours;
	private int mins;
	private int secs;
	public Time() {
		
	}
	public Time(int hours) {
		this.hours = 0;
	}
	public Time(int hours,int mins) {
		this.hours = 0;
		this.mins = 0;
	}
	public Time(int hours,int mins,int secs) {
		this.hours = hours;
		this.mins = mins;
		this.secs = secs;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		if(hours>=0&&hours<=23) {
		    this.hours = hours;
		}
	}
	public int getMinutes() {
		return mins;
	}
	public void setMinutes(int mins) {
		if(mins>=0&&mins<=59) {
		    this.mins = mins;
		}
	}
	public int getSeconds() {
		return secs;
	}
	public void setSeconds(int secs) {
		if(secs>=0&&secs<=59){
		    this.secs = secs;
		}
	}
	public boolean equals(Time otherTime) {
		if(otherTime.getHours()==this.hours&&
				otherTime.getMinutes()==this.getMinutes()&&
				otherTime.getSeconds()==this.getSeconds()) {
			return true;
		}
		return false;
	}
	public String toString() {
		if(mins == 0) {
			if(secs == 0) {
				return this.hours+":"+this.mins+"0:"+this.secs+"0";
			}
			return  this.hours+":"+this.mins+"0:"+this.secs;
		}
		if(secs == 0) {
			return  this.hours+":"+this.mins+":"+this.secs+"0";
		}
		return this.hours+":"+this.mins+":"+this.secs;
	}
	public int CompareTo(Object o) {
		Time e = (Time)o;
		if(e.timecalaulator()<this.timecalaulator()){
			return -1;
		}
		return 0;
	}
	public int timecalaulator() {
		return this.hours*3600+this.mins*60+this.secs;
	}
}
