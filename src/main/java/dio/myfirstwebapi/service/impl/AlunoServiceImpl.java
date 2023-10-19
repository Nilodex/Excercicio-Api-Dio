package dio.myfirstwebapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dio.myfirstwebapi.model.Aluno;
import dio.myfirstwebapi.model.AlunoRepository;
import dio.myfirstwebapi.model.Turma;
import dio.myfirstwebapi.model.TurmaRepository;
import dio.myfirstwebapi.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public Iterable<Aluno> buscarTodos() {
        return alunoRepository.findAll();
    }

    @Override
    public Aluno buscarPorId(Long id) {
       Optional<Aluno> aluno = alunoRepository.findById(id);
       if(aluno.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
       }
       return aluno.get();
    }

    @Override
    public void inserir(Aluno aluno) {
        // Verifica se a turma existe
        String nomeTurma = aluno.getTurma().getNome();
        Optional<Turma> turma = turmaRepository.findOneByNome(nomeTurma);
        if(turma.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não existente. Crie uma nova turma antes de vincular um aluno à ela.");
        }
        aluno.setTurma(turma.get());
       //inserir aluno, vinculando com a turma
        alunoRepository.save(aluno);
    }

    @Override
    public void atualizar(Long id, Aluno aluno) {
        // Buscar aluno pelo ID, caso exista
        Optional<Aluno> alunoBd = alunoRepository.findById(id);
        if(alunoBd.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado");
        }
        //vincula o id do aluno no modelo
        Long alunoId = alunoBd.get().getId();
        aluno.setId(alunoId);
        inserir(aluno);
    }

    @Override
    public void deletar(Long id) {
        //Deleta aluno pelo id
        alunoRepository.deleteById(id);
    }
}
