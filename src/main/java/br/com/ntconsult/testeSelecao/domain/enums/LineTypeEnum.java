package br.com.ntconsult.testeSelecao.domain.enums;

import java.util.Arrays;
import java.util.List;

import br.com.ntconsult.testeSelecao.parse.LineParse;
import br.com.ntconsult.testeSelecao.parse.LineParseBuyer;
import br.com.ntconsult.testeSelecao.parse.LineParseSaleman;
import br.com.ntconsult.testeSelecao.parse.LineParseSales;

/**
 * @author tiago.marzari
 * @since 24/11/2019
 * 
 */

public enum LineTypeEnum {

	SALEMAN("001", new LineParseSaleman()), 
	BUYER("002", new LineParseBuyer()), 
	SALE("003", new LineParseSales());

	private final String lineId;
	private final LineParse parse;

	private LineTypeEnum(String lineId, LineParse parse) {
		this.lineId = lineId;
		this.parse = parse;
	}

	public LineParse getConverter() {
		return parse;
	}
	
	public static LineTypeEnum findById(String id) {
		List<LineTypeEnum> kind = Arrays.asList(values());
		for (LineTypeEnum lineTypeEnum : kind) {
			if(lineTypeEnum.lineId.equals(id)) {
				return lineTypeEnum;
			}
		}
		return null;
	}

}
