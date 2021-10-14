package entities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

	/*
	 * Enquanto o while for diferente de null, o algoritmo vai ler a pr�xima linha.
	 * Utilizando o m�todo Split, que recorta com base na virgula e guardando cada
	 * informa��o em um vetor auxiliar. Ap�s percorrer a linha inteira, ir�
	 * instanciar o cliente e em seguida adicionar ao ArrayList. No final, o
	 * algoritmo l� a pr�xima linha e repete o processo at� a pr�xima linha ser
	 * vazia.
	 */
	public List<Cliente> lerArquivo() {
		try (BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(this.path), "UTF-8"))) {

			String linha = leitor.readLine();

			while (linha != null) {

				linha = semAcento(linha);

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
	
	/*
	 * Regex para fazer a tratativa de acentos.
	 */
	public static String semAcento(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

}
