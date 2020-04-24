import java.util.Scanner;

public class Main {
	
	static int altura, largura;
	static int eixoLinha, eixoColuna;
	static int linha, coluna;
	
	//metodo para validar se a execução vai estourar o limite da matriz
	static boolean dentroLimite(int linha, int coluna) {
		return (linha > -1) && (coluna > -1) && (linha < altura) && (coluna < largura); 
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		largura = sc.nextInt();
		altura = sc.nextInt();
		
		char[][] caminho = new char[altura][largura];
		boolean caminhoPercorrido[][] = new boolean[altura][largura];
		
		
		//popular array de String com todas as linhas
		String linhas[] = new String[altura];
		for (int i = 0; i < altura; i++) {
			linhas[i] = sc.next();
		}
		
		//popular a matriz caminho com todos os simbolos
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				caminho[i][j] = linhas[i].charAt(j);
			}
		}
		
		//enquanto estiver dento do Limite, percorrer o caminho dentro da matriz
		while(dentroLimite(linha, coluna)) {
			
			//se a posicao atual ja foi percorrida, interromper a execucao
			if(caminhoPercorrido[linha][coluna]) {
				System.out.println("!");
				return;
			}
			
			//se a posicao atual for o bau, parar a execucao
			if(caminho[linha][coluna] == '*') {
				System.out.println("*");
				return;
			}
			//se a seta for >, percorrer a matriz para a direita
			if(caminho[linha][coluna] == '>') {
				eixoLinha = 0;
				eixoColuna = 1;	
			}
			//se a seta for <, percorrer a matriz para a esquerda
			else if(caminho[linha][coluna] == '<') {
				eixoLinha = 0;
				eixoColuna = -1;
			}
			//se a seta for v, percorrer a matriz para baixo
			else if(caminho[linha][coluna] == 'v') {
				eixoLinha = 1;
				eixoColuna = 0;
			}
			//se a seta for ^, percorrer a matriz para cima
			else if(caminho[linha][coluna] == '^') {
				eixoLinha = -1;
				eixoColuna = 0;
			}
			
			//marcar a posicao atual como percorrida
			caminhoPercorrido[linha][coluna] = true;
			
			linha = linha + eixoLinha; //atualiza posicao da largura da matriz
			coluna = coluna + eixoColuna; //atualiza posicao da altura da matriz
		}
	}
}
