package entities;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BuscaBinaria {
	private List<Cliente> list = new ArrayList<Cliente>();
	private int count;

	public BuscaBinaria() {
	}

	public BuscaBinaria(List<Cliente> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}
	/*
	 * 	Metodo pesquisaBinaria recebe uma variavel String nome para fazer a busca
	 * dentro da Lista de Clientes. Utilizando a Classe Collator para comparação
	 * de String em 'pt' 'BR'.
	 * 	No primeiro if, é comparado se o Nome do Cliente é igual ao Nome passado por 
	 * parâmetro, se for igual o resultado será 0 e com isso retorna o Cliente achado.
	 * 	No segundo if é verificado se comparação é maior que 0, caso seja o fim é atualizado.
	 * caso seja menor, o inicio é atualizado. 
	 */
	public Cliente pesquisaBinaria(String nome) {

		Collator coll = Collator.getInstance(new Locale("pt", "BR"));

		int inicio, fim, meio;

		inicio = 0;
		fim = list.size() - 1;
		count = 0;

		while (inicio <= fim) {
			meio = (inicio + fim) / 2;

			Cliente cliente = list.get(meio);

			count++;
			if (coll.compare(cliente.getNome(), nome) == 0)  {
				return cliente;
			}

			count++;
			if (coll.compare(cliente.getNome(), nome) > 0) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}
		return null;
	}

}
