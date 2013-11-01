
public class BowlingScoreTest {
	
	private Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
	}
	
	private void rollMany(int number, int pins) {
		for(int index = 0; index  < number; index++){
			game.roll(pins);
		}
	}
	
	@Test /**This is Annotation*/
	public void firstTest() {
		int number = 20;
		int pins = 0;
		
		rollMany(number, pins);
		
		assertEquals(0, game.score());
	}

	@Test
	public void WholeGameTest(){
		int n = 20;
		int pins = 1;
		
		rollMany(n, pins);
		
		assertEquals(20, game.score());
	}
	
	@Test
	public void SpareGameTest(){
		game.roll(5);
		game.roll(5);
		game.roll(5);
		
		rollMany(17, 0);
		
		assertEquals(20, game.score());
	}
	
	@Test
	public void StrikeGameTest(){
		game.roll(10);
		game.roll(0);
		
		game.roll(3);
		game.roll(4);
		
		rollMany(16, 0);
		
		assertEquals(24, game.score());
	
	}
	
	@Test
	public void DoubleStrikeTest(){
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(0);
		game.roll(3);
		game.roll(4);
		
		rollMany(14, 0);
		
		assertEquals(47, game.score());
	}
	
	@Test
	public void TripleStrikeTest(){
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(0);
		
		game.roll(3);
		game.roll(4);
		
		rollMany(12, 0);
		
		assertEquals(77, game.score());
	}
	
	@Test
	public void LastFrameTest(){
		rollMany(18, 0);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		
		assertEquals(30, game.score());
	}
	
	@Test
	public void LastToOneFrameTest(){
		rollMany(16, 0);
		game.roll(10);
		game.roll(0);
		
		
		/**이거봐이거봐 인덱싱 에러잖아*/
		game.roll(10);
		game.roll(10);
		game.roll(10);
		
		assertEquals(60, game.score());
	}
	
	@Test
	public void PerfectGameTest(){
		for(int index = 0; index < 9; index ++){
			game.roll(10);
			game.roll(0);
		}
		
		/**마지막 프레임이 3회 굴린다는 사실을 아직 적지 않았지
		 * 허나 스트라이크가 없다면 기회상실이라는 사실도 염두해야지*/
		game.roll(10);
		game.roll(10);
		game.roll(10);
		
		assertEquals(300, game.score());
	}
	
	public void ArbitraryScoreTest(){
		
		game.roll(5);
		game.roll(5);
		
		game.roll(10);
		game.roll(0);
		
		game.roll(7);
		game.roll(3);
		
		game.roll(6);
		game.roll(3);
		
		game.roll(0);
		game.roll(9);
		
		game.roll(9);
		game.roll(1);
		
		game.roll(10);
		game.roll(0);
		
		game.roll(9);
		game.roll(1);
		
		game.roll(10);
		game.roll(0);
		
		game.roll(9);
		game.roll(1);
		game.roll(10);
		
		assertEquals(174, game.score());
		
	}

}
