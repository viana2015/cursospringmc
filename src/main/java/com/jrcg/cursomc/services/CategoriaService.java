package com.jrcg.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jrcg.cursomc.domain.Categoria;
import com.jrcg.cursomc.dto.CategoriaDto;
import com.jrcg.cursomc.repositories.CategoriaRepository;
import com.jrcg.cursomc.services.exceptions.DataIntegrityException;
import com.jrcg.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria cadastrar(Categoria obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria atualizar(Categoria obj) {
		Categoria newObj = buscar(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj) ;
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			categoriaRepository.deleteById(id);	
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivél excluir uma categoria em que possui produtos.");
		}
		
	}

	public List<Categoria> buscarTodas() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDto objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
