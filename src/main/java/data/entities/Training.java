package data.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Training {
	@Id
	@GeneratedValue
	private int id;
	
	private Calendar startDate;
	private Calendar endDate;
	
	@ManyToOne
	@JoinColumn
	private Court court;
	
	private final int NUM_MAX_PUPILS = 4;
	
	@ManyToOne
	@JoinColumn
	private User trainer;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<User> pupils;
	
	public Training(){}
	
	public Training(Calendar startDate, Calendar endDate, Court court, User user){
		this.startDate = startDate;
		this.endDate = endDate;
		this.court = court;
		this.trainer = user;
		this.pupils = new ArrayList<User>();
	}
	@Override
	public String toString() {
		String strdate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(startDate.getTime());
		String nddate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(endDate.getTime());
		return "Training [id=" + id + ", startDate=" + strdate + ", endDate=" + nddate 
				+ ", court=" + court + ", numPupilsTraining=" + NUM_MAX_PUPILS + ", user=" + trainer + ", pupils=" + pupils + "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			return id == ((Training) obj).id;
		}
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setUser(User trainer) {
		this.trainer = trainer;
	}

	public List<User> getPupils() {
		return pupils;
	}

	public boolean addPupil(User user){
		if (pupils.size()<NUM_MAX_PUPILS && !isPupil(user)){
			return pupils.add(user);
		}
		return false;
	}
	public boolean removePupil(User user){
		return pupils.remove(user);
	}

	public boolean isPupil(User pupil) {
		for(User u:pupils){
			if(u.equals(pupil)){
				return true;
			}
		}
		return false;
	}
	public Date getLessonTime(){
		return this.startDate.getTime();
	}
}
