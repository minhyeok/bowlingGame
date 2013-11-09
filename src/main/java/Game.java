public class Game {
	
	/**�������� �ִ� ���� Ƚ���� 21ȸ*/
	int rolls[] = new int[21];
	int iIndex = 0;
	
	/**���� ���߿��� �� �����ӿ� ������ �����Ѵ�*/
	public void roll(int pins){
		rolls[iIndex++] = pins;
	}

	/**������ ���ھ� ��� ����*/
	public int score(){
		int score = 0;
		int index = 0;
		/**9������ ������ ������ ���� ���� ����*/
		for(index=0;index<9;index ++){
			if(isStrike(index)){ /**��Ʈ����ũ �ΰ�?*/
				if(isStrike(index+1)){ /**���� �����ӵ� ��Ʈ����ũ�ΰ�?*/
					if(isStrike(index+2)){ /**�� ���� �����ӵ�...?*/
						if(index == 7){ /**8������ ȸ�� ���*/
							score+=10+framesCore(index+ 1)+rolls[(index+2)*2];
						}else if(index == 8){ /** 9������ ȸ�� ���*/
							score += 10 + rolls[(index+1)*2] + rolls[(index+2)*2];
						}else{ /**�Ϲ����� ���*/
							score += 10 + framesCore(index+1) + framesCore(index+2);
						}
						/**��� �̰� �������� �ڵ��� �ϱ�����ڴµ�*/
					}else{
						score += 10 + framesCore(index+1) + bonusScoreOfSpare(index +1);
					}
				}else{
					score += 10 + framesCore(index + 1);
				}
				
			}
			else if(isSpare(index)){/**�װ͵� �ƴϸ� ������ΰ�?*/
				score += 10 + bonusScoreOfSpare(index);
				
			}else{ /**�װ͵� �ƴϸ�*/
				score += framesCore(index);
			}
			framesCore(index);
		}
		
		/**������ �������� ���� �����ݾ�, �װ͵� ��Ʈ����ũ ���ο� �������.*/
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
