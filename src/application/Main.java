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

import entities.Cliente;

public class Main {

	public static void main(String[] args) {
		
		//Caminho do arquivo csv da atividade.
		String path = "C:\\Users\\LEDcl\\eclipse-workspace\\pota-21270887\\arquivoDados.csv";  
		
		//Lista de Clientes para armazenar informações de cada cliente.
		List<Cliente> list = new ArrayList<Cliente>();
		
		try(BufferedReader leitor = new BufferedReader(new FileReader(path))){
			
			String linha = leitor.readLine();
			
			/*
			 * Enquanto o while for diferente de null, o algoritmo vai ler a próxima linha. Utilizando o 
			 * método Split, que recortar com base na virgula e guardando cada informação em um vetor auxiliar.
			 * Após percorrer a linha inteira, irá instanciar o cliente e em seguida adicionar ao ArrayList.
			 * No final, o algoritmo lê a próxima linha e repete o processo até a próxima linha ser vazia. 
			 */
			while(linha != null) {
				
				String [] vetor = linha.split(",");
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
				
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("O arquivo contém: " + list.size() + " registros de clientes.");
		
		/*System.out.println("Lista de Pessoas: ");
		for (Cliente c : list) {
			System.out.println(c);
		}*/
	}
}
