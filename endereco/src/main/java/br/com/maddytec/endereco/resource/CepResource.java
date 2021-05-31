package br.com.maddytec.endereco.resource;

import br.com.maddytec.endereco.response.EnderecoResponse;
import br.com.maddytec.endereco.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("cep")
public class CepResource {

    private final ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep(@PathVariable("cep") String cep) {
        EnderecoResponse enderecoResponse = viaCepService.obterCep(cep);
        return ResponseEntity.ok(enderecoResponse);
    }
}
