package application;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import entities.Arquivo;
import entities.BuscaBinaria;
import entities.Cliente;

public class Menu {

	public static void iniciar() {
		Scanner inputTexto = new Scanner(System.in);
		Scanner inputNumero = new Scanner(System.in);

		// Caminho do arquivo csv da atividade.
		String path = "C:\\Users\\LEDcl\\eclipse-workspace\\pota-21270887\\arquivoDados.csv";

		List<Cliente> list = new ArrayList<Cliente>();

		Arquivo arquivoCsv = new Arquivo(path);

		list = arquivoCsv.lerArquivo();

		String finalizarPrograma = "";

		System.out.println("*****BEM VINDO!*****");
		System.out.println("********MENU********");

		do {

			System.out.println("1) Pesquisar Um Cliente.");
			System.out.println("2) Pesquisar Todos os Clientes.");
			System.out.println("3) Finalizar Programa.");
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
				pularLinha();
				informarTodosClientes(list);
				pularLinha();
				break;
			case 3:
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

	}

	private static void informarTodosClientes(List<Cliente> list) {
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

		nome = semAcento(nome);

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

	/*
	 * M�todo para remover todos os acentos.
	 */
	public static String semAcento(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}
