/**
 * 
 */
package br.com.ntconsult.testeSelecao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.ntconsult.testeSelecao.domain.Line;
import br.com.ntconsult.testeSelecao.domain.enums.LineTypeEnum;

/**
 * @author tiago.marzari
 * @since 24/11/2019
 */
@Component
public class LineBuilderService {

	private static final int PLACE_BEGIN_ID = 0;
	private static final int PLACE_END_ID = 3;

	public List<Line> LineBuilder(List<String> lines) {
		List<Line> linesParse = new ArrayList<>();
                lines.stream().map((line) -> lineParser(line)).forEachOrdered((lineParsed) -> {
                    linesParse.add(lineParsed);
            });

		return linesParse;
	}

	public Line lineParser(String line) {
		return (Line) LineTypeEnum.findById(line.substring(PLACE_BEGIN_ID, PLACE_END_ID)).getConverter().parse(line.split(";"));
	}

}
