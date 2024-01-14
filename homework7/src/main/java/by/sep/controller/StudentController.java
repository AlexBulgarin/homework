package by.sep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @GetMapping("/students")
    public String getStudentPage() {
        return "students";
    }
}

