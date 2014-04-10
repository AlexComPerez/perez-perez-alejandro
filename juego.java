/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;

/**
 *
 * @author Alejandro Perez Perez
 */
public class juego {
    int[][] tablero= new int[8][8];     //se define la matriz de 8 x 8, con valores iniciales de 0   
 
    public boolean solucion(int f, int c, int num) { //se define la fila y la columna, asi como el numero que se 
        //posicionara en ese cuadrado.
            tablero[f][c] = num;     //Se le agrega el numero(de reina) a la casilla, en la fila y columna especificada.
            if(num==8) return true;  //los numeros que se agregaran a las casillas son secuenciales, lo que facilita la 
               //saber cuando ya se colocaron las 8 reinas.
            int[][] posibles = buscarPosibles();  //se describe en el metodo
            for(int i=0; i<posibles.length; i++) {
                if(solucion(posibles[i][0], posibles[i][1], num+1)) return true;
            }
            tablero[f][c]=0;
            return false;
    }
 
    int[][] buscarPosibles() {
        int[][] cuadritos = new int[64][2];   //se crea una matriz de 64 filas y dos columnas, el indice de cada fila reprecenta cada cuadro de la matriz del tablero(8*8) y las columnas son para saber si estan o no ocupadas esas casillas (0 y 1)
        int[] FilasOcupadas = new int[8];    //se hace un arreglo del tamaño total de filas, lo cual sirvira para saber de forma general si una fila ya tiene algun cuadro ocupado, funcionara como la cabecera de fila
        int[] ColumnasOcupadas = new int[8]; //se hace un arreglo del tamaño total de columnas, lo cual serivira para saber de forma general sui una fila ya tiene algun cuadro ocupado. Es la cabecera de columna
        int     pos  = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(tablero[i][j]>0) {   //esto comprueba todas las casillas de la matriz. Todas las columnas de cada fila.
                    FilasOcupadas[i]++;      //Si encuentra un elemento en alguna fila, aumenta a 1 el contenido de esa celda, y asi se sabe que en toda la fila ya se encuenra un elemento.
                    ColumnasOcupadas[j]++;   //Lo mismo que en la fila, solo que ahora en la columna.
                }
            }
        }
        for(int i=0; i<8; i++) {
            if(FilasOcupadas[i]>0) continue;  //si la fila N esta ocupada(tiene valor 1), continua para la siguiente instruccion.
            for(int j=0; j<8; j++) {
                if(ColumnasOcupadas[j]>0) continue;  //si la columna N esta ocupada en alguna posicion, continua
                if(i>0 && j>0 && tablero[i-1][j-1] > 0) continue;
                if(i>0 && j<8-1 && tablero[i-1][j+1] > 0) continue;
                if(i<8-1 && j>0 && tablero[i+1][j-1] > 0) continue;
                if(i<8-1 && j<8-1 && tablero[i+1][j+1] > 0) continue;
                cuadritos[pos][0]=i;   // Asigan su valor del 1 al 8 para cada reina en el cuadrito
                cuadritos[pos][1]=j;  //lo mismo aqui para cada una de las posiciones
                pos++;
            }
        }
        int[][] temporal = new int[pos][2]; //es matriz temporal porque solo se crea para cada una de las posiciones (del 1 al 64), y se elimina al salir del metodo
        for(int i=0; i<pos; i++) { 
            temporal[i][0] = cuadritos[i][0]; //se pasa el valor de  cuadritos en la columna 0 al temporal para ser retornado por el metodo
            temporal[i][1]=cuadritos[i][1]; //Se paa el valor de la columna 1 de cuadritos.
        }
        return temporal;
    }
    
        public void mostrarReinas(){
          System.out.println("Para obtener la solucion, las reinas se posicionan en: \n");  
       for(int i=0; i<tablero.length; i++){
           for(int j=0; j<tablero.length; j++){
             if(tablero[i][j]>0)
                 System.out.println("("+i+","+j+")");
           }  
       }
    }
  
    public static void main(String[] args) {
        Random aleatorio= new Random();
        int filaInicial   = aleatorio.nextInt(8);
        int columnaInicial    = aleatorio.nextInt(8);
        juego inicia = new juego();
        inicia.solucion(filaInicial, columnaInicial,1);
        inicia.mostrarReinas();
       
    }
    
}
