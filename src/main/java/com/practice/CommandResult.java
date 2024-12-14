package com.practice;

public class CommandResult {
    private boolean gameContinues; // final 인게 좋은건가.. 왜 추천할까
    private String message;

    public CommandResult(boolean gameContinues){
        this.gameContinues = gameContinues;
    }

    public CommandResult(boolean gameContinues, String message){
        this.gameContinues = gameContinues;
        this.message = message;
    }

    public boolean isGameContinues(){
        return gameContinues;
    }

    public String getMessage() {
        return message;
    }


}
//        for(int i = 0; i<size; i++ ){
//            for(int j= 0; j <size ; j ++){
//                if (!"x".equals(map[i][j])) {
//                    int count = 0;
//
//                    for (int k = 0; k < 8 ; k++){
//                        int ni = i + dx[k];
//                        int nj = j + dy[k];
//
//                        if (isInBounds(ni,nj,size) && "x".equals(map[ni][nj])){
//                            count ++;
//                        }
//                    }
//
//                    if (count !=0){
//                        map[i][j] = count;
//                    }
//                }
//            }
//        }
