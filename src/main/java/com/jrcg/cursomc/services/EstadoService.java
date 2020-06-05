package com.jrcg.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrcg.cursomc.domain.Estado;
import com.jrcg.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> buscarTodas() {
		return estadoRepository.findAllByOrderByNome();
	}
}
