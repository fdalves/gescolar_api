package br.com.gescolar.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.AtivarMatrciulaDTO;
import br.com.gescolar.dto.BoletoDTO;
import br.com.gescolar.dto.CobrancaDTO;
import br.com.gescolar.dto.ContratoDTO;
import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.service.MatriculaIniService;

@RestController
@RequestMapping("/matricula")
public class MatriculaIniResource {

	@Autowired
	private MatriculaIniService matriculaIniService; 
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<MatriculaDTO> criar(@Valid @RequestBody MatriculaDTO matriculaIni, HttpServletResponse response) {
		MatriculaDTO matriculaIniSalva = matriculaIniService.salvar(matriculaIni);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, Long.valueOf(matriculaIniSalva.getCodigo())));
		return ResponseEntity.status(HttpStatus.CREATED).body(matriculaIniSalva);
	}
	
	
	@PostMapping("/ativar")
	public  ResponseEntity<Object> ativar(@Valid @RequestBody AtivarMatrciulaDTO ativarMatrciulaDTO) {
		 matriculaIniService.ativarMatrciula(ativarMatrciulaDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<MatriculaDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		MatriculaDTO matriculaDTO = matriculaIniService.buscarPeloCodigo(codigo);
		return matriculaDTO != null ? ResponseEntity.ok(matriculaDTO) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<MatriculaDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody MatriculaDTO matriculaDTO) {
		MatriculaDTO dto  = matriculaIniService.atualizar(codigo, matriculaDTO);
		return ResponseEntity.ok(dto);
	}

	
	@GetMapping
	public Page<MatriculaDTO> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
	        Pageable pageable) {
		return matriculaIniService.findByNomeContaining(nome, pageable);
	}
	
	
	@GetMapping("/boletos/{codigo}")
	public ResponseEntity<List<BoletoDTO>> buscarBoletos(@PathVariable Long codigo) {
		List<BoletoDTO> boletoDTOs = matriculaIniService.buscarBoletos(codigo);
		return boletoDTOs != null ? ResponseEntity.ok(boletoDTOs) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/matriculasEmAtraso")
	public ResponseEntity<List<CobrancaDTO>> matriculasEmAtraso() {
		return ResponseEntity.ok(CobrancaDTO.generateMook()); 
	}
	
	
	@GetMapping("/downloadBoleto/{codigo}")
    @ResponseBody
    public ResponseEntity<Resource> downloadBoleto(@PathVariable Long codigo) {
		Resource file = matriculaIniService.downloadBoleto(codigo);
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, "application/x-pdf")
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
	
	
	@GetMapping("/downloadContrato/{codigo}")
    @ResponseBody
    public ResponseEntity<Resource> downloadContrato(@PathVariable Long codigo) {
		Resource file = matriculaIniService.downloadContrato(codigo);
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
	
	
	@GetMapping("/contratos")
	public ResponseEntity<List<ContratoDTO>> buscarContratos() {
		List<ContratoDTO> contratoDTOs = matriculaIniService.buscarContratos();
		return contratoDTOs != null ? ResponseEntity.ok(contratoDTOs) : ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/downloadCnab/{codigo}")
    @ResponseBody
    public ResponseEntity<Resource> downloadCnab(@PathVariable Long codigo) {
		Resource file = matriculaIniService.downloadCnab(codigo);
        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
	
}
