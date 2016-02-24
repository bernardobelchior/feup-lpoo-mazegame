package game;		//O QUE � ISTO?? Not sure

public class Hero extends Entity{
	private boolean swordEquipped;
	
	public Hero(int x, int y){
		super(x, y);		//O QUE � ISTO?? Construtor da classe mae
		swordEquipped = false;
	}
		
	public void equipSword(){
		swordEquipped = true;
		//� preciso mudar letra para 'A' mas nao criaste esse atributo em nenhuma das classes e tens um getchar ali em baixo...nao percebo...
		// Ve a funcao getChar
	}
	
	public boolean getSwordEquipped(){
		return swordEquipped;
	}
	
	
	public char getChar(){
		return swordEquipped ? 'A' : 'H';	//O QUE � ISTO?? Se swordEquipped e true entao retorna 'A' senao retorna 'H'. E um if basicamente
	}
	
	/*public boolean NextToDragon(){
		if ((dragon.getX()==x+1 && dragon.getY()==y)||(dragon.getX()==x-1 && dragon.getY()==y)||
			(dragon.getX()==x && dragon.getY()==y+1)||(dragon.getX()==x && dragon.getY()==y+1)){
			 nexttodragon=true;
		}
		return nexttodragon;
	}*/	
}
