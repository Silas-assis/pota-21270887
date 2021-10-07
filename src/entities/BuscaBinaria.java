package entities;

import java.util.ArrayList;
import java.util.List;

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

	public Cliente pesquisaBinaria(String nome) {
		int inicio, fim, meio;

		inicio = 0;
		fim = list.size() - 1;
		count = 0;

		while (inicio <= fim) {
			meio = (inicio + fim) / 2;

			Cliente cliente = list.get(meio);

			count++;
			if (cliente.getNome().equalsIgnoreCase(nome)) {
				return cliente;
			}

			count++;
			if (cliente.getNome().compareTo(nome) > 0) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}
		return null;
	}

}
