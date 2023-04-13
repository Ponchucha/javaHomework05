/*На шахматной доске расставить 8 ферзей так, 
чтобы они не били друг друга. 
И вывести Доску. Пример вывода доски 8x8
0x000000
0000x000
00x00000 */
public class task03 {
    public static void main(String[] args) {
        String[][] board = new String [8][8];
        boolean isPossible = true;
        int[] positions = new int [8];
        boolean[] isQweenPlaced = new boolean [8];
        for (int i = 0; i < board.length; i++) {    // выбрали строку (i - индекс текущей строки)
            for (int k = 0; k < board.length; k++) {// ищем, куда поставить ферзя:   (k - индекс текущего столбца)
                if(isQweenPlaced[i] == true){
                    board[i][k] = "0";
                    isPossible = false;
                }
                else if (i == 0){                        // если строка первая
                    board[i][k] = "x";
                    positions[i] = k;             // то просто ставим ферзя в текущую клетку
                    isQweenPlaced[i] = true;
                }
                else{                               // в противном случае
                    for (int q = 0; q < i; q++) {       // в предыдущих строках      (q - индекс проверяемой строки)
                        if(board[q][k].equals("x") ||                       // если в том же столбце есть ферзь
                        (q+(k-i) >= 0 && board[q][q+(k-i)].equals("x")) ||  // если по диагонали влево есть ферзь
                        (i+k)-q < 8 && board[q][(i+k)-q].equals("x")){     // если по диагонали вправо есть ферзь         
                            board[i][k] = "0";            // проверка не пройдена, клетка не подходит
                            isPossible = false;
                        }
                    }
                    if (isPossible){       // все проверки пройдены, можно ставить ферзя; 
                        board[i][k] = "x";
                        positions[i] = k;
                        isQweenPlaced[i] = true;
                    }
                    else{
                        if (k == board.length - 1){              // если это последняя клетка в строке
                            while(true){                        // пока не найдём строку, где можно возобновить поиск
                                i--;                             // возвращаемся на строку выше
                                board[i][positions[i]] = "0";    // убираем с неё ферзя
                                isQweenPlaced[i] = false;
                                if(positions[i] < board.length-1){ // если он был не на последней клетке,
                                    k = positions[i];          // начинаем искать для ферзя новую позицию со следущей клетки
                                    break;
                                }
                            }
                        }
                        else{                                     // если клетка не последняя
                            board[i][k] = "0";                    // ставим "0"
                        }

                    }
                }
                isPossible = true;               
            }
            isQweenPlaced[i] = false;                           
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {            
            System.out.print((i) + ". ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

}

