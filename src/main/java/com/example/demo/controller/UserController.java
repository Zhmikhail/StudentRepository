package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    String regex = "[А-Яа-яA-Za-z]+";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Outputted all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        logger.info("User outputted id: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        validateUserDto(userDto);
        userService.addUser(userDto);
        logger.info("User added: {} {}", userDto.getFirstName(), userDto.getLastName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        validateUserDto(userDto);
        userDto.setId(id);
        userService.updateUser(userDto);
        logger.info("User updated: {} {}", userDto.getFirstName(), userDto.getLastName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        logger.info("User deleted, id: {}", id);
        return ResponseEntity.ok().build();
    }

    private void validateUserDto(UserDto userDto) {
        String birthDateString = DateUtil.DATE_FORMAT.format(userDto.getBirthDate());
        LocalDate birthDate = LocalDate.parse(birthDateString, DateUtil.DATE_FORMATTER);
        String hireDateString = DateUtil.DATE_FORMAT.format(userDto.getHireDate());
        LocalDate hireDate = LocalDate.parse(hireDateString, DateUtil.DATE_FORMATTER);

        validateName(userDto.getFirstName());
        validateName(userDto.getLastName());

        validateBirthDate(birthDate);
        validateHireDate(hireDate);
        if (userDto.getFireDate() != null){
            String FireDateString = DateUtil.DATE_FORMAT.format(userDto.getFireDate());
            LocalDate fireDate = LocalDate.parse(FireDateString, DateUtil.DATE_FORMATTER);
            validateFireDate(fireDate, hireDate);
        }

        if (!((userDto.getPositionId() > 0) & (userDto.getPositionId() < 4))) {
            logger.warn("Try to add user with incorrect position: {}", userDto.getPositionId());
            throw new IllegalArgumentException("position must be in list : 1, 2, 3.");
        }

        if (!((userDto.getSalary() >= 0) & (userDto.getSalary() <= 5000000))) {
            logger.warn("Try to add user with incorrect salary: {}", userDto.getSalary());
            throw new IllegalArgumentException("Salary must be between 0 and 5 000 000");
        }

    }

    private void validateName(String name){
        if (!name.matches(regex)) {
            logger.warn("Try to add user with incorrect name: {}", name);
            throw new IllegalArgumentException("Name must be a letter of english or russian language");
        }
    }

    private void validateBirthDate(LocalDate birtDate) {
        validateDateBetween(birtDate, DateUtil.MIN_DATE_BORN, DateUtil.MAX_DATE_BORN);
    }

    private void validateHireDate(LocalDate hireDate) {
        validateDateBetween(hireDate, DateUtil.MIN_DATE_HIRE, DateUtil.MAX_DATE_HIRE);
    }

    private void validateFireDate(LocalDate fireDate, LocalDate hireDate) {
        validateDateBetween(fireDate, hireDate, DateUtil.MAX_DATE_FIRE);
    }

    private void validateDateBetween(LocalDate dateStr, LocalDate minDate, LocalDate maxDate) {
        if (dateStr.isBefore(minDate) & dateStr.isAfter(maxDate)) {
            logger.warn("Tried to add user with incorrect date : {}", dateStr);
            throw new IllegalArgumentException(String.format("Date %s must be between %s and %s.", dateStr, minDate, maxDate));
        }
    }
}
