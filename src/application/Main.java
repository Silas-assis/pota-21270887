/*
 * Atividade 1 - POTA
 * Grupo: Silas Lima de Assis - RA: 21270887, Gustavo Volpe Santos - RA: 21363230, 
 * Lucas Garcia M. Zampirolli - RA: 21238938, Murilo Zinezi - RA: 21272578,
 * Reinaldo Muraro Vieira Filho - RA: 21099060
 */
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.BuscaBinaria;
import entities.Cliente;

public class Main {

	public static void main(String[] args) {
		
		Scanner inputTexto = new Scanner(System.in);
		
		// Caminho do arquivo csv da atividade.
		String path = "C:\\Users\\LEDcl\\eclipse-workspace\\pota-21270887\\arquivoDados.csv";

		// Lista de Clientes para armazenar informa��es de cada cliente.
		List<Cliente> list = new ArrayList<Cliente>();

		try (BufferedReader leitor = new BufferedReader(new FileReader(path))) {

			String linha = leitor.readLine();

			/*
			 * Enquanto o while for diferente de null, o algoritmo vai ler a pr�xima linha.
			 * Utilizando o m�todo Split, que recortar com base na virgula e guardando cada
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

		//System.out.println("*****MENU*****");
		//System.out.println("1. ");
		
		
		
		//System.out.println("O arquivo cont�m: " + list.size() + " registros de clientes.");
		
		System.out.println("Informe o nome do cliente desejado:");
		String nome = inputTexto.nextLine();
		
		BuscaBinaria pesquisaCliente = new BuscaBinaria(list);
		
		Cliente cliente = pesquisaCliente.pesquisaBinaria(nome);
		
		if (cliente == null) {
			System.out.println("Cliente: " + nome + " n�o existe!.");
			System.out.println("N�mero de compara��es realizadas: " + pesquisaCliente.getCount());
		}else {
			System.out.println("Busca Realizada:\n" + cliente.toString());
			System.out.println("N�mero de compara��es realizadas: " + pesquisaCliente.getCount());
		}
	
		fecharScanner(inputTexto);
	}
	
	private static void fecharScanner(Scanner inputTexto) {
		inputTexto.close();
	}
}
