package com.iao.users.service.trainingService;

import com.iao.users.model.entity.Employee;
import com.iao.users.model.entity.Training;
import com.iao.users.repository.IEmployeeRepository;
import com.iao.users.repository.ITrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService implements ITrainingService{

    @Autowired
    private ITrainingRepository repo;
    @Override
    public Training saveTraining(Training training) {
        return repo.save(training);
    }

    @Override
    public List<Training> getAllTrainings() {
        return repo.findAll();
    }

    @Override
    public Training getTrainingById(int id) {
        Optional<Training> opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @Override
    public void deleteTrainingById(int id) {
        repo.delete(getTrainingById(id));
    }


    @Override
    public void updateTraining(Training training) {
        repo.save(training);
    }
}
