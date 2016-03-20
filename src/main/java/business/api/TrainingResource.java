package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;

import business.api.exceptions.InvalidCourtTrainingException;
import business.api.exceptions.InvalidDateException;
import business.controllers.CourtController;
import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {
	
    private CourtController courtController;
	
	private TrainingController trainingController;
	
	@Autowired
    public void setCourtController(CourtController courtController) {
        this.courtController = courtController;
    }
	
	@Autowired
	public void setTrainingController(TrainingController trainingController){
		this.trainingController = trainingController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void createTraining(@AuthenticationPrincipal User activeUser, @RequestBody TrainingWrapper trainingWrapper)
	throws InvalidDateException, InvalidCourtTrainingException{
		if(!courtController.exist(trainingWrapper.getCourt().getCourtId())){
			throw new InvalidCourtTrainingException("" + trainingWrapper.getCourt().getCourtId());
		}
		if(trainingWrapper.getEndDate().getTimeInMillis()-trainingWrapper.getStartDate().getTimeInMillis() != 3600000){
			throw new InvalidDateException();
		}
		if(!trainingController.createTraining(trainingWrapper)){
			throw new Error("No se puede crear el entrenamiento");
		}
	}
}
