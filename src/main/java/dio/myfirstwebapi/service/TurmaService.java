package dio.myfirstwebapi.service;

import dio.myfirstwebapi.model.Turma;


public interface TurmaService {

    Iterable<Turma> buscarTodos();

    Turma buscarPorId(Long id);

    void inserir(Turma turma);

    void atualizar(Long id, Turma turma);

    void deletar(Long id);       
}
