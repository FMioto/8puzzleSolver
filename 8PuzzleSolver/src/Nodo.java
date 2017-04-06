import java.util.ArrayList;
import java.util.Arrays;

public class Nodo {

	int[][] estado;
	Nodo pai;
	String passo;
	int custoHeuristicaBordas;
	int custoTotalCaminho = 0;
	int tamanhoCaminho = 0;

	private static final ArrayList<Integer> BORDAS_POSICAO_0 = new ArrayList<>(Arrays.asList(-1, 9, 9, -1));
	private static final ArrayList<Integer> BORDAS_POSICAO_1 = new ArrayList<>(Arrays.asList(-1, 9, 9, 9));
	private static final ArrayList<Integer> BORDAS_POSICAO_2 = new ArrayList<>(Arrays.asList(-1, -1, 9, 9));
	private static final ArrayList<Integer> BORDAS_POSICAO_3 = new ArrayList<>(Arrays.asList(9, 9, 9, -1));
	private static final ArrayList<Integer> BORDAS_POSICAO_4 = new ArrayList<>(Arrays.asList(9, 9, 9, 9));
	private static final ArrayList<Integer> BORDAS_POSICAO_5 = new ArrayList<>(Arrays.asList(9, -1, 9, 9));
	private static final ArrayList<Integer> BORDAS_POSICAO_6 = new ArrayList<>(Arrays.asList(9, 9, -1, -1));
	private static final ArrayList<Integer> BORDAS_POSICAO_7 = new ArrayList<>(Arrays.asList(9, 9, -1, 9));
	private static final ArrayList<Integer> BORDAS_POSICAO_8 = new ArrayList<>(Arrays.asList(9, -1, -1, 9));

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
		if (temNumeroPosicao(linha, coluna, -1, 0)) {
			bordas.add(this.estado[linha - 1][coluna]);
		} else {
			bordas.add(-1);
		}

		// borda da direita
		if (temNumeroPosicao(linha, coluna, 0, 1))
			bordas.add(this.estado[linha][coluna + 1]);
		else
			bordas.add(-1);

		// borda de baixo
		if (temNumeroPosicao(linha, coluna, 1, 0))
			bordas.add(this.estado[linha + 1][coluna]);
		else
			bordas.add(-1);

		// borda da esquerda
		if (temNumeroPosicao(linha, coluna, 0, -1))
			bordas.add(this.estado[linha][coluna - 1]);
		else
			bordas.add(-1);

		return bordas;
	}

	/*
	 * -- Identifica o limite externo do tabuleiro em referencia a posicao dada
	 * primeiro ele tenta acessar o tablueiro em uma posicao dada: - se não cair
	 * na exceção significa que é uma posicao válida no tabuleiro - se entrar na
	 * execao significa que a posicao acessada está fora do tabuleiro
	 */
	public boolean temNumeroPosicao(int linha, int coluna, int somaLinha, int somaColuna) {
		int[][] tab = new int[3][3];
		int teste;
		if (somaLinha != 0) {
			try {
				teste = tab[linha + somaLinha][coluna];
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		} else {
			try {
				int n = tab[linha][coluna + somaColuna];
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		}
		return true;
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
	
	public boolean ehIgual(int[][] matriz){
		for(int i=0;i<this.estado.length;i++){
			for(int j=0;j<this.estado.length;j++){
				if(this.estado[i][j] != matriz[i][j]){
					return false;
				}
			}
		}
		return true;
	}
}
