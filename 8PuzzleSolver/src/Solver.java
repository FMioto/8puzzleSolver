import java.util.ArrayList;

public class Solver {

	ArrayList<Nodo> fronteira;
	ArrayList<Nodo> nodosVisitados;
	Nodo nodoAtual;
	ArrayList<Integer> estadoSolucao;

	public Solver(ArrayList<Integer> estadoInicial) {
		nodoAtual = new Nodo(estadoInicial);
		estadoSolucao = new ArrayList<>();
		estadoSolucao.add(1);
		estadoSolucao.add(2);
		estadoSolucao.add(3);
		estadoSolucao.add(4);
		estadoSolucao.add(5);
		estadoSolucao.add(6);
		estadoSolucao.add(7);
		estadoSolucao.add(8);
		estadoSolucao.add(0);
		fronteira = new ArrayList<>();
		nodosVisitados = new ArrayList<>();
	}

	public ArrayList<String> solvePuzzle() {
		while (!isNodoSolucao()) {
			Nodo visitado = isNodoVisitado(nodoAtual);
			if (visitado != null && nodoAtual.custo_total_caminho > visitado.custo_total_caminho) {
				nodosVisitados.remove(visitado);
				nodosVisitados.add(nodoAtual);
			} else {
				ArrayList<Nodo> filhos = buscaFilhos(nodoAtual);
				for (Nodo filho : filhos) {
					if (!isFilhoNaFronteira(filho)) {
						fronteira.add(filho);
					}
				}
			}
			fronteira.remove(nodoAtual);
			nodosVisitados.add(nodoAtual);
			nodoAtual = buscaNodoFronteira();
			System.out.println("Nodos na Fronteira: " + fronteira.size());
		}
		// Calcula caminho a partir do nodoAtual
		System.out.println("Solu��o encontrada!");
		return geraCaminhoSolucao(nodoAtual);
	}

	private boolean isFilhoNaFronteira(Nodo filho) {
		boolean isFilhoNaFronteira = false;
		for (Nodo n : fronteira) {
			if (filho.estado.equals(n.estado))
				isFilhoNaFronteira = true;
		}
		return isFilhoNaFronteira;
	}

	public boolean isNodoSolucao() {
		return nodoAtual.estado.equals(estadoSolucao);
	}

	public Nodo isNodoVisitado(Nodo nodo) {
		for (Nodo n : nodosVisitados) {
			if (n.estado.equals(nodo.estado)) {
				return n;
			}
		}
		return null;
	}

	public Nodo buscaNodoFronteira() {
		Nodo maisBarato = fronteira.get(0);
		for (Nodo n : fronteira) {
			if (n.custo_total_caminho > maisBarato.custo_total_caminho) {
				maisBarato = n;
			}
		}
		return maisBarato;
	}

	public ArrayList<String> geraCaminhoSolucao(Nodo nodoAtual) {
		Nodo nodo = nodoAtual;
		ArrayList<String> solucao = new ArrayList<>();
		while (nodo.pai != null) {
			solucao.add(nodo.passo);
			nodo = nodo.pai;
		}
		return new ArrayList<>();
	}

	public ArrayList<Nodo> buscaFilhos(Nodo pai) {
		ArrayList<Nodo> filhos = new ArrayList<>();
		switch (pai.estado.indexOf(0)) {
		case 0:
			filhos.add(geraFilhoPassoEsquerda(pai, 0));
			filhos.add(geraFilhoPassoCima(pai, 0));
			return filhos;
		case 1:
			filhos.add(geraFilhoPassoEsquerda(pai, 1));
			filhos.add(geraFilhoPassoCima(pai, 1));
			filhos.add(geraFilhoPassoDireita(pai, 1));
			return filhos;
		case 2:
			filhos.add(geraFilhoPassoCima(pai, 2));
			filhos.add(geraFilhoPassoDireita(pai, 2));
			return filhos;
		case 3:
			filhos.add(geraFilhoPassoEsquerda(pai, 3));
			filhos.add(geraFilhoPassoCima(pai, 3));
			filhos.add(geraFilhoPassoBaixo(pai, 3));
			return filhos;
		case 4:
			filhos.add(geraFilhoPassoEsquerda(pai, 4));
			filhos.add(geraFilhoPassoCima(pai, 4));
			filhos.add(geraFilhoPassoDireita(pai, 4));
			filhos.add(geraFilhoPassoBaixo(pai, 4));
			return filhos;
		case 5:
			filhos.add(geraFilhoPassoCima(pai, 5));
			filhos.add(geraFilhoPassoDireita(pai, 5));
			filhos.add(geraFilhoPassoBaixo(pai, 5));
			return filhos;
		case 6:
			filhos.add(geraFilhoPassoEsquerda(pai, 6));
			filhos.add(geraFilhoPassoBaixo(pai, 6));
			return filhos;
		case 7:
			filhos.add(geraFilhoPassoEsquerda(pai, 7));
			filhos.add(geraFilhoPassoDireita(pai, 7));
			filhos.add(geraFilhoPassoBaixo(pai, 7));
			return filhos;
		case 8:
			filhos.add(geraFilhoPassoDireita(pai, 8));
			filhos.add(geraFilhoPassoBaixo(pai, 8));
			return filhos;
		default:
			return null;
		}
	}

	public Nodo geraFilhoPassoEsquerda(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pessaADireita = estadoFilho.get(indexPecaVazia + 1);
		estadoFilho.set(indexPecaVazia + 1, 0);
		estadoFilho.set(indexPecaVazia, pessaADireita);

		return new Nodo(estadoFilho, pai, "Esquerda ");
	}

	public Nodo geraFilhoPassoCima(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia + 3);
		estadoFilho.set(indexPecaVazia + 3, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Cima ");
	}

	public Nodo geraFilhoPassoDireita(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia - 1);
		estadoFilho.set(indexPecaVazia - 1, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Direita ");
	}

	public Nodo geraFilhoPassoBaixo(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia - 3);
		estadoFilho.set(indexPecaVazia - 3, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Baixo ");
	}

}