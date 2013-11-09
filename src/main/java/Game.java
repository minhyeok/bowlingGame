public class Game {
	
	/**볼링에서 최대 투구 횟수는 21회*/
	int rolls[] = new int[21];
	int iIndex = 0;
	
	/**실행 도중에는 각 프레임에 점수만 누적한다*/
	public void roll(int pins){
		rolls[iIndex++] = pins;
	}

	/**최종적 스코어 취득 수행*/
	public int score(){
		int score = 0;
		int index = 0;
		/**9프레임 까지는 프레임 단위 연산 수행*/
		for(index=0;index<9;index ++){
			if(isStrike(index)){ /**스트라이크 인가?*/
				if(isStrike(index+1)){ /**다음 프레임도 스트라이크인가?*/
					if(isStrike(index+2)){ /**그 다음 프레임도...?*/
						if(index == 7){ /**8프레임 회차 계산*/
							score+=10+framesCore(index+ 1)+rolls[(index+2)*2];
						}else if(index == 8){ /** 9프레임 회차 계산*/
							score += 10 + rolls[(index+1)*2] + rolls[(index+2)*2];
						}else{ /**일반적인 경우*/
							score += 10 + framesCore(index+1) + framesCore(index+2);
						}
						/**사실 이거 보기좋은 코드라고 하기힘들겠는데*/
					}else{
						score += 10 + framesCore(index+1) + bonusScoreOfSpare(index +1);
					}
				}else{
					score += 10 + framesCore(index + 1);
				}
				
			}
			else if(isSpare(index)){/**그것도 아니면 스페어인가?*/
				score += 10 + bonusScoreOfSpare(index);
				
			}else{ /**그것도 아니면*/
				score += framesCore(index);
			}
			framesCore(index);
		}
		
		/**마지막 프레임은 세번 던지잖아, 그것도 스트라이크 여부에 상관없이.*/
		score += lastFrameScore();
		
		return score;
	}

	private boolean isStrike(int index){
		return rolls[index*2]== 10;
	}

	private int bonusScoreOfSpare(int index){
		return rolls[(index + 1) *2];
	}

	private boolean isSpare(int index){
		return framesCore(index) == 10;
	}

	private int framesCore(int index){
		return rolls[index *2] + rolls[index *2 + 1];
	}
	
	private int lastFrameScore(){
		return rolls[18] + rolls[19] + rolls[20];
	}
}
