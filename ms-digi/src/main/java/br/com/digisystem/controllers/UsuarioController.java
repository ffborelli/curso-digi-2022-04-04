package br.com.digisystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.services.UsuarioService;

@RestController
public class UsuarioController {
	
//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//    private int contador = 1;
	
	//private UsuarioService usuarioService = new UsuarioService();
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("usuarios")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
//		System.out.println("primeiro usuário");
//		return "um texto 2";
		
		List<UsuarioEntity> lista = this.usuarioService.getAll();
		
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		List<UsuarioDTO> listaDTO2 = lista.stream()
					.map( x -> x.toDTO() ).collect(Collectors.toList())  ;
		
		return ResponseEntity.ok().body( listaDTO );		
	}
	// usuarios/1
	// usuarios/12
	@GetMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
		
//		UsuarioEntity usuario = this.usuarioService.getOne(id);
//		return usuario;
		
		UsuarioEntity usuarioEntity = this.usuarioService.getOne(id);
		
		return ResponseEntity.ok().body( usuarioEntity.toDTO() );
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuario ) {
		
		
//		UsuarioEntity meuUsuario= new UsuarioEntity();
//		meuUsuario.setId(this.contador);
//		//meuUsuario.setNome("Fabrizio " + this.contador);
//		meuUsuario.setNome(usuario.getNome());
//		//meuUsuario.setEmail("fabrizio@grandeporte.com.br");
//		meuUsuario.setEmail(usuario.getEmail());
//		
//		System.out.println(usuario);
//		
//		listaUsuario.add(meuUsuario);
//		this.contador++;
//		
//		
//		return meuUsuario;
		
//		UsuarioEntity usuarioEntity = new UsuarioEntity();
//		usuarioEntity.setEmail( usuario.getEmail() );
//		usuarioEntity.setNome( usuario.getNome() );
//		return this.usuarioService.create(usuarioEntity);
		
		UsuarioEntity usuarioEntity = usuario.toEntity();
		
		UsuarioEntity usuarioEntitySalvo = this.usuarioService.create( usuarioEntity );
		
		return ResponseEntity.ok().body( usuarioEntitySalvo.toDTO() );
	}
	// usuarios/5
	@PatchMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable int id, 
			@RequestBody UsuarioDTO usuario) {
		
//		for (int i =0 ; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				
//				//if (usuario.getNome() != null ) {
//				this.listaUsuario.get(i).setNome( usuario.getNome() );
//				//}
//				
//				this.listaUsuario.get(i).setEmail( usuario.getEmail() ); 
//				
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
	
		
		UsuarioEntity usuarioEntitySalvo =
					this.usuarioService.update(id, usuario.toEntity() );
		
		return ResponseEntity.ok().body( usuarioEntitySalvo.toDTO() );
	}
	
	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			if (this.listaUsuario.get(i).getId() == id) {
//				this.listaUsuario.remove(i);
//			}
//		}
		
		this.usuarioService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("usuarios/get-by-nome/{nome}")
	public ResponseEntity<List<UsuarioDTO>> getByNome(@PathVariable String nome){
		
		List<UsuarioEntity> lista = this.usuarioService.getByNome(nome);
		
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
				
		return ResponseEntity.ok().body( listaDTO );
	}
	
	@PatchMapping("usuarios/update/{id}")
	public ResponseEntity<Void> updateUsuario(@PathVariable int id, 
			@RequestBody UsuarioDTO dto){
		
		this.usuarioService.updateUsuario(id, dto.getNome());
		
		return ResponseEntity.ok().build();
	}
}
