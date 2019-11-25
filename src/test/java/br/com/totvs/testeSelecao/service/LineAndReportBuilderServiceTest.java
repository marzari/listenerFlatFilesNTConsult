/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.totvs.testeSelecao.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.totvs.testeSelecao.domain.CalcReport;
import br.com.totvs.testeSelecao.domain.Line;
import br.com.totvs.testeSelecao.service.LineBuilderService;
import br.com.totvs.testeSelecao.service.ReportBuilderService;

import static org.junit.Assert.*;

/**
 *
 * @author tiago.marzari
 */
public class LineAndReportBuilderServiceTest {

	public LineAndReportBuilderServiceTest() {
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
	 * Test of LineBuilder method, of class LineBuilderService and
	 * ReportBuilderService, dataProcess method .
	 * 
	 * 001;1234567891234;Pedro;50000
	 * 001;3245678865434;Paulo;40000.99
	 * 002;2345675434544345;Jose da Silva;Rural
	 * 002;2345675433444345;Eduardo Pereira;Rural
	 * 003;10;[1-10-100,2-30-2.50,3-40-3.10];Pedro
	 * 003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo
	 */
	@Test
	public void testLineAndReportBuilder() {
		System.out.println("LineBuilder/ReportBuilder TEST");
		List<String> lines = new ArrayList<>();
		lines.add("001;1234567891234;Pedro;50000");
		lines.add("001;3245678865434;Paulo;40000.99");
		lines.add("002;2345675434544345;Jose da Silva;Rural");
		lines.add("002;2345675433444345;Eduardo Pereira;Rural");
		lines.add("003;10;[1-10-100,2-30-2.50,3-40-3.10];Pedro");
		lines.add("003;08;[1-34-10,2-33-1.50,3-40-0.10];Paulo");
		LineBuilderService instance = new LineBuilderService();
		List<Line> lineBuilder = instance.LineBuilder(lines);
		// LINHAS ARQUIVO
		assertEquals(6, lineBuilder.size());
		ReportBuilderService relatorio = new ReportBuilderService();
		CalcReport dataProcess = relatorio.dataProcess(lineBuilder);
		// ID da venda mais cara
		assertEquals(10, dataProcess.getIdMmostExpensiveSale());
		// Quantidade de clientes no arquivo de entrada
		assertEquals(2, dataProcess.getSumBuyer());
		// Quantidade de vendedor no arquivo de entrada
		assertEquals(2, dataProcess.getSumSaleman());
		// O pior vendedor
		assertEquals("Paulo", dataProcess.getWorstSalesman().getName());
	}

}
