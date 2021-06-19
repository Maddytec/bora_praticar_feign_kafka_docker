package br.com.maddytec.cliente.controller;

import br.com.maddytec.cliente.model.Endereco;
import br.com.maddytec.cliente.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco){
        enderecoService.save(endereco);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getListaEndereco(){
        List<Endereco> listaEndereco = enderecoService.getListaEndereco();
        return ResponseEntity.ok(listaEndereco);
    }

}
