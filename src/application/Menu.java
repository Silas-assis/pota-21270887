package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.BuscaBinaria;
import entities.Cliente;

public class Menu {

	public static void iniciar() {
		Scanner inputTexto = new Scanner(System.in);
		Scanner inputNumero = new Scanner(System.in);

		// Caminho do arquivo csv da atividade.
		String path = "C:\\Users\\LEDcl\\eclipse-workspace\\pota-21270887\\arquivoDados.csv";

		// Lista de Clientes para armazenar informações de cada cliente.
		List<Cliente> list = new ArrayList<Cliente>();

		// Arquivo arquivoCsv = new Arquivo(path);

		try (BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

			String linha = leitor.readLine();

			/*
			 * Enquanto o while for diferente de null, o algoritmo vai ler a próxima linha.
			 * Utilizando o método Split, que recorta com base na virgula e guardando cada
			 * informação em um vetor auxiliar. Após percorrer a linha inteira, irá
			 * instanciar o cliente e em seguida adicionar ao ArrayList. No final, o
			 * algoritmo lê a próxima linha e repete o processo até a próxima linha ser
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

				linha = leitor.readLine(); // Ler a próxima linha
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		String finalizarPrograma = "";

		System.out.println("*****BEM VINDO!*****");
		System.out.println("********MENU********");

		do {

			System.out.println("1) Pesquisar Cliente.");
			System.out.println("2) Finalizar Programa.");
			System.out.print("Escolha uma opção: ");

			int escolhaMenu = 0;

			try {
				escolhaMenu = inputNumero.nextInt();
			} catch (InputMismatchException e) {
				inputNumero.nextLine();
			}

			switch (escolhaMenu) {
			case 1:
				pularLinha();
				pesquisarCliente(inputTexto, list);
				break;
			case 2:
				do {
					finalizarPrograma = encerrarPrograma(finalizarPrograma, inputTexto);
					pularLinha();
				} while (!finalizarPrograma.equals("exit"));
				break;
			default:
				opcaoInvalida();
				pularLinha();
				break;
			}
		} while (!finalizarPrograma.equals("exit"));

		fecharScanner(inputTexto, inputNumero);

		for (Cliente cliente : list) {
			System.out.println(cliente);
		}
	}

	/*
	 * Método que verifica se o input do usúario está correto para finalizar o
	 * programa.
	 */
	private static String encerrarPrograma(String finalizarPrograma, Scanner inputTexto) {
		System.out.print("Digite exit para encerrar o programa: ");
		finalizarPrograma = inputTexto.nextLine();

		if (finalizarPrograma.equals("exit")) {
			System.out.println("Finalizando o programa...");
			return "exit";
		}
		System.out.println("Erro de digitação, por favor informe 'exit' para finalizar.");
		return "";
	}

	/*
	 * Método para informa que o usúario fez uma tentativa inválida.
	 */
	private static void opcaoInvalida() {
		System.out.println("Opção Inválida! Tente novamente entre 1 e 2.");
	}

	/*
	 * Método para pesquisar o Cliente na lista. Primeiro é instanciado a Classe
	 * BuscaBinaria recebendo como parâmetro a lista do arquivo csv. Em seguida o
	 * usúario informa um nome que deseja pesquisar, utilizando essa variável como
	 * parâmetro no método pessquisaBinaria. Após o retorno do método, é verificado
	 * se é um cliente null. Se for null informa que o cliente não existe, caso
	 * contrário é informado os dados do cliente e quantas comparações para acha-lo.
	 */
	private static void pesquisarCliente(Scanner inputTexto, List<Cliente> list) {
		BuscaBinaria pesquisaCliente = new BuscaBinaria(list);

		System.out.print("Informe o nome do cliente desejado: ");
		String nome = inputTexto.nextLine();

		Cliente cliente = pesquisaCliente.pesquisaBinaria(nome);

		if (cliente == null) {
			System.out.println("Cliente: " + nome + " não existe!");
			System.out.println("Número de comparações realizadas: " + pesquisaCliente.getCount());
			pularLinha();
		} else {
			System.out.println("Busca Realizada:\n" + cliente.toString());
			System.out.println("Número de comparações realizadas: " + pesquisaCliente.getCount());
			pularLinha();
		}
	}

	/*
	 * Método para pular linha.
	 */
	private static void pularLinha() {
		System.out.println("");
	}

	/*
	 * Método para fechar o Scanner.
	 */
	private static void fecharScanner(Scanner inputTexto, Scanner inputNumero) {
		inputTexto.close();
		inputNumero.close();
	}
}
