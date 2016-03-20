package data.daos;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Training;
import data.entities.User;


public interface TrainingDao extends  JpaRepository<Training, Integer>, TrainingExtended{
	
	List<Training> findByTrainer(User user);
	Training findById(int idTraining);
	
	@Query(value="select * from training where startdate= ?1 and court_id = ?2", nativeQuery = true)
	Training findByLessonTime(Calendar date, int court_id);
}