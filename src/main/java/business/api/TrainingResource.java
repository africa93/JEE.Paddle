package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public List<TrainingWrapper> showTrainings(){
		return trainingController.showTrainings();
	}
	
	@RequestMapping(value= Uris.REGISTER_TRAINING + Uris.ID, method = RequestMethod.POST)
	public void registerTraining(@AuthenticationPrincipal User trainer, @PathVariable int trainingID, @RequestBody String pupilUsername){
		trainingController.registerTraining(trainingID,pupilUsername);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteTraining(@AuthenticationPrincipal User trainer, @RequestParam(required = true) String trainingID){
		trainingController.deleteTraining(Integer.parseInt(trainingID));
	}
	
	@RequestMapping(value = Uris.DELETE_TRAINING_PLAYER + Uris.ID, method = RequestMethod.POST)
	public void deleteTrainingPlayer(@AuthenticationPrincipal User trainer, @PathVariable int trainingID, @RequestBody String pupilUsername){
		trainingController.deleteTrainingPlayer(trainingID, pupilUsername);
	}
}
