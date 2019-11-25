/**
 * 
 */
package br.com.totvs.testeSelecao.parse;

import br.com.totvs.testeSelecao.domain.Line;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
public interface LineParse {
	
	public abstract Line parse(String[] data);

}
