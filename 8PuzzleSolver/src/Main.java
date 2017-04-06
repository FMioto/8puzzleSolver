import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int estado1[][] = {{8,6,7},{2,5,4},{3,0,1}};
		Solver testSolver = new Solver(estado1);
		ArrayList<String> solucao = testSolver.solvePuzzle();
		if(solucao!=null){
			for(String passo : solucao){
				System.out.println("Imprimindo passos para a solução:");
				System.out.println("- "+passo);
			}
		}
//		Nodo pai = new Nodo(estado1);
//		printEstado(pai);		
//		testaGeraFilhos(testSolver, pai);
		
		
		
	}
	
	public static void testaGeraFilhos(Solver testSolver, Nodo pai){
		System.out.println("Gerando filhos...");
		ArrayList<Nodo> filhos = testSolver.buscaFilhos(pai);
		for(Nodo n: filhos){
			System.out.println("Filho :");
			printEstado(n);
		}
		System.out.println("pirnt pai: ");
		printEstado(pai);
	}
	
	public static void printEstado(Nodo nodo){
		System.out.println(nodo.estado[0][0]+" - "+nodo.estado[0][1]+" - "+nodo.estado[0][2]);
		System.out.println(nodo.estado[1][0]+" - "+nodo.estado[1][1]+" - "+nodo.estado[1][2]);
		System.out.println(nodo.estado[2][0]+" - "+nodo.estado[2][1]+" - "+nodo.estado[2][2]);
		System.out.println("Custo = "+nodo.custoHeuristicaBordas);
		System.out.println("Custo total = "+nodo.custoTotalCaminho);
	}

}
