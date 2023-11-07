package com.livros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.livros.entities.livro;
import com.livros.repositories.livroRepositorio;

@Service
public class livroServico {
    
    private final livroRepositorio livroRepositorio;
    
    public livroServico(livroRepositorio livroRepositorio) {
    	this.livroRepositorio = livroRepositorio;
    }
    
    public livro getLivroById(Long id) {
		return livroRepositorio.findById(id).orElse(null);
	}
    
    public livro saveLivro(livro Livro){
    	return livroRepositorio.save(Livro);
    }  
    
    public List<livro> getAllLivros(){
    	return livroRepositorio.findAll();
    }

	public void deleteLivro(Long id) {
		livroRepositorio.deleteById(id);
		
	}
	
	// fazendo o update do Livro com o optional
		public livro updateLivro(Long id, livro novoLivro) {
	        Optional<livro> LivroOptional = livroRepositorio.findById(id);
	        if (LivroOptional.isPresent()) {
	        	livro LivroExistente = LivroOptional.get();
	           	LivroExistente.setdescricao(novoLivro.getdescricao());
	        	LivroExistente.setisbn(novoLivro.getisbn());          
	            return livroRepositorio.save(LivroExistente); 
	        } else {
	            return null; 
	        }
	    }
	
    
    
    
    


}