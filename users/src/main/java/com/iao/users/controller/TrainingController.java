package com.iao.users.controller;

import com.iao.users.event.EventType;
import com.iao.users.model.entity.Employee;
import com.iao.users.model.entity.Training;
import com.iao.users.service.emailService.EmailService;
import com.iao.users.service.employeeService.IEmployeeSrvice;
import com.iao.users.service.trainingService.ITrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TrainingController {

    @Autowired
    private ITrainingService trainingService;

    @Autowired
    private IEmployeeSrvice employeeService;

    @Autowired
    private EmailService emailService;

    @PostMapping("training/save")
    public String saveTraining(
            @ModelAttribute Training training,
            RedirectAttributes attributes
    ) {
        Training tra= trainingService.saveTraining(training);
        attributes.addAttribute("La formation avec le nom :"+tra.getNom_training()+" a été enregistré avec succès !");
        return "redirect:/training";
    }

    @GetMapping("training/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ) {
        String page = null;
        try {
            Training training = trainingService.getTrainingById(id);
            model.addAttribute("training", training);
            page="editTraining";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/training";
        }
        return page;
    }
    @PostMapping("training/update")
    public String updateTraining(
            @ModelAttribute Training training,
            RedirectAttributes attributes
    ) {
        trainingService.updateTraining(training);
        attributes.addAttribute("message", "La formation avec le nom :"+training.getNom_training()+"'  a été mis à jour avec succès !");
        return "redirect:/training";
    }


    @RequestMapping("/training")
    public String showTrainingPage( @RequestParam(value = "message", required = false) String message,
                                    Model model) {

        List<Training> trainings= trainingService.getAllTrainings();
        model.addAttribute("list", trainings);
        model.addAttribute("message", message);
        return "training";
    }

    @RequestMapping("training/edit")
    public String editTrainingPage() {
        return "editTraining";
    }

    @GetMapping("training/delete")
    public String deleteTraining(
            @RequestParam int id,
            RedirectAttributes attributes
    ) {
        try {
            String name = trainingService.getTrainingById(id).getNom_training();
            List<Employee> emps = employeeService.getEmployeesByTrainingId(id);

            trainingService.deleteTrainingById(id);

            for (Employee em : emps) {
                emailService.sendEmail(name,em.getEmail(), EventType.UserRemovedFromTraining);
            }
            attributes.addAttribute("message", "La formation avec le Nom : "+name+" a été supprimé avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/training";
    }
}
