package mk.ukim.finki.courses.web.rest;

import mk.ukim.finki.courses.model.Category;
import mk.ukim.finki.courses.model.Course;
import mk.ukim.finki.courses.model.CourseUser;
import mk.ukim.finki.courses.model.DTO.LoginDTO;
import mk.ukim.finki.courses.model.DTO.PaginatedLecturersDto;
import mk.ukim.finki.courses.model.DTO.UserDto;
import mk.ukim.finki.courses.service.CourseService;
import mk.ukim.finki.courses.service.CourseUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://courses-app-umkt.onrender.com")
@RequestMapping("/api/user")
public class CourseUserController {

    private final CourseUserService courseUserService;

//    private final CourseService courseService;


    public CourseUserController(CourseUserService courseUserService) {
        this.courseUserService = courseUserService;
//        this.courseService = courseService;
    }

    @PostMapping
    public UserDto create(@RequestBody CourseUser user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getImageUrl(),
                user.getDescription()
                );
        this.courseUserService.create(user);
        return userDto;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDTO loginDTO){
        CourseUser user = this.courseUserService.login(loginDTO.getEmail(),loginDTO.getPassword());
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getImageUrl(),
                user.getDescription()
        );
        return userDto;
    }

    @PutMapping("/edit")
    public UserDto edit(@RequestBody UserDto userDto){
        CourseUser user = this.courseUserService.edit(userDto);
        UserDto returnDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getImageUrl(),
                user.getDescription()
        );
        return returnDto;
    }

    @GetMapping("/lecturers")
    public PaginatedLecturersDto getLecturers(@RequestParam int page){
        return this.courseUserService.getLecturers(page);
    }

    @GetMapping("/{username}")
    public UserDto findById(@PathVariable String username){
         return this.courseUserService.findByUsername(username);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<List<Course>> getAllMyCourses(@PathVariable Long id) {
        return ResponseEntity.ok(courseUserService.getUserCourses(id));
    }

    @GetMapping("/owns")
    public boolean hasBoughtCourse(@RequestParam Long userId, @RequestParam Long courseId){
        return this.courseUserService.HasBought(userId,courseId);
    }

}
