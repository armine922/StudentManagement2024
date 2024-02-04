package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Lesson;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.entity.UserType;
import com.example.studentmanagement.repository.LessonRepository;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Value("/Users/armenuhiyeghoyan/IdeaProjects/StudentManagement/pictures")
    private String uploadDirectory;

    @GetMapping("/teachers")
    public String teacherPage(ModelMap modelMap) {
        List<User> teachers=userRepository.findAllByUserType(UserType.TEACHER);
        modelMap.addAttribute("teachers", teachers);
        return "teachers";
    }

    @GetMapping("/teachers/add")
    public String addTeacherPage(ModelMap modelMap) {
        modelMap.addAttribute("lessons", lessonRepository.findAll());

        return "addTeacher";
    }

    @PostMapping("/teachers/add")
    public String addTeacher(@ModelAttribute User user, @RequestParam int lessonId,
                             @RequestParam("picName") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            user.setPicName(picName);
        }
        Optional<Lesson> byId = lessonRepository.findById(lessonId);
        if (byId.isPresent()) {
            Lesson lesson = byId.get();
            user.setLesson(lesson);
            userRepository.save(user);
        }

        return "redirect:/teachers";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/teachers";

    }

    @GetMapping("/teachers/update/{id}")
    public String updateTeacherPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("teacher", byId.get());
        } else {
            return "redirect:/teachers";
        }
        return "updateTeacher";
    }

    @PostMapping("/teachers/update")
    public String updateTeacher(@ModelAttribute User teacher) {

        userRepository.save(teacher);
        return "redirect:/teachers";
    }


    @GetMapping("/students")
    public String studentsPage(ModelMap modelMap) {

modelMap.addAttribute("students",userRepository.findAllByUserType(UserType.STUDENT));
        return "students";
    }

    @GetMapping("/students/add")
    public String addStudentPage(ModelMap modelMap) {
        modelMap.addAttribute("lessons", lessonRepository.findAll());

        return "addStudent";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute User user, @RequestParam int lessonId,
                             @RequestParam("picName") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            user.setPicName(picName);
        }
        Optional<Lesson> byId = lessonRepository.findById(lessonId);
        if (byId.isPresent()) {
            Lesson lesson = byId.get();
            user.setLesson(lesson);
            userRepository.save(user);
        }

        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudentsPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {

            modelMap.addAttribute("users", byId.get());
        } else {
            return "redirect:/students";
        }
        return "updateStudent";
    }

    @PostMapping("/students/update")
    public String updateStudents(@ModelAttribute User user) {

        userRepository.save(user);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/students";
    }


}
