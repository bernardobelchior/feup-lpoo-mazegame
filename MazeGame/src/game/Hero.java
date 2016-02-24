package game;		//O QUE É ISTO??

public class Hero extends Entity{
	private boolean swordEquipped;
	private boolean nexttodragon;
	private boolean life;
	private Dragon dragon;
	private Sword sword;
	
	public Hero(int x, int y){
		super(x, y);		//O QUE É ISTO??
		swordEquipped = false;
		nexttodragon=false;
		life=true;
	}
	
	public void FindSword(){
		if (x==sword.getX() && y==sword.getY()){
			equipSword();
		}
	}
		
	public void equipSword(){
		swordEquipped = true;
		//é preciso mudar letra para 'A' mas nao criaste esse atributo em nenhuma das classes e tens um getchar ali em baixo...nao percebo...
	}
	
	public boolean getSwordEquipped(){
		return swordEquipped;
	}
	
	public boolean getnexttodragon(){
		return nexttodragon;
	}
	
	public boolean getlife(){
		return life;
	}
	
	
	public char getChar(){
		return swordEquipped ? 'A' : 'H';	//O QUE É ISTO??
	}
	
	public boolean NextToDragon(){
		if ((dragon.getX()==x+1 && dragon.getY()==y)||(dragon.getX()==x-1 && dragon.getY()==y)||
			(dragon.getX()==x && dragon.getY()==y+1)||(dragon.getX()==x && dragon.getY()==y+1)){
			 nexttodragon=true;
		}
		return nexttodragon;
	}
	
	public boolean Dead(){
		return life=false;
	}
	
}
