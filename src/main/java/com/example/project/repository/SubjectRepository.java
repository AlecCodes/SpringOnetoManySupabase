package com.example.project.repository;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.Subject;


//JpaRepository needs two type parameters, the type of the entity, and the type of the primary key
public interface SubjectRepository extends JpaRepository<Subject,Long>{
    List<Subject> findById(long postId);

    @Transactional
    void deleteById(long subjectId);
}
