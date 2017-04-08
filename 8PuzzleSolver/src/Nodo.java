import java.util.ArrayList;
import java.util.Arrays;

public class Nodo {

	int[][] estado;
	Nodo pai;
	String passo;
	int custoHeuristicaBordas;
	int custoTotalCaminho = 0;
	int tamanhoCaminho = 0;

	public Nodo(int[][] estado) {
		this.estado = estado;
		custoHeuristicaBordas = calculeCusto(estado);
		custoTotalCaminho = custoHeuristicaBordas;
		tamanhoCaminho = 1;
	}

	public Nodo(int[][] estado, Nodo pai, String passo) {
		this.estado = estado;
		custoHeuristicaBordas = calculeCusto(estado);
		custoTotalCaminho = pai.custoTotalCaminho + custoHeuristicaBordas;
		this.pai = pai;
		this.passo = passo;
		tamanhoCaminho = pai.tamanhoCaminho + 1;
	}

	public void setPai(Nodo pai) {
		this.pai = pai;
	}

	public void setPasso(String passo) {
		this.passo = passo;
	}

	public int calculeCusto(int[][] estado) {
		ArrayList<Integer> bordas;
		int custo = 0;
		for (int linha = 0; linha < estado.length; linha++) {
			for (int coluna = 0; coluna < estado.length; coluna++) {
				int n = estado[linha][coluna];
				bordas = new ArrayList<>();
				bordas = getBordasPosicao(getCoordenada(n));
				custo += HeuristicaBordas.calculaBordasErradas(n, bordas);
				custo += HeuristicaBordas.calculaQuantidadeBorda(n, bordas);
			}
		}
		return custo;
	}

	public ArrayList<Integer> getCoordenada(int number) {
		ArrayList<Integer> coordenadas = new ArrayList<>();
		for (int i = 0; i < this.estado.length; i++) {
			for (int j = 0; j < this.estado.length; j++) {
				if (this.estado[i][j] == number) {
					coordenadas.add(i);
					coordenadas.add(j);
					break;
				}
			}
		}
		return coordenadas;
	}

	// retorna as bordas de uma posicao dada
	public ArrayList<Integer> getBordasPosicao(ArrayList<Integer> posicao) {
		int linha = posicao.get(0);
		int coluna = posicao.get(1);
		ArrayList<Integer> bordas = new ArrayList<>();

		// borda de cima
		if (linha > 0)
			bordas.add(this.estado[linha - 1][coluna]);
		else
			bordas.add(-1);

		// borda da direita
		if (coluna < 2)
			bordas.add(this.estado[linha][coluna + 1]);
		else
			bordas.add(-1);

		// borda de baixo
		if (linha < 2)
			bordas.add(this.estado[linha + 1][coluna]);
		else
			bordas.add(-1);

		// borda da esquerda
		if (coluna > 0)
			bordas.add(this.estado[linha][coluna - 1]);
		else
			bordas.add(-1);

		return bordas;
	}

	public int[][] getCopia() {
		int[][] copia = new int[3][3];
		for (int i = 0; i < this.estado.length; i++) {
			for (int j = 0; j < this.estado.length; j++) {
				copia[i][j] = this.estado[i][j];
			}
		}
		return copia;
	}

	public boolean ehIgual(int[][] matriz) {
		for (int i = 0; i < this.estado.length; i++) {
			for (int j = 0; j < this.estado.length; j++) {
				if (this.estado[i][j] != matriz[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
