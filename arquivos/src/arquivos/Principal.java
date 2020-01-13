package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o caminho da pasta/arquivo");
		String caminho = sc.next();
		List<Produto> produtos = new ArrayList<Produto>();
		File path = new File(caminho);

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				System.out.println(line);

				String artigos[] = line.split(",");
				Produto prod = new Produto();
				prod.setNome(artigos[0]);
				prod.setPreco(Double.parseDouble(artigos[1]));
				prod.setQuantidade(Integer.parseInt(artigos[2]));

				produtos.add(prod);

				line = br.readLine();
			}
			/// home/gustavo/Documents/PESSOAL/udemy/artigos.csv

			String caminho2 = path.getParent();

			boolean success = new File(caminho2 + "/out").mkdir();
			if (success) {
				System.out.println("Pasta criada !!!" + success);
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho2 + "/out/summary.csv"))) {
				for (int x = 0; x < produtos.size(); x++) {
					bw.write(produtos.get(x).getNome() + ", "
							+ (produtos.get(x).getPreco() * produtos.get(x).getQuantidade()));
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
