package br.com.springboot.sample_crud_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.springboot.sample_crud_springboot.model.Usuario;
import br.com.springboot.sample_crud_springboot.repository.UsuarioRepository;


@RestController
public class UsuariosController {

	@Autowired
	private UsuarioRepository usuariosRepository;

	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listaUsuarios(){

		//consulta no banco de dados
		List<Usuario> usuarios = usuariosRepository.findAll();

		//retorno em JSON
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);	 	
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){

		Usuario user = usuariosRepository.save(usuario);

		return new ResponseEntity<Usuario> (user, HttpStatus.CREATED);    	 	
	}

	@DeleteMapping(value = "deletar")
	@ResponseBody
	public ResponseEntity<String> deletar (@RequestParam Long iduser){

		usuariosRepository.deleteById(iduser);

		return new ResponseEntity<String>("Usuário deletado com sucesso.", HttpStatus.OK);
	}

	@GetMapping(value = "editar")
	@ResponseBody
	public ResponseEntity<Usuario> buscaruserid (@RequestParam(name="iduser") Long iduser){

		Usuario usuarios = usuariosRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar (@RequestBody Usuario usuarios){

		if(usuarios.getId() == null) {
			return new ResponseEntity<String>("ID não informado para atualização.", HttpStatus.OK);
		}

		Usuario usuario = usuariosRepository.saveAndFlush(usuarios);

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping(value = "buscarpornome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> pesquisarNome (@RequestParam(name = "name") String name){

		List<Usuario> usuarios = usuariosRepository.pesquisarNome(name.trim().toUpperCase());	

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}   
}
