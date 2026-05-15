package cl.devops.innovatech_backend.controller;

import cl.devops.innovatech_backend.repository.ProjectRepository;
import cl.devops.innovatech_backend.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final ProjectRepository projectRepository;
    private final ResourceRepository resourceRepository;

    public AnalyticsController(ProjectRepository projectRepository, ResourceRepository resourceRepository) {
        this.projectRepository = projectRepository;
        this.resourceRepository = resourceRepository;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        summary.put("totalProjects", projectRepository.count());
        summary.put("totalResources", resourceRepository.count());
        summary.put("systemStatus", "Activo");

        return summary;
    }
}