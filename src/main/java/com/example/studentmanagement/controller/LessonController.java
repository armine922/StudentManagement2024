package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Lesson;
import com.example.studentmanagement.entity.User;
import com.example.studentmanagement.entity.UserType;
import com.example.studentmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.repository.LessonRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@Controller
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;
@Autowired
private UserRepository userRepository;

    @GetMapping("/lessons")
    public String lessonsPage(ModelMap modelMap) {

       modelMap.addAttribute("lessons", lessonRepository.findAll());
        return "lessons";
    }

    @GetMapping("/lessons/add")
    public String addLessonPage(ModelMap modelMap) {
        modelMap.addAttribute("users",userRepository.findAllByUserType(UserType.TEACHER));
        return "addLesson";
    }

    @PostMapping("/lessons/add")
    public String addLesson(@ModelAttribute Lesson lesson,@RequestParam int userId){
        Optional<User> byId =userRepository.findById(userId);
        if(byId.isPresent()){
            User user=byId.get();
            lesson.setUser(user);
        lessonRepository.save(lesson);}
        return "redirect:/lessons";
    }
//
//    @GetMapping("/lessons/delete/{id}")
//    public String deleteLesson(@PathVariable("id") int id) {
//        lessonRepository.deleteById(id);
//        return "redirect:/lessons";
//
//    }
//
//    @GetMapping("/lessons/update/{id}")
//    public String updateLessonsPage(@PathVariable("id") int id, ModelMap modelMap) {
//        Optional<Lesson> byId = lessonRepository.findById(id);
//        if (byId.isPresent()) {
////            modelMap.addAttribute("teacher", teacherRepository.findAll());
//            modelMap.addAttribute("lesson", byId.get());
//        } else {
//            return "redirect:/lessons";
//        }
//        return "updateLesson";
//    }
//
//    @PostMapping("/lessons/update")
//    public String updateLessons(@ModelAttribute Lesson lesson) {
//
//        lessonRepository.save(lesson);
//        return "redirect:/lessons";
//    }
}
