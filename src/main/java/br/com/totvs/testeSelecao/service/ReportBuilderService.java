/**
 * 
 */
package br.com.totvs.testeSelecao.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.totvs.testeSelecao.domain.CalcReport;
import br.com.totvs.testeSelecao.domain.Line;
import br.com.totvs.testeSelecao.domain.LineBuyerFactory;
import br.com.totvs.testeSelecao.domain.LineSalesFactory;
import br.com.totvs.testeSelecao.domain.LineSalesmanFactory;

/**
 * @author tiago.marzari
 * @since 24/11/2019
 * Reponsavel por construir o relatório tendo metodos para provisionar os dados de cada objeto.
 */
@Service
public class ReportBuilderService {

	public CalcReport dataProcess(List<Line> lineParsed) {
		long sumBuyers = sumBuyers(lineParsed);
		long sumSalesman = sumSalesman(lineParsed);
		long idMmostExpensiveSale = idMmostExpensiveSale(lineParsed);
		Line worstSalesman = worstSalesman(lineParsed);
		return new CalcReport(idMmostExpensiveSale, sumBuyers, sumSalesman, worstSalesman);
	}

	private long sumSalesman(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineSalesmanFactory.class::isInstance).count();
	}

	private long sumBuyers(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineBuyerFactory.class::isInstance).count();
	}

	/**
	 * @param lineParsed
	 * @return
	 * Responsável por verificar qual foi a melhor venda
	 */
	private long idMmostExpensiveSale(List<Line> lineParsed) {
		List<LineSalesFactory> sales = getSales(lineParsed);

		if (sales.isEmpty()) return 0l;

		//Retorna o id da maior venda
		return Collections.max(sales, Comparator.comparing(LineSalesFactory::getCost)).getIdSale();
	}

	private List<LineSalesFactory> getSales(List<Line> lineParsed) {
		return lineParsed.stream().filter(LineSalesFactory.class::isInstance).map(x -> (LineSalesFactory) x).collect(Collectors.toList());
	}

	
	/**
	 * @param linesParsed
	 * @return LineSalesmanFactory
	 * Responsavel por verificar o vendedor com a pior venda
	 */
	private Line worstSalesman(List<Line> linesParsed) {
		
		List<LineSalesmanFactory> salesmans = getSalesman(linesParsed);
		List<LineSalesFactory> sales = getSales(linesParsed);
		Map<LineSalesmanFactory, BigDecimal> salesBySalesman = new HashMap<>();
                
		sales.forEach((lineSalesFactory) -> {
			salesmans.stream().filter((salesman) -> (lineSalesFactory.getSalesman().equals(salesman.getName())))
					.forEachOrdered((salesman) -> {
						salesBySalesman.put(salesman, lineSalesFactory.getCost());
					});
		});
		
		//Retornando a chave da pior venda
		return Collections.min(salesBySalesman.entrySet(), Comparator.comparing(Entry::getValue)).getKey();
	}

	private List<LineSalesmanFactory> getSalesman(List<Line> linesParsed) {
		return linesParsed.stream().filter(LineSalesmanFactory.class::isInstance).map(x -> (LineSalesmanFactory) x).collect(Collectors.toList());
	}

}
