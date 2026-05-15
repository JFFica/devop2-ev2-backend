package cl.devops.innovatech_backend.repository;


import cl.devops.innovatech_backend.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}