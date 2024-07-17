package com.example.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";  // YYYY-MM-DD
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final LocalDate MAX_DATE_BORN = LocalDate.now().minusYears(18);
    private static final LocalDate MIN_DATE_BORN = LocalDate.now().minusYears(100);
    private static final LocalDate MAX_DATE_HIRE = LocalDate.now();
    private static final LocalDate MIN_DATE_HIRE = LocalDate.of(1991, 1, 1);
    private static final LocalDate MAX_DATE_FIRE = LocalDate.now().plusWeeks(5);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("Выведены все пользователи");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        logger.info("Выведен пользователь с id: {}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        checkInput(userDto);
        userService.addUser(userDto);
        logger.info("Пользователь успешно добавлен: {} {}", userDto.getFirstName(), userDto.getLastName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        checkInput(userDto);
        userDto.setId(id);
        userService.updateUser(userDto);
        logger.info("Пользователь успешно обновлен: {} {}", userDto.getFirstName(), userDto.getLastName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        logger.info("Пользователь успешно удален, id: {}", id);
        return ResponseEntity.ok().build();
    }

//    private void validateAndParseDate(String dateStr, String fieldName) {
//        if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
//            logger.error("Некорректный формат {}: {}", fieldName, dateStr);
//            throw new IllegalArgumentException(fieldName + " должна быть в формате YYYY-MM-DD.");
//        }
//        try {
//            LocalDate.parse(dateStr, DATE_FORMATTER);
//        } catch (DateTimeParseException e) {
//            logger.error("Некорректный формат {}: {}", fieldName, dateStr);
//            throw new IllegalArgumentException("Некорректный формат " + fieldName + ".");
//        }
//    }

    private void checkInput(UserDto userDto) {
        String birthDateString = DATE_FORMAT.format(userDto.getBirthDate());
        LocalDate birthDate = LocalDate.parse(birthDateString, DATE_FORMATTER);
        String HireDateString = DATE_FORMAT.format(userDto.getHireDate());
        LocalDate HireDate = LocalDate.parse(HireDateString, DATE_FORMATTER);

        if (!userDto.getFirstName().matches("[А-Яа-яA-Za-z]+")) {
            logger.warn("Попытка добавить пользователя с некорректным именем: {}", userDto.getFirstName());
            throw new IllegalArgumentException("Имя пользователя должно содержать только буквы.");
        }

        if (!userDto.getLastName().matches("[А-Яа-яA-Za-z]+")) {
            logger.warn("Попытка добавить пользователя с некорректной фамилией: {}", userDto.getLastName());
            throw new IllegalArgumentException("Фамилия пользователя должна содержать только буквы.");
        }

//        validateAndParseDate(birthDateString, "Дата рождения");
//        validateAndParseDate(HireDateString, "Дата принятия");
//        validateAndParseDate(FireDateString, "Дата увольнения");
        validateMinMaxDate(birthDate, "рождения", "min", MIN_DATE_BORN);
        validateMinMaxDate(birthDate, "рождения", "max", MAX_DATE_BORN);
        validateMinMaxDate(HireDate, "принятия", "min", MIN_DATE_HIRE);
        validateMinMaxDate(HireDate, "принятия", "max", MAX_DATE_HIRE);
        if (userDto.getFireDate() != null){
            String FireDateString = DATE_FORMAT.format(userDto.getFireDate());
            LocalDate FireDate = LocalDate.parse(FireDateString, DATE_FORMATTER);
            validateMinMaxDate(FireDate, "увольнения", "min", HireDate);
            validateMinMaxDate(FireDate, "увольнения", "max", MAX_DATE_FIRE);
        }

        if (!((userDto.getPositionId() > 0) & (userDto.getPositionId() < 4))) {
            logger.warn("Попытка добавить пользователя с некорректной должностью: {}", userDto.getPositionId());
            throw new IllegalArgumentException("Должность пользователя должна быть из списка : 1, 2, 3.");
        }

        if (!((userDto.getSalary() >= 0) & (userDto.getSalary() <= 5000000))) {
            logger.warn("Попытка добавить пользователя с некорректной ЗП: {}", userDto.getSalary());
            throw new IllegalArgumentException("ЗП пользователя должна быть от 0 до 5 000 000");
        }

    }

    private void validateMinMaxDate(LocalDate dateStr, String fieldName, String minMax, LocalDate minMaxDate) {
        String wordAfterBefore = "";
        if (minMax.equals("min")){
            if (dateStr.isBefore(minMaxDate)) {
                wordAfterBefore = "позже";
            }
        } else if (minMax.equals("max")){
            if (dateStr.isAfter(minMaxDate)) {
                wordAfterBefore = "раньше";
            }
        } else {
            throw new IllegalArgumentException("Функция validateMinMaxDate использована неверно: положено только min/max");
        }

        if (!wordAfterBefore.isEmpty()) {
            logger.warn("Попытка добавить пользователя с некорректной датой {}: {}", fieldName, dateStr);
            throw new IllegalArgumentException(String.format("Дата %s должна быть %s %s.", fieldName, wordAfterBefore, minMaxDate));
        }
    }
}
