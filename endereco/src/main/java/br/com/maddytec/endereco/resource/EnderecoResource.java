package br.com.maddytec.endereco.resource;

import br.com.maddytec.endereco.http.EnderecoJson;
import br.com.maddytec.endereco.response.EnderecoResponse;
import br.com.maddytec.endereco.service.EnderecoService;
import br.com.maddytec.endereco.service.ViaCepService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class EnderecoResource {

    private final ViaCepService viaCepService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponse> obterCep(@PathVariable("cep") String cep) {
        EnderecoResponse enderecoResponse = viaCepService.obterCep(cep);
        return ResponseEntity.ok(enderecoResponse);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponse> enviarEndereco(@RequestBody EnderecoJson enderecoJson) throws JsonProcessingException {
        
        log.info("## Dados envidos pelo cliente: {}", enderecoJson);
                
        EnderecoResponse enderecoResponse = viaCepService.obterCep(enderecoJson.getCep());

        enderecoResponse.setComplemento(enderecoJson.getComplemento());
        enderecoResponse.setNumero(enderecoJson.getNumero());
        
        ObjectMapper objectMapper = new ObjectMapper();
        String mensagem = objectMapper.writeValueAsString(enderecoResponse);

        enderecoService.sendMessage(mensagem);
        log.info("## Endereço retornado pela API de CEP: {}", enderecoResponse);

        return ResponseEntity.ok(enderecoResponse);
    }
}
