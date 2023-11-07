package com.livros.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livros.entities.livro;
import com.livros.services.livroServico;


@RestController
@RequestMapping("/livros")
public class livroController {
	private final livroServico livroServico;

	public livroController(livroServico livroServico) {
		this.livroServico = livroServico;
	}

	@GetMapping("/{id}")
	public ResponseEntity<livro> getlivro(@PathVariable Long id) {
		livro livro = ((livroServico) livroServico).getLivroById(id);
		if (livro != null) {
			return ResponseEntity.ok(livro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/home")
	public String paginaInicial() {
		return "index"; // Nome do seu arquivo HTML (sem a extensão)
	}

	@PostMapping
	public livro createlivro(@RequestBody livro livro) {
		return livroServico.saveLivro(livro);
	}
	
	//Utilizando o ResponseEntity e RequestEntity
		@GetMapping
		public ResponseEntity<List<livro>> getAlllivros(RequestEntity<Void> requestEntity) {
			String method = requestEntity.getMethod().name();
			String contentType = requestEntity.getHeaders().getContentType().toString();
			List<livro> livros = livroServico.getAllLivros();
			return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
					.body(livros);
		}
		
		@PutMapping("/{id}")
		public livro updatelivro(@PathVariable Long id, @RequestBody livro livro) {
		    return livroServico.updateLivro(id, livro);
		}
		

	@DeleteMapping("/{id}")
	public void deletelivro(@PathVariable Long id) {
		livroServico.deleteLivro(id);
	}
	
	
	
}
