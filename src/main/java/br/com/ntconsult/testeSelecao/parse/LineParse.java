/**
 * 
 */
package br.com.ntconsult.testeSelecao.parse;

import br.com.ntconsult.testeSelecao.domain.Line;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
public interface LineParse {
	
	public abstract Line parse(String[] data);

}
