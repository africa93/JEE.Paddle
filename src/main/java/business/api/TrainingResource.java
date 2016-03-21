package business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import business.controllers.TrainingController;
import business.wrapper.TrainingWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.TRAININGS)
public class TrainingResource {
	
	private TrainingController trainingController;
	
	@Autowired
	public void setTrainingController(TrainingController trainingController){
		this.trainingController = trainingController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void createTraining(@AuthenticationPrincipal User trainer, @RequestBody TrainingWrapper trainingWrapper){
		trainingController.createTraining(trainingWrapper);
	}
}
