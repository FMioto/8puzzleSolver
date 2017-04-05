import java.util.ArrayList;

public class Solver {

	ArrayList<Nodo> fronteira;
	ArrayList<Nodo> nodosVisitados;
	Nodo nodoAtual;
	ArrayList<Integer> estadoSolucao;
	boolean erro = false;

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
			if (foiVisitado(nodoAtual)){
				int indexVisitado = getIndexNaLista(nodoAtual, nodosVisitados);
				if(nodoAtual.custoTotalCaminho < nodosVisitados.get(indexVisitado).custoTotalCaminho){
					adicionaOuAtualizaFilhos(buscaFilhos(nodoAtual));
				}
			} else {
				adicionaOuAtualizaFilhos(buscaFilhos(nodoAtual));
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
			if (f.estado.equals(n)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean foiVisitado(Nodo n) {
		for (Nodo v : nodosVisitados) {
			if (v.estado.equals(n)) {
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
		int indexVisitados = getIndexNaLista(novoNodo, nodosVisitados);
		if(novoNodo.custoTotalCaminho < nodosVisitados.get(indexVisitados).custoTotalCaminho){
			atualizaFilho(nodosVisitados.get(indexVisitados), novoNodo);
		}
	}
	
	public int getIndexNaLista(Nodo procurado, ArrayList<Nodo> lista){
		for (Nodo n : lista) {
			if (n.estado.equals(procurado)) {
				return fronteira.indexOf(procurado);
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
			if (n.custoTotalCaminho < maisBarato.custoTotalCaminho) {
				if (maisBarato.tamanhoCaminho < 32) {
					maisBarato = n;
				}
			}
		}
		if (maisBarato.tamanhoCaminho >= 32) {
			maisBarato = null;
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
		return solucao;
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

		return new Nodo(estadoFilho, pai, "Esquerda");
	}

	public Nodo geraFilhoPassoCima(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia + 3);
		estadoFilho.set(indexPecaVazia + 3, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Cima");
	}

	public Nodo geraFilhoPassoDireita(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia - 1);
		estadoFilho.set(indexPecaVazia - 1, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Direita");
	}

	public Nodo geraFilhoPassoBaixo(Nodo pai, int indexPecaVazia) {
		ArrayList<Integer> estadoFilho = new ArrayList<>(pai.estado);
		int pecaEmbaixo = estadoFilho.get(indexPecaVazia - 3);
		estadoFilho.set(indexPecaVazia - 3, 0);
		estadoFilho.set(indexPecaVazia, pecaEmbaixo);

		return new Nodo(estadoFilho, pai, "Baixo");
	}

}
