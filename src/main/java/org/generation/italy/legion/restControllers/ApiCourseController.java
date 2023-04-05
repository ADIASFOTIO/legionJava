package org.generation.italy.legion.restControllers;

import org.generation.italy.legion.dtos.CourseDto;
import org.generation.italy.legion.dtos.SimpleTeacherDto;
import org.generation.italy.legion.model.data.exceptions.DataException;
import org.generation.italy.legion.model.entities.Course;
import org.generation.italy.legion.model.entities.Teacher;
import org.generation.italy.legion.model.services.abstractions.AbstractCourseDidacticService;
import org.generation.italy.legion.model.services.abstractions.AbstractTeacherDidacticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/courses")
public class ApiCourseController {
    private AbstractCourseDidacticService service;
    @Autowired
    public ApiCourseController(AbstractCourseDidacticService service) {
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<Iterable<CourseDto>> findCoursesByTitleContains(@RequestParam(required = false) String part){
        try {
            Iterable<Course> courses = service.findCoursesByTitleContains(part);
            return ResponseEntity.ok().body(CourseDto.fromEntityIterable(courses));
        } catch (DataException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

