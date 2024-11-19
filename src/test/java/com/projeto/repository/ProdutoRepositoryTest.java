package com.projeto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.projeto.entities.Produto;

@DataJpaTest
class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "fone", "otima marca", 50.00);
		// When / Act
		Produto saveProduto = produtoRepository.save(produto1);

		// Then /Assert
		assertNotNull(saveProduto);
		assertTrue(saveProduto.getId() > 0);

	}

	@DisplayName("Testando GET para todos os Produtos")
	@Test
	void testGetAllRepository() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "fone", "otima marca", 50.00);

		Produto produto2 = new Produto(null, "carregador", "alta performace", 80.00);

		produtoRepository.save(produto1);
		produtoRepository.save(produto2);

		// When /Act
		List<Produto> produtoList = produtoRepository.findAll();

		// Then / Assert
		assertNotNull(produtoList);
		assertEquals(2, produtoList.size());

	}

	@DisplayName("Testando GET ById")
	@Test
	void testGetById() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "fone", "otima marca", 50.00);

		produtoRepository.save(produto1);

		// When /Act
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();

		// Then / Assert
		assertNotNull(saveProduto);
		assertEquals(produto1.getId(), saveProduto.getId());

	}

	@DisplayName("Testando Update")
	@Test
	void testUpdateProduto() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "relogio", "boa marca", 320.000);

		produtoRepository.save(produto1);

		// When /Act
		Produto saveProduto = produtoRepository.findById(produto1.getId()).get();
		produto1.setNome("mochila");
		produto1.setDescricao("boa marca");
		produto1.setPreco(20.00);

		Produto updateProduto = produtoRepository.save(saveProduto);

		// Then / Assert
		assertNotNull(updateProduto);
		assertEquals("mochila", updateProduto.getNome());
		assertEquals("boa marca", updateProduto.getDescricao());
		assertEquals(20.00, updateProduto.getPreco());
	}

	@DisplayName("Testando o Delete")
	@Test
	void testDeleteProduto() {

		// Given / Arrange
		Produto produto1 = new Produto(null, "relogio", "boa marca", 320.00);

		produtoRepository.save(produto1);

		// When /Act
		produtoRepository.deleteById(produto1.getId());

		Optional<Produto> produtoOptional = produtoRepository.findById(produto1.getId());

		// Then / Assert
		assertTrue(produtoOptional.isEmpty());
	}
}
