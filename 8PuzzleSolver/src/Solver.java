import java.util.ArrayList;

public class Solver {

	ArrayList<Nodo> fronteira;
	ArrayList<Nodo> nodosVisitados;
	Nodo nodoAtual;
	int[][] estadoSolucao = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	boolean erro = false;

	public Solver(int[][] estadoInicial) {
		nodoAtual = new Nodo(estadoInicial);
		fronteira = new ArrayList<>();
		nodosVisitados = new ArrayList<>();
	}

	public ArrayList<String> solvePuzzle() {
		while (!nodoAtual.ehIgual(estadoSolucao)) {
			if (foiVisitado(nodoAtual)) {
				int indexVisitado = getIndexNaLista(nodoAtual, nodosVisitados);
				if (nodoAtual.custoTotalCaminho < nodosVisitados.get(indexVisitado).custoTotalCaminho) {
					adicionaOuAtualizaFilhos(geraFilhos(nodoAtual));
				}
			} else {
				adicionaOuAtualizaFilhos(geraFilhos(nodoAtual));
			}
			fronteira.remove(nodoAtual);
			nodosVisitados.add(nodoAtual);
			nodoAtual = buscaNodoFronteira();
			if (nodoAtual == null) {
				nodoAtual = new Nodo(estadoSolucao);
				erro = true;
			}
			System.out.println("Nodos na Fronteira: " + fronteira.size());
		}
		if (erro) {
			System.out.println("Programa terminado. Caminho não encontrado");
			return null;
		}
		System.out.println("Solução encontrada!");
		return geraCaminhoSolucao(nodoAtual);
	}

	// Verifica se os filhos estão na fronteira ou ja foram visitados,
	// nestes caso atualiza os filhos se os custos forem menores.
	// Se ele não estiverem na fronteira e não foram visitados, são adicionados
	// a fronteira.
	private void adicionaOuAtualizaFilhos(ArrayList<Nodo> filhos) {
		for (Nodo n : filhos) {
			if (taNaFronteira(n)) {
				atualizaFilhoFronteira(n);
			} else if (foiVisitado(n)) {
				atualizaFilhoVisitados(n);
			} else {
				fronteira.add(n);
			}
		}
	}

	private boolean taNaFronteira(Nodo n) {
		for (Nodo f : fronteira) {
			if (n.ehIgual(f.estado)) {
				return true;
			}
		}
		return false;
	}

	private boolean foiVisitado(Nodo n) {
		for (Nodo v : nodosVisitados) {
			if (n.ehIgual(v.estado)) {
				return true;
			}
		}
		return false;
	}

	private void atualizaFilhoFronteira(Nodo novoNodo) {
		int indexFronteira = getIndexNaLista(novoNodo, fronteira);
		if (novoNodo.custoTotalCaminho < fronteira.get(indexFronteira).custoTotalCaminho) {
			atualizaFilho(fronteira.get(indexFronteira), novoNodo);
		}
	}

	private void atualizaFilhoVisitados(Nodo novoNodo) {
		int indexVisitado = getIndexNaLista(novoNodo, nodosVisitados);
		if (novoNodo.custoTotalCaminho < nodosVisitados.get(indexVisitado).custoTotalCaminho) {
			atualizaFilho(nodosVisitados.get(indexVisitado), novoNodo);
		}
	}

	public int getIndexNaLista(Nodo procurado, ArrayList<Nodo> lista) {
		for (Nodo n : lista) {
			if (procurado.ehIgual(n.estado)) {
				return lista.indexOf(n);
			}
		}
		return -1;
	}

	public void atualizaFilho(Nodo velho, Nodo novo) {
		velho.custoTotalCaminho = novo.custoTotalCaminho;
		velho.tamanhoCaminho = novo.tamanhoCaminho;
		velho.pai = novo.pai;
		velho.passo = novo.passo;
	}

	public Nodo buscaNodoFronteira() {
		Nodo maisBarato = fronteira.get(0);
		for (Nodo n : fronteira) {
			if (n.custoTotalCaminho < maisBarato.custoTotalCaminho) {
				if (maisBarato.tamanhoCaminho < 32) {
					maisBarato = n;
				}
			}
		}
		// se o nodo mais barato vem de uma profundidade maior que 32 significa
		// heuristica falhou
		if (maisBarato.tamanhoCaminho >= 32) {
			maisBarato = null;
		}
		return maisBarato;
	}

	// retorna os passos necessários para chegar à solução
	public ArrayList<String> geraCaminhoSolucao(Nodo nodoAtual) {
		Nodo nodo = nodoAtual;
		ArrayList<String> solucao = new ArrayList<>();
		while (nodo.pai != null) {
			solucao.add(nodo.passo);
			nodo = nodo.pai;
		}
		return solucao;
	}

	public ArrayList<Nodo> geraFilhos(Nodo pai) {
		ArrayList<Nodo> filhos = new ArrayList<>();
		ArrayList<Integer> coordenadasZero = pai.getCoordenada(0);
		int linhaZero = coordenadasZero.get(0);
		int colunaZero = coordenadasZero.get(1);

		if (linhaZero < 2) {
			filhos.add(geraFilhoPassoBaixo(pai, coordenadasZero.get(0), coordenadasZero.get(1)));
		}

		if (linhaZero > 0) {
			filhos.add(geraFilhoPassoCima(pai, coordenadasZero.get(0), coordenadasZero.get(1)));
		}

		if (colunaZero < 2) {
			filhos.add(geraFilhoPassoDireita(pai, coordenadasZero.get(0), coordenadasZero.get(1)));
		}

		if (colunaZero > 0) {
			filhos.add(geraFilhoPassoEsquerda(pai, coordenadasZero.get(0), coordenadasZero.get(1)));
		}

		return filhos;
	}

	public Nodo geraFilhoPassoBaixo(Nodo pai, int linhaZero, int colunaZero) {
		int[][] estadoFilho = pai.getCopia();
		int numeroABaixo = estadoFilho[linhaZero + 1][colunaZero];
		estadoFilho[linhaZero][colunaZero] = numeroABaixo;
		estadoFilho[linhaZero + 1][colunaZero] = 0;

		return new Nodo(estadoFilho, pai, "Baixo");
	}

	public Nodo geraFilhoPassoCima(Nodo pai, int linhaZero, int colunaZero) {
		int[][] estadoFilho = pai.getCopia();
		int numeroACima = estadoFilho[linhaZero - 1][colunaZero];
		estadoFilho[linhaZero][colunaZero] = numeroACima;
		estadoFilho[linhaZero - 1][colunaZero] = 0;

		return new Nodo(estadoFilho, pai, "Cima");
	}

	public Nodo geraFilhoPassoDireita(Nodo pai, int linhaZero, int colunaZero) {
		int[][] estadoFilho = pai.getCopia();
		int numeroADireita = estadoFilho[linhaZero][colunaZero + 1];
		estadoFilho[linhaZero][colunaZero] = numeroADireita;
		estadoFilho[linhaZero][colunaZero + 1] = 0;

		return new Nodo(estadoFilho, pai, "Direita");
	}

	public Nodo geraFilhoPassoEsquerda(Nodo pai, int linhaZero, int colunaZero) {
		int[][] estadoFilho = pai.getCopia();
		int pessaAEsquerda = estadoFilho[linhaZero][colunaZero - 1];
		estadoFilho[linhaZero][colunaZero] = pessaAEsquerda;
		estadoFilho[linhaZero][colunaZero - 1] = 0;
		return new Nodo(estadoFilho, pai, "Esquerda");
	}
}
