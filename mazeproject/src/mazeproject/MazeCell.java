package mazeproject;

public class MazeCell {

	private CellType type;
	private boolean end;
	private MazeCell[] neighbours = new MazeCell[6];
	
	
	
	
	public static enum CellType{
		WALL("W"), START("M"), END("C"), OPEN("O"),
		CURRENT_PATH(" "), END_FOUND("S"), REJECTED("X");
		
		private final String display;
		CellType(String display) {
		this.display = display;
		}
		
		public String getDisplay() { return display; }
		};
}
