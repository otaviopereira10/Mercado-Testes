package com.projeto.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProdutoTest {

private Produto produto;
	
	@BeforeEach
	void setUp() {
		produto = new Produto(1L, "Luizão Régua", "Luiz charmoso", 33.0);
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo ID")
	void testId() {
		//Act
		produto.setId(1L);
		//Assert
		assertEquals(1L, produto.getId());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo nome")
	void testNome() {
		//Act
		produto.setNome("Luizão Régua");
		//Assert
		assertEquals("Luizão Régua", produto.getNome());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo e-mail")
	void testDescricao() {
		//Act
		produto.setDescricao("Luiz charmoso");
		//Assert
		assertEquals("Luiz charmoso", produto.getDescricao());
	}
	
	@Test
	@DisplayName("Testando o getter e setter do campo telefone")
	void testP() {
		//Act
		produto.setPreco(33.0);
		//Assert
		assertEquals(33.0, produto.getPreco());
	}
	
	@Test
	@DisplayName("Testando o construtor com todos os argumentos")
	void testConstrutorAll() {
		//Act
		Produto novoProduto = new Produto(2L, "Luizão Urubu", "Luizão bombom", 44.0);
		//Assertion
		assertAll("novoProduto", 
				()-> assertEquals(2L, novoProduto.getId()),
				()-> assertEquals("Luizão Urubu", novoProduto.getNome()),
				()-> assertEquals("Luizão bombom", novoProduto.getDescricao()),
				()-> assertEquals(44.0, novoProduto.getPreco()));
		
	}

}
