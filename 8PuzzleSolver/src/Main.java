import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Integer> estado1 = new ArrayList<>();
		estado1.add(1);
		estado1.add(2);
		estado1.add(3);
		estado1.add(4);
		estado1.add(5);
		estado1.add(0);
		estado1.add(7);
		estado1.add(6);
		estado1.add(8);
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
	}
	
	public static void printEstado(Nodo nodo){
		System.out.println(nodo.estado.get(0)+" - "+nodo.estado.get(1)+" - "+nodo.estado.get(2));
		System.out.println(nodo.estado.get(3)+" - "+nodo.estado.get(4)+" - "+nodo.estado.get(5));
		System.out.println(nodo.estado.get(6)+" - "+nodo.estado.get(7)+" - "+nodo.estado.get(8));
		System.out.println("Custo = "+nodo.custoHeuristicaBordas);
	}

}
