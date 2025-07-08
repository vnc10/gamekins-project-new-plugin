package gamekins.project.controller;

import gamekins.project.domain.dto.EnrollmentDTO;
import gamekins.project.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnrollmentDTO createEnrollment(@RequestBody EnrollmentDTO enrollment) {
        return enrollmentService.create(enrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable Long id) {
        return enrollmentService.findById(id)
                .map(enrollment -> {
                    enrollmentService.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
