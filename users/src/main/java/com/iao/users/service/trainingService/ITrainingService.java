package com.iao.users.service.trainingService;

import com.iao.users.model.entity.Employee;
import com.iao.users.model.entity.Training;

import java.util.List;

public interface ITrainingService {
    public Training saveTraining(Training training);
    public List<Training> getAllTrainings();
    public Training getTrainingById(int id);
    public void deleteTrainingById(int id);
    public void updateTraining(Training training);
}
