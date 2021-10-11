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

	public Cliente pesquisaBinaria(String nome) {
		int inicio, fim, meio;

		Collator collator = Collator.getInstance(new Locale("pt", "BR"));
		collator.setStrength(Collator.PRIMARY);

		inicio = 0;
		fim = list.size() - 1;
		count = 0;

		while (inicio <= fim) {
			meio = (inicio + fim) / 2;

			Cliente cliente = list.get(meio);

			count++;
			if (collator.compare(cliente.getNome(), nome) == 0) {
				return cliente;
			}

			count++;
			if (collator.compare(cliente.getNome(), nome) > 0) {
				fim = meio - 1;
			} else {
				inicio = meio + 1;
			}
		}
		return null;
	}

}
