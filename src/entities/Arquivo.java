package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	private String path;
	private List<Cliente> list = new ArrayList<Cliente>();

	public Arquivo() {
	}

	public Arquivo(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Cliente> lerArquivo(String path) {
		try (BufferedReader leitor = new BufferedReader(new FileReader(path))) {

			String linha = leitor.readLine();

			/*
			 * Enquanto o while for diferente de null, o algoritmo vai ler a pr�xima linha.
			 * Utilizando o m�todo Split, que recorta com base na virgula e guardando cada
			 * informa��o em um vetor auxiliar. Ap�s percorrer a linha inteira, ir�
			 * instanciar o cliente e em seguida adicionar ao ArrayList. No final, o
			 * algoritmo l� a pr�xima linha e repete o processo at� a pr�xima linha ser
			 * vazia.
			 */
			while (linha != null) {

				String[] vetor = linha.split(",");
				String nome = vetor[0];
				String sexo = vetor[1];
				String endereco = vetor[2];
				String cidade = vetor[3];
				String email = vetor[4];
				String telefone = vetor[5];
				String idade = vetor[6];

				Cliente cliente = new Cliente(nome, sexo, endereco, cidade, email, telefone, idade);
				list.add(cliente);

				linha = leitor.readLine(); // Ler a pr�xima linha
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return this.list;
	}
}
