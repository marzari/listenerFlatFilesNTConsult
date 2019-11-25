package br.com.totvs.testeSelecao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tiago marzari
 * @since 24/11/2019
 */
@Getter
@Setter
@AllArgsConstructor
public class LineBuyerFactory extends Line{
	
	private String cnpj;
	private String name;
	private String businessArea;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LineBuyerFactory [cnpj=");
		builder.append(cnpj);
		builder.append(", name=");
		builder.append(name);
		builder.append(", businessArea=");
		builder.append(businessArea);
		builder.append("]");
		return builder.toString();
	}

}
