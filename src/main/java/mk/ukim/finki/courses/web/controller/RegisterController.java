package mk.ukim.finki.courses.web.controller;

import mk.ukim.finki.courses.model.dto.CourseUserCreationDto;
import mk.ukim.finki.courses.model.enumerations.Role;
import mk.ukim.finki.courses.service.CourseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
* This controller is just to add admin login to db
* Go to http://localhost:9091/register
* And admin user will be created
* Username:admin
* Password:admin
* Remove controller if necessary
* */

@Controller
public class RegisterController {

    private final CourseUserService courseUserService;

    public RegisterController(CourseUserService courseUserService) {
        this.courseUserService = courseUserService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        try{
            CourseUserCreationDto userDto = new CourseUserCreationDto();
            userDto.setName("admin");
            userDto.setRole(Role.ROLE_STUDENT.toString());
            userDto.setPassword("admin");
            userDto.setSurname("admin");
            userDto.setEmail("admin@admin.com");
            userDto.setUsername("admin");
            courseUserService.create(userDto);
        }catch (Exception e){
            System.out.println("Admin already created");
        }
        return "redirect:/login";
    }

}
