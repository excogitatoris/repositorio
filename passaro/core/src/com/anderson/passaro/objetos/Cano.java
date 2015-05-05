package com.anderson.passaro.objetos;

/**
 * Created by tachyon on 02/05/2015.
 */
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Cano extends Rolavel {
  private Random r;
  private Rectangle caveiraAlta, caveiraBaixa,barraAlta,barraBaixa;
  public static final int INTERVALOVERTICAL =45;
  public static final int LARGURACAVEIRA=24;
  public static final int ALTURACAVEIRA=11;
  private float soloY;
  private boolean contabilizado=false;
  // When Pipe's constructor is invoked, invoke the super (Scrollable)
  // constructor
  public Cano(float x, float y, int largura, int altura, float velocidadeRolagem,float soloY) {
    super(x, y, largura, altura, velocidadeRolagem);
    // Initialize a Random object for Random number generation
    r = new Random();
    caveiraAlta =new Rectangle();
    caveiraBaixa =new Rectangle();
    barraAlta=new Rectangle();
    barraBaixa=new Rectangle();
    this.soloY=soloY;
  }

  @Override
  public void update(float delta){
    super.update(delta);
    barraAlta.set(posicao.x,posicao.y,largura,altura);
    barraBaixa.set(posicao.x,posicao.y+altura+ INTERVALOVERTICAL,largura,soloY-(posicao.y+altura+ INTERVALOVERTICAL));
    caveiraAlta.set(posicao.x-(LARGURACAVEIRA-largura)/2,posicao.y+altura-ALTURACAVEIRA,LARGURACAVEIRA,ALTURACAVEIRA);
    caveiraBaixa.set(posicao.x-(LARGURACAVEIRA-largura)/2,barraBaixa.y,LARGURACAVEIRA,ALTURACAVEIRA);
  }

  @Override
  public void reset(float novoX) {
    // Call the reset method in the superclass (Scrollable)
    super.reset(novoX);
    // Change the height to a random number
    altura = r.nextInt(90) + 15;
    contabilizado=false;
  }
  public Rectangle getCaveiraAlta(){
    return caveiraAlta;
  }
  public Rectangle getCaveiraBaixa(){
    return caveiraBaixa;
  }
  public Rectangle getBarraAlta(){
    return barraAlta;
  }
  public Rectangle getBarraBaixa(){
    return barraBaixa;
  }

  public boolean colisao(Passarinho passarinho){
    if(posicao.x<passarinho.getX()+ passarinho.getLargura()){
      return (Intersector.overlaps(passarinho.getCirculo(),barraAlta)||Intersector.overlaps(passarinho.getCirculo(),barraBaixa)||
          Intersector.overlaps(passarinho.getCirculo(),caveiraAlta)|| Intersector.overlaps(passarinho.getCirculo(),caveiraBaixa));
    }
    return false;
  }

  public boolean getContabilizado(){
    return contabilizado;
  }

  public void setContabilizado(boolean b){
    contabilizado=b;
  }

  public void onReinicio(float x, float velocidadeRolagem) {
    velocidade.x = velocidadeRolagem;
    reset(x);
  }
}
