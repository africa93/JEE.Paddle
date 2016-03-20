package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TrainingWrapper {
	private Calendar startDate;
	private Calendar endDate;
	private CourtState court;
	private UserWrapper trainer;
	
	public TrainingWrapper() {}
	public TrainingWrapper(Calendar startDate, Calendar endDate, CourtState court, UserWrapper trainer) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.court = court;
		this.trainer = trainer;
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
	public CourtState getCourt() {
		return court;
	}
	public void setCourt(CourtState court) {
		this.court = court;
	}
	public UserWrapper getTrainer() {
		return trainer;
	}
	public void setTrainer(UserWrapper trainer) {
		this.trainer = trainer;
	}
	@Override
	public String toString() {

		String strdate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(startDate.getTime());
		String nddate = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(endDate.getTime());
		return "TrainingWrapper [startDate=" + strdate + ", endDate=" + nddate + ", court=" + court + ", trainer="
				+ trainer + "]";
	}
}
