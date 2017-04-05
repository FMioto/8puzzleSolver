import java.util.ArrayList;
import java.util.Arrays;

public class HeuristicaBordas {

	//sequencia: cima, direita, embaixo, esquerda
	private static final ArrayList<Integer> BORDAS_1 = new ArrayList<>(Arrays.asList(-1,2,4,-1));
	private static final ArrayList<Integer> BORDAS_2 = new ArrayList<>(Arrays.asList(-1,3,5,1));
	private static final ArrayList<Integer> BORDAS_3 = new ArrayList<>(Arrays.asList(-1,-1,6,2));
	private static final ArrayList<Integer> BORDAS_4 = new ArrayList<>(Arrays.asList(1,5,7,-1));
	private static final ArrayList<Integer> BORDAS_5 = new ArrayList<>(Arrays.asList(2,6,8,4));
	private static final ArrayList<Integer> BORDAS_6 = new ArrayList<>(Arrays.asList(3,-1,0,5));
	private static final ArrayList<Integer> BORDAS_7 = new ArrayList<>(Arrays.asList(4,8,-1,-1));
	private static final ArrayList<Integer> BORDAS_8 = new ArrayList<>(Arrays.asList(5,0,-1,7));
	private static final ArrayList<Integer> BORDAS_VAZIO = new ArrayList<>(Arrays.asList(6,-1,-1,8));
	
// OUTPUTS:
//	-4 = todas as bordas erradas
//	-2 = tres bordas erradas e uma certa
//	0 = duas bordas erradas e duas certas
//	2 = tres bordas certas e uma errada
//	4 = todas as bordas certas
	
	public static int calculaBordasErradas(int numero, ArrayList<Integer>bordas){
		int somatoriaBordas = 0;
		switch(numero){
		case 1:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_1.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 2:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_2.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 3:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_3.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 4:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_4.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 5:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_5.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 6:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_6.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 7:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_7.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		case 8:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_8.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		default:
			for(int i = 0; i < bordas.size(); i++){
				if(bordas.get(i)!=BORDAS_VAZIO.get(i)){
					somatoriaBordas--;
				}else{
					somatoriaBordas++;
				}
			}
			break;
		}		
		return somatoriaBordas;
	}
	
//	Calcula quantas bordas pertecem as bordas corretas, independente da posicao delas
//	retorna entre 1 - 4
	public static int calculaQuantidadeBorda(int numero, ArrayList<Integer>bordas){
		int quantidade = 0;
		switch(numero){
		case 1:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_1.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 2:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_2.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 3:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_3.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 4:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_4.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 5:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_5.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 6:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_6.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 7:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_7.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		case 8:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_8.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		default:
			for(int i = 0; i < bordas.size(); i++){
				if(BORDAS_VAZIO.contains(bordas.get(i))){
					quantidade++;
				}
			}
			break;
		}
		return quantidade;
	}
	
}
