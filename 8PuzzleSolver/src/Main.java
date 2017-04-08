import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int estado1[][] = {{2,7,1},{8,3,6},{4,5,0}};
		Solver testSolver = new Solver(estado1);
		ArrayList<String> solucao = testSolver.solvePuzzle();
		if(solucao!=null){
			System.out.println("Numero de passos para a solução: "+solucao.size());
			System.out.println("Imprimindo passos para a solução:");
			for(int i = solucao.size()-1; i>=0; i--){
				System.out.println("- "+solucao.get(i));
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
