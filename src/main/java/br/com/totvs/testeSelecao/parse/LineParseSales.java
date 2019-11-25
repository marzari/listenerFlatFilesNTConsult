/**
 * 
 */
package br.com.totvs.testeSelecao.parse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.totvs.testeSelecao.config.constants.PositionFileConfig;
import br.com.totvs.testeSelecao.domain.Item;
import br.com.totvs.testeSelecao.domain.Line;
import br.com.totvs.testeSelecao.domain.LineSalesFactory;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
@Component
public class LineParseSales implements LineParse {

	@Override
	public Line parse(String[] data) {
		String saleman = data[PositionFileConfig.PLACE_SALE_SALESMAN];
		String idSale = data[PositionFileConfig.PLACE_SALE_ID];
		String itens = data[PositionFileConfig.PLACE_SALE_ITENS].replace("[", "").replace("]", "");
		List<String> itensSplit = Arrays.asList(itens.split(","));

		ArrayList<Item> itemList = new ArrayList<>();

		itensSplit.stream().map((string) -> string.split("-"))
				.map((stringSplit) -> 
				new Item(stringSplit[PositionFileConfig.PLACE_SALE_ITEM_ID],
						stringSplit[PositionFileConfig.PLACE_SALE_ITEM_QUANTITY],
						new BigDecimal(stringSplit[PositionFileConfig.PLACE_SALE_ITEM_PRICE])))
				.forEachOrdered((item) -> {
					itemList.add(item);
				});

		return new LineSalesFactory(idSale, itemList, saleman);
	}

}
