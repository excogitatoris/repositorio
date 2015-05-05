package com.anderson.passaro.mundo;

import com.anderson.passaro.Auxiliares.Carregador;
import com.anderson.passaro.objetos.ManipuladorRolagem;
import com.anderson.passaro.objetos.Passarinho;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by tachyon on 27/04/2015.
 */
public class Mundo {
//private Rectangle retangulo=new Rectangle(0,0,17,12);
  private Passarinho passarinho;
  private ManipuladorRolagem rolador;
  //private boolean estaVivo;
  private Rectangle chao;
  private int placar=0;
  public enum Estados{PRONTO,RODANDO,GAMEOVER,HIGHSCORE}
  private Estados estado;
  private int pontoMeioY;

  public Mundo(int pontoMeioY){
    this.pontoMeioY=pontoMeioY;
    passarinho=new Passarinho(33,pontoMeioY-5,17,12);
    rolador= new ManipuladorRolagem(this,pontoMeioY+66);
    chao=new Rectangle(0,pontoMeioY+66,136,11);
    estado=Estados.PRONTO;
  }

public void update(float delta){
 switch (estado){
   case PRONTO:
     updatePronto(delta);
     break;
   case RODANDO:
     updateRodando(delta);
     break;
   default:
     break;
 }
}
  private void updateRodando(float delta){
  if(delta>.15f){
    delta=.15f;
  }
  passarinho.update(delta);
  rolador.update(delta);
  if(rolador.colisao(passarinho)&&passarinho.estaVivo()){
    rolador.parar();
    Carregador.morte.play();
    //estaVivo=false;
  }
  if(Intersector.overlaps(passarinho.getCirculo(),chao)){
    rolador.parar();
    passarinho.morre();
    passarinho.desacelera();
    estado=Estados.GAMEOVER;
    if(placar>Carregador.getHighScore()){
      Carregador.setHighScore(placar);
      estado=Estados.HIGHSCORE;
    }
  }
  }
  private void updatePronto(float delta){

  }

  public boolean eHighScore(){
    return estado==Estados.HIGHSCORE;
  }

  public Passarinho getPassarinho(){
    return passarinho;
  }

  public ManipuladorRolagem getRolador(){
    return rolador;
  }

  public int getPlacar(){
    return placar;
  }

  public void aumentaPlacar(int incremento){
    placar+=incremento;
  }

  public boolean estaPronto(){
    return estado==Estados.PRONTO;
  }

  public void inicio(){
    estado=Estados.RODANDO;
  }

  public void reinicio(){
    estado=Estados.PRONTO;
    placar=0;
    passarinho.onReinicio(pontoMeioY-5);
    rolador.onReinicio();
    estado=Estados.PRONTO;
  }
  public boolean eGameOver(){
    return estado==Estados.GAMEOVER;
  }
}
