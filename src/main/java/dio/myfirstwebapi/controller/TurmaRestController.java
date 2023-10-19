package dio.myfirstwebapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.myfirstwebapi.model.Turma;
import dio.myfirstwebapi.service.TurmaService;

@RestController
@RequestMapping("turmas")
public class TurmaRestController {
    
    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<Iterable<Turma>> buscarTodos(){
        return ResponseEntity.ok(turmaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Turma> inserir(@RequestBody Turma turma){
        turmaService.inserir(turma);
        return ResponseEntity.ok(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        turmaService.atualizar(id, turma);
        return ResponseEntity.ok(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        turmaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
