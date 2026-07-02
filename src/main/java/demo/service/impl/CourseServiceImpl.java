package demo.service.impl;

import demo.model.dto.response.CourseInstructorResponse;
import demo.model.dto.response.CourseResponse;
import demo.repositoty.CourseRepository;
import demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {
        if (page < 0){
            page = 0;
        }

        if (sortBy == null || sortBy.isBlank()){
            sortBy = "id";
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(direction, sortBy)
        );

        return courseRepository.findAll(pageable)
                .map(course -> CourseResponse.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .status(course.getStatus())
                        .instructor(
                                CourseInstructorResponse.builder()
                                        .id(course.getInstructor().getId())
                                        .name(course.getInstructor().getName())
                                        .build()
                        )
                        .build());
    }
}
