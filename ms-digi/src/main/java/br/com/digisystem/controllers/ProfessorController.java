package br.com.digisystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.services.ProfessorService;

@RestController
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("professores")
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		List<ProfessorEntity> lista = this.professorService.getAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		// mais difícil
		for(int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		// mais moderno
		List<ProfessorDTO> listaDTO2 = lista.stream().map( x -> x.toDTO() )
					.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDTO2);
	}
	
	@GetMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id) {
		return ResponseEntity.ok().body( this.professorService.getOne(id).toDTO() );
	}
	
	@PostMapping("professores")
	public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professorDTO) {
		return ResponseEntity.ok().body( 
				this.professorService.save( professorDTO.toEntity() ).toDTO() 
			);
	}
	
	@PatchMapping("professores/{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, 
			@RequestBody ProfessorDTO professorDTO) {
		return ResponseEntity.ok().body( 
				this.professorService.update(id, professorDTO.toEntity()).toDTO()
				);
	}
	
	@DeleteMapping("professores/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		this.professorService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}
