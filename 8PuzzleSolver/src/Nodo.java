import java.util.ArrayList;
import java.util.Arrays;

public class Nodo {

	ArrayList<Integer> estado;
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

	public Nodo(ArrayList<Integer> estado) {
		this.estado = estado;
		custoHeuristicaBordas = calculeCusto(estado);
		custoTotalCaminho = custoHeuristicaBordas;
		tamanhoCaminho = 1;
	}

	public Nodo(ArrayList<Integer> estado, Nodo pai, String passo) {
		this.estado = estado;
		custoHeuristicaBordas = calculeCusto(estado);
		custoTotalCaminho = pai.custoTotalCaminho + custoHeuristicaBordas;
		this.pai = pai;
		this.passo = passo;
		tamanhoCaminho = pai.tamanhoCaminho+1;
	}

	public void setPai(Nodo pai) {
		this.pai = pai;
	}

	public void setPasso(String passo) {
		this.passo = passo;
	}

	public int calculeCusto(ArrayList<Integer> estado) {
		ArrayList<Integer> bordas;
		int custo = 0;
		for (int n : estado) {
			bordas = new ArrayList<>();
			bordas = calculaBordas(n, estado);
			custo += HeuristicaBordas.calculaBordasErradas(n, bordas);
			custo += HeuristicaBordas.calculaQuantidadeBorda(n, bordas);
		}
		return custo;
	}

	public ArrayList<Integer> calculaBordas(int number, ArrayList<Integer> estado) {
		ArrayList<Integer> bordas = new ArrayList<>();
		ArrayList<Integer> bordasPosicao = getBordasPosicao(estado.indexOf(number));

		// Calcula borda superior
		if (bordasPosicao.get(0) != -1)
			bordas.add(estado.get(estado.indexOf(number) - 3));
		else
			bordas.add(-1);

		// Calcula borda da direita
		if (bordasPosicao.get(1) != -1)
			bordas.add(estado.get(estado.indexOf(number) + 1));
		else
			bordas.add(-1);

		// Calcula borda inferior
		if (bordasPosicao.get(2) != -1)
			bordas.add(estado.get(estado.indexOf(number) + 3));
		else
			bordas.add(-1);

		// Calcula borda da esquerda
		if (bordasPosicao.get(3) != -1)
			bordas.add(estado.get(estado.indexOf(number) - 1));
		else
			bordas.add(-1);

		return bordas;
	}

	public ArrayList<Integer> getBordasPosicao(int posicao) {
		switch (posicao) {
		case 0:
			return BORDAS_POSICAO_0;
		case 1:
			return BORDAS_POSICAO_1;
		case 2:
			return BORDAS_POSICAO_2;
		case 3:
			return BORDAS_POSICAO_3;
		case 4:
			return BORDAS_POSICAO_4;
		case 5:
			return BORDAS_POSICAO_5;
		case 6:
			return BORDAS_POSICAO_6;
		case 7:
			return BORDAS_POSICAO_7;
		default:
			return BORDAS_POSICAO_8;
		}
	}
}
