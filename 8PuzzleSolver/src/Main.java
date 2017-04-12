import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int estado1[][] = {{4,7,5},{0,2,1},{3,6,8}};
		Solver testSolver = new Solver(estado1);
		ArrayList<String> solucao = testSolver.solvePuzzle();
		if(solucao!=null){
			System.out.println("Numero de passos para a solução: "+solucao.size());
			System.out.println("Imprimindo passos para a solução:");
			for(int i = solucao.size()-1; i>=0; i--){
				System.out.println("- "+solucao.get(i));
			}
		}		
	}
}
