package com.projeto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.projeto.entities.Produto;
import com.projeto.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setUp() {
        produtoRepository.deleteAll(); // Limpa o banco de dados antes de cada teste
    }

    @DisplayName("Testando salvar Produto")
    @Test
    void testSalvarProduto() {
        Produto produto = new Produto(null, "Televisão", "Boa marca", 800.00);

        Produto resultado = produtoService.salvarProduto(produto);

        assertNotNull(resultado);
        assertEquals("Televisão", resultado.getNome());
        assertEquals("Boa marca", resultado.getDescricao());
        assertEquals(800.00, resultado.getPreco());
        assertTrue(resultado.getId() > 0);
    }

    @DisplayName("Testando listar todos os Produtos")
    @Test
    void testListarTodos() {
        Produto produto1 = new Produto(null, "Televisão", "Boa marca", 800.00);
        Produto produto2 = new Produto(null, "Tênis", "Bom preço", 300.00);

        produtoService.salvarProduto(produto1);
        produtoService.salvarProduto(produto2);

        List<Produto> resultado = produtoService.getAllProduto();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }

    @DisplayName("Testando buscar Produto por ID")
    @Test
    void testBuscarPorId() {
        Produto produto = new Produto(null, "Notebook", "Alto desempenho", 5000.00);

        Produto salvo = produtoService.salvarProduto(produto);
        Produto resultado = produtoService.getProdutoById(salvo.getId());

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
        assertEquals("Alto desempenho", resultado.getDescricao());
        assertEquals(5000.00, resultado.getPreco());
    }

    @DisplayName("Testando atualizar Produto")
    @Test
    void testAtualizarProduto() {
        Produto produto = new Produto(null, "Cadeira", "Confortável", 200.00);
        Produto salvo = produtoService.salvarProduto(produto);

        Produto updatedProduto = new Produto(null, "Cadeira Gamer", "Ergonomicamente projetada", 500.00);

        Produto atualizado = produtoService.updateProduto(salvo.getId(), updatedProduto);

        assertNotNull(atualizado);
        assertEquals("Cadeira Gamer", atualizado.getNome());
        assertEquals("Ergonomicamente projetada", atualizado.getDescricao());
        assertEquals(500.00, atualizado.getPreco());
    }

    @DisplayName("Testando deletar Produto por ID")
    @Test
    void testDeletarProduto() {
        Produto produto = new Produto(null, "Mesa", "Mesa de jantar", 1500.00);

        Produto salvo = produtoService.salvarProduto(produto);
        produtoService.deleteProduto(salvo.getId());

        Produto resultado = produtoService.getProdutoById(salvo.getId());
    }
}
