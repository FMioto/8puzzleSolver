import java.util.ArrayList;
import java.util.Arrays;

public class HeuristicaBordas {

	public static final int[][] ESTADO_SOLUCAO = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

	// calcula quantas bordas erradas tem um numero
	public static int calculaBordasErradas(int numero, ArrayList<Integer> bordas) {
		int somatoriaBordas = 0;
		ArrayList<Integer> bordasSolucao = getConjuntoBordas(numero);
		for (int i = 0; i < bordasSolucao.size(); i++) {
			if (bordas.get(i) != bordasSolucao.get(i)) {
				somatoriaBordas++;
			}
		}
		return somatoriaBordas;
	}

	// Calcula quantas bordas não pertecem ao conjunto de bordas corretas,
	// independente da posicao delas
	public static int calculaQuantidadeBorda(int numero, ArrayList<Integer> bordas) {
		int quantidade = 0;
		ArrayList<Integer> bordasSolucao = getConjuntoBordas(numero);
		for (int i : bordas) {
			if (!bordasSolucao.contains(i)) {
				quantidade++;
			}
		}
		return quantidade;
	}

	// calcula o conjunto de bordas de determinado numero no estado solução
	public static ArrayList<Integer> getConjuntoBordas(int num) {
		ArrayList<Integer> conjuntoBordas = new ArrayList<>();
		ArrayList<Integer> coordenadas = getCoordenadasNumero(num);
		int linha = coordenadas.get(0);
		int coluna = coordenadas.get(1);

		// borda de cima
		try {
			conjuntoBordas.add(ESTADO_SOLUCAO[linha - 1][coluna]);
		} catch (IndexOutOfBoundsException e) {
			conjuntoBordas.add(-1);
		}

		// borda da direita
		try {
			conjuntoBordas.add(ESTADO_SOLUCAO[linha][coluna + 1]);
		} catch (IndexOutOfBoundsException e) {
			conjuntoBordas.add(-1);
		}

		// borda de baixo
		try {
			conjuntoBordas.add(ESTADO_SOLUCAO[linha + 1][coluna]);
		} catch (IndexOutOfBoundsException e) {
			conjuntoBordas.add(-1);
		}

		// borda da esquerda
		try {
			conjuntoBordas.add(ESTADO_SOLUCAO[linha][coluna - 1]);
		} catch (IndexOutOfBoundsException e) {
			conjuntoBordas.add(-1);
		}
		return conjuntoBordas;
	}

	private static ArrayList<Integer> getCoordenadasNumero(int num) {
		ArrayList<Integer> coord = new ArrayList<>();
		for (int i = 0; i < ESTADO_SOLUCAO.length; i++) {
			for (int j = 0; j < ESTADO_SOLUCAO.length; j++) {
				if (num == ESTADO_SOLUCAO[i][j]) {
					coord.add(i);
					coord.add(j);
				}
			}
		}
		return coord;
	}
}
