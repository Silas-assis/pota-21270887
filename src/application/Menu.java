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

		// Lista de Clientes para armazenar informa��es de cada cliente.
		List<Cliente> list = new ArrayList<Cliente>();

		// Arquivo arquivoCsv = new Arquivo(path);

		try (BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

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

		String finalizarPrograma = "";

		System.out.println("*****BEM VINDO!*****");
		System.out.println("********MENU********");

		do {

			System.out.println("1) Pesquisar Cliente.");
			System.out.println("2) Finalizar Programa.");
			System.out.print("Escolha uma op��o: ");

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
	 * M�todo que verifica se o input do us�ario est� correto para finalizar o
	 * programa.
	 */
	private static String encerrarPrograma(String finalizarPrograma, Scanner inputTexto) {
		System.out.print("Digite exit para encerrar o programa: ");
		finalizarPrograma = inputTexto.nextLine();

		if (finalizarPrograma.equals("exit")) {
			System.out.println("Finalizando o programa...");
			return "exit";
		}
		System.out.println("Erro de digita��o, por favor informe 'exit' para finalizar.");
		return "";
	}

	/*
	 * M�todo para informa que o us�ario fez uma tentativa inv�lida.
	 */
	private static void opcaoInvalida() {
		System.out.println("Op��o Inv�lida! Tente novamente entre 1 e 2.");
	}

	/*
	 * M�todo para pesquisar o Cliente na lista. Primeiro � instanciado a Classe
	 * BuscaBinaria recebendo como par�metro a lista do arquivo csv. Em seguida o
	 * us�ario informa um nome que deseja pesquisar, utilizando essa vari�vel como
	 * par�metro no m�todo pessquisaBinaria. Ap�s o retorno do m�todo, � verificado
	 * se � um cliente null. Se for null informa que o cliente n�o existe, caso
	 * contr�rio � informado os dados do cliente e quantas compara��es para acha-lo.
	 */
	private static void pesquisarCliente(Scanner inputTexto, List<Cliente> list) {
		BuscaBinaria pesquisaCliente = new BuscaBinaria(list);

		System.out.print("Informe o nome do cliente desejado: ");
		String nome = inputTexto.nextLine();

		Cliente cliente = pesquisaCliente.pesquisaBinaria(nome);

		if (cliente == null) {
			System.out.println("Cliente: " + nome + " n�o existe!");
			System.out.println("N�mero de compara��es realizadas: " + pesquisaCliente.getCount());
			pularLinha();
		} else {
			System.out.println("Busca Realizada:\n" + cliente.toString());
			System.out.println("N�mero de compara��es realizadas: " + pesquisaCliente.getCount());
			pularLinha();
		}
	}

	/*
	 * M�todo para pular linha.
	 */
	private static void pularLinha() {
		System.out.println("");
	}

	/*
	 * M�todo para fechar o Scanner.
	 */
	private static void fecharScanner(Scanner inputTexto, Scanner inputNumero) {
		inputTexto.close();
		inputNumero.close();
	}
}
