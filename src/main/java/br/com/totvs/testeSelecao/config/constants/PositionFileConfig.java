/**
 * 
 */
package br.com.totvs.testeSelecao.config.constants;

/**
 * @author tiago marzari
 *
 */
public class PositionFileConfig {

	public static final Integer PLACE_SALESMAN_CPF = 1;
	public static final Integer PLACE_SALESMAN_NAME = 2;
	public static final Integer PLACE_SALESMAN_SALARY = 3;

	public static final Integer PLACE_BUYER_CNPJ = 1;
	public static final Integer PLACE_BUYER_NAME = 2;
	public static final Integer PLACE_BUYER_BUSINESS_AREA = 3;
	
	//(0	;	1			;	2			;	3	
	//(003	;	ID da venda	;	[items]		;	Nome do Vendedor)
	public static final Integer PLACE_SALE_ID = 1;
	public static final Integer PLACE_SALE_ITENS = 2;
	public static final Integer PLACE_SALE_SALESMAN = 3;
	
	//[0	,	1		,	2			]
	//[id	,	preço	,	quantidade	]
	public static final Integer PLACE_SALE_ITEM_ID = 0;
	public static final Integer PLACE_SALE_ITEM_QUANTITY = 1;
	public static final Integer PLACE_SALE_ITEM_PRICE = 2;


}
