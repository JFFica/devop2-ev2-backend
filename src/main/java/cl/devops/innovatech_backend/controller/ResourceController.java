package cl.devops.innovatech_backend.controller;

import cl.devops.innovatech_backend.model.Resource;
import cl.devops.innovatech_backend.repository.ResourceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*")
public class ResourceController {

    private final ResourceRepository resourceRepository;

    public ResourceController(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @GetMapping
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceRepository.save(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceRepository.deleteById(id);
    }
}