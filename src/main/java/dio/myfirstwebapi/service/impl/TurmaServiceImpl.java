package dio.myfirstwebapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dio.myfirstwebapi.model.Turma;
import dio.myfirstwebapi.model.TurmaRepository;
import dio.myfirstwebapi.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public Iterable<Turma> buscarTodos() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma buscarPorId(Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada");
        }
        return turma.get();
    }

    @Override
    public void inserir(Turma turma) {
         // Verifica se a turma já existe
        String nomeTurma = turma.getNome();
        Optional<Turma> turmaBd = turmaRepository.findOneByNome(nomeTurma);
        if(turmaBd.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma já existente");
        }
        turmaRepository.save(turma);
    }

    @Override
    public void atualizar(Long id, Turma turma) {
        // Buscar turma pelo ID, caso exista
        Optional<Turma> turmaBd = turmaRepository.findById(id);
        if(turmaBd.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada");
        }
        //vincula o id da turma no modelo
        Long turmaId = turmaBd.get().getId();
        turma.setId(turmaId);
        turmaRepository.save(turma);
    }

    @Override
    public void deletar(Long id) {
        //Deleta turma pelo id
        turmaRepository.deleteById(id);
    }
}
