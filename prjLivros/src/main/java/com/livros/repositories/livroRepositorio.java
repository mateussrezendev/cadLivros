package com.livros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livros.entities.livro;

public interface livroRepositorio extends JpaRepository<livro, Long>{
	
}
