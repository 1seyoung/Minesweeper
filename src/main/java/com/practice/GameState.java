package com.practice;

public enum GameState {
    INITIAL,  // 게임 초기 상태
    READY,    // 준비 상태 (첫 클릭 대기)
    PLAYING,  // 게임 진행 중
    GAME_OVER,// 게임 종료 (패배)
    VICTORY   // 게임 종료 (승리)
}


// 게임 초기 상태 - 사용자 이름 세팅 , 게임 정보 선택
// 준비 상태- 입력에 따라 맵, 사용자 구성 등이 끝나고 첫 클릭을 기다림
// 게임 진행 함
// 지뢰 누르면 게임 종료됨
// 다 끝내면 승리

// 1번 목표 : 1p
// 2번 목표 : 2p -> 점수제 도입 필요 + 기회
