package demo.controller;

import demo.model.dto.response.ApiResponse;
import demo.model.dto.response.CourseResponse;
import demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getPagedCourses(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, @RequestParam(value = "sortBy", defaultValue = "") String sortBy, @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction) {
        return new ResponseEntity<>(new ApiResponse<>(
                true,
                "Đã lấy ra danh sách",
                courseService.getPagedCourses(page - 1, size, sortBy, direction),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
