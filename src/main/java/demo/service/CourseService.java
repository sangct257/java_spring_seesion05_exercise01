package demo.service;

import demo.model.dto.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CourseService {
    Page<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction);
}
