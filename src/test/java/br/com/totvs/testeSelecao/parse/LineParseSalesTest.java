/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.totvs.testeSelecao.parse;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.totvs.testeSelecao.domain.LineSalesFactory;

/**
 *
 * @author tiago.marzari
 */
public class LineParseSalesTest {
    
    public LineParseSalesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of parse method, of class LineParseSales.
     * 003;10;[1-10-100,2-30-2.50,3-40-3.10];Pedro
     * [Item ID-Item Quantity-Item Price]
     */
    @Test
    public void testParse() {
        System.out.println("LineParseSales/LineSalesFactory TEST 1");
        
        String[] data = {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        
        LineParseSales instance = new LineParseSales();
        LineSalesFactory result = (LineSalesFactory) instance.parse(data);
        // ID DA VENDA
        assertEquals(10L, result.getIdSale());
        // NOME DO VENDEDOR
        assertEquals("Pedro", result.getSalesman());
        // SOMATORIO DA VENDA COM MAIOR VALOR
        assertEquals(new BigDecimal("1000"), result.getCost());
    }
    
    /**
     * Test of parse method, of class LineParseSales.
     * 003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo
     * [Item ID-Item Quantity-Item Price]
     */
    @Test
    public void testParse2() {
        System.out.println("LineParseSales/LineSalesFactory TEST 2");
        String[] data = {"003", "08","[1-34-10,2-33-1.50,3-40-0.10]", "Renato"};
        LineParseSales instance = new LineParseSales();
        LineSalesFactory result = (LineSalesFactory) instance.parse(data);
        // ID DA VENDA
        assertEquals(8L, result.getIdSale());
        // NOME DO VENDEDOR
        assertEquals("Renato", result.getSalesman());
        // SOMATORIO DA VENDA COM MAIOR VALOR
        assertEquals(new BigDecimal("340"), result.getCost());
    }
}
