package br.com.springboot.sample_crud_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.sample_crud_springboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> pesquisarNome(String nome);
	
	/*
	@Query(value = "select u from Usuario u where upper(trim(u.curso)) like %?1%")
	List<Usuario> buscarPorCurso(String curso);*/

}