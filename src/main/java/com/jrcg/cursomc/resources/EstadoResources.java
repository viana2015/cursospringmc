package com.jrcg.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jrcg.cursomc.domain.Cidade;
import com.jrcg.cursomc.domain.Estado;
import com.jrcg.cursomc.dto.CidadeDTO;
import com.jrcg.cursomc.dto.EstadoDTO;
import com.jrcg.cursomc.services.CidadeService;
import com.jrcg.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResources {

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity <List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.buscarTodas();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity <List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
