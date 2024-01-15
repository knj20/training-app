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
public class UserController {


    @Autowired
    private IEmployeeSrvice employeeService;

    @Autowired
    private ITrainingService trainingService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/manager")
    public void manager(){
        System.out.println("test");

    }
    @RequestMapping("/")
    public String showHomePage() {
        //userService.sendName("karima");
        return "home";
    }
    @PostMapping("employee/save")
    public String saveEmployee(
            @ModelAttribute Employee employee,
            RedirectAttributes attributes
    ) {
        Employee emp = employeeService.saveEmployee(employee);
        int cne = emp.getCNE();
        attributes.addAttribute("L'employé avec le CNE :"+cne+" a été enregistré avec succès !");
        emailService.sendEmail(emp.getTraining().getNom_training(),emp.getEmail(), EventType.UserAddedToTraining);
        return "redirect:/employee";
    }

    @GetMapping("employee/edit")
    public String getEditPage(
            Model model,
            RedirectAttributes attributes,
            @RequestParam int id
    ) {
        String page = null;
        try {
            Employee employee = employeeService.getEmployeeById(id);
            List<Training> trainings= trainingService.getAllTrainings();
            model.addAttribute("employee", employee);
            model.addAttribute("training_list", trainings);
            page="editEmployee";
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/employee";
        }
        return page;
    }
    @PostMapping("employee/update")
    public String updateInvoice(
            @ModelAttribute Employee employee,
            RedirectAttributes attributes
    ) {
        employeeService.updateEmployee(employee);
        int cne = employee.getCNE();
        attributes.addAttribute("message", "Employé avec le CNE :"+cne+"'  a été mis à jour avec succès !");
        emailService.sendEmail(employee.getTraining().getNom_training(),employee.getEmail(), EventType.TrainingIsUpdated);
        return "redirect:/employee";
    }


    @RequestMapping("/employee")
    public String showEmployeePage( @RequestParam(value = "message", required = false) String message,
                                    Model model) {

        List<Employee> employees= employeeService.getAllEmployees();
        List<Training> trainings= trainingService.getAllTrainings();
        model.addAttribute("list", employees);
        model.addAttribute("message", message);
        model.addAttribute("training_list",trainings);
        return "employee";
    }

    @RequestMapping("/edit")
    public String editEmployeePage() {
        return "editEmployee";
    }

    @GetMapping("employee/delete")
    public String deleteEmployee(
            @RequestParam int id,
            RedirectAttributes attributes
    ) {
        try {
            int cne = employeeService.getEmployeeById(id).getCNE();
            String trainingName = employeeService.getEmployeeById(id).getTraining().getNom_training();
            String email = employeeService.getEmployeeById(id).getEmail();
            employeeService.deleteEmployeeById(id);
            attributes.addAttribute("message", "Employé avec le CNE : "+cne+" a été supprimé avec succès !");
            emailService.sendEmail(trainingName,email, EventType.UserRemovedFromTraining);
        } catch (Exception e) {
            e.printStackTrace();
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/employee";
    }
}
