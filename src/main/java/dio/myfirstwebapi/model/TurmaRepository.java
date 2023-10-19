package dio.myfirstwebapi.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Long> {

    Optional<Turma> findOneByNome(String nome);
    
}
