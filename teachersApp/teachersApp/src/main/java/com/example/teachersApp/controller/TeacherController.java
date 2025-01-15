package com.example.teachersApp.controller;
import com.example.teachersApp.model.Teacher;
import com.example.teachersApp.model.TeacherDto;
import com.example.teachersApp.service.TeacherServiceImpl;
import com.example.teachersApp.service.exceptions.TeacherEmailAlreadyExistsException;
import com.example.teachersApp.service.exceptions.TeacherNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
@RequestMapping
public class TeacherController {
    @Autowired
    private final TeacherServiceImpl teacherServiceImpl;

    public TeacherController(TeacherServiceImpl teacherServiceImpl) {
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @GetMapping(value = "/")                        //-----------------------------HOME PAGE------------------------------------
    public String showTeacherPage(){
        return "teachersList";
    }

    @GetMapping(value = "/teachers")                  //-----------------------------SHOW ALL------------------------------------
    public String showAllTeachers(Model model){
        model.addAttribute("teacher", teacherServiceImpl.getTeachers());
        return "teachersList";
    }

    @GetMapping(value = "/teachers/search")           //-----------------------------SEARCH------------------------------------
    public String showTeacherList(Model model, @RequestParam String lname, RedirectAttributes redirectAttributes) throws TeacherNotFoundException {
        if (lname != null){
            List<Teacher> teacherList = teacherServiceImpl.findByLname(lname);
            if(teacherList.isEmpty()){
                model.addAttribute("teacher", teacherList);
                redirectAttributes.addFlashAttribute("error", "Teacher with last name: " + lname + " does no exist");
                return "redirect:/";
            }
            else {
                model.addAttribute("teacher", teacherServiceImpl.findByLname(lname));
                return "teachersList";
            }
        }
        else {
            return "teachersList";
        }

    }

    @GetMapping(value = "/create")                 //-----------------------------CREATE PAGE------------------------------------
    public String showCreatePage(Model model){
        TeacherDto teacherDto = new TeacherDto();
        model.addAttribute("teacherDto", teacherDto);
        return "addTeacher";
    }

    @PostMapping("/create")                        //-----------------------------SAVE TEACHER------------------------------------
    public String createTeacher(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult result, RedirectAttributes redirectAttributes) throws TeacherEmailAlreadyExistsException {
        if(result.hasErrors()){
            return "addTeacher";
        }
        try{
            teacherServiceImpl.insertTeacher(teacherDto);
        }catch (TeacherEmailAlreadyExistsException  e1){
            result.addError(new FieldError("teacherDto", "email", "E-mail already exists!"));
            return "addTeacher";
        }
        redirectAttributes.addFlashAttribute("success", "Teacher successfully created!");
        return "redirect:/";
    }

    @GetMapping("/edit")                            //-----------------------------EDIT PAGE------------------------------------
    public String showUpdatePage(Model model, @RequestParam int id){
        try {
            Teacher teacher = teacherServiceImpl.findById(id);
            TeacherDto teacherDto = teacherServiceImpl.setTeacherDto(teacher);
            model.addAttribute("teacherDto", teacherDto);
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/teachers";
        }
        return "/editTeacher";

    }

    @PostMapping("/edit")                        //-----------------------------UPDATE TEACHER------------------------------------
    public String updateTeacher(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult result, RedirectAttributes redirectAttributes) throws TeacherEmailAlreadyExistsException {
        if(result.hasErrors()){
            return "editTeacher";
        }
        try{
            teacherServiceImpl.updateTeacher(teacherDto);
        }catch (TeacherEmailAlreadyExistsException  e1){
            result.addError(new FieldError("teacherDto", "email", "E-mail already exists!"));
            redirectAttributes.addFlashAttribute("error", "Something went wrong!");
            return "editTeacher";
        }
        redirectAttributes.addFlashAttribute("success", "Teacher successfully updated!");
        return "redirect:/";
    }

    @GetMapping("/delete")                            //-----------------------------DELETE TEACHER------------------------------------
    public String deleteTeacher(Model model, @RequestParam int id, RedirectAttributes redirectAttributes){
        try {
            Teacher teacher = teacherServiceImpl.findById(id);
            teacherServiceImpl.deleteTeacher(teacher);

        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Something went wrong!");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("success", "Teacher successfully deleted!");
        return "redirect:/";

    }




}
