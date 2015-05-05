package com.anderson.passaro.objetos;

import com.anderson.passaro.Auxiliares.Carregador;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tachyon on 29/04/2015.
 */
public class Passarinho {
  private Vector2 posicao;
  private Vector2 velocidade;
  private Vector2 aceleracao;
  private float rotacao;
  private int largura;
  private int altura;
  private Circle circulo;
  private boolean estaVivo;

  public Passarinho(float x, float y, int largura, int altura){
    this.largura=largura;
    this.altura=altura;
    posicao=new Vector2(x,y);
    velocidade=new Vector2(0,0);
    aceleracao=new Vector2(0,460);
    circulo=new Circle();
    estaVivo=true;
  }

  public void update(float delta){
    velocidade.add(aceleracao.cpy().scl(delta));
    if (velocidade.y>200){
      velocidade.y=200;
    }

    if(posicao.y<-13){
      posicao.y=-13;
      velocidade.y=0;
    }

    posicao.add(velocidade.cpy().scl(delta));

    circulo.set(posicao.x+9,posicao.y+6,6.5f);
    //rotacao antihorario
    if (velocidade.y<0){
      rotacao-=600*delta;
      if (rotacao<-20){
        rotacao=-20;
      }
    }
    //rotacao horario
    if(caindo()||!estaVivo){
      rotacao+=480*delta;
      if (rotacao>90){
        rotacao=90;
      }
    }
  }

  public boolean estaVivo(){
    return estaVivo;
  }

  public boolean caindo(){
    return velocidade.y>110;
  }

  public boolean naoBaterAsas() {
    return velocidade.y > 70||!estaVivo;
  }

  public void onClick(){
    if(estaVivo) {
      Carregador.flap.play();
      velocidade.y = -140;
    }
  }

  public void morre(){
    estaVivo=false;
    velocidade.y=0;
  }

  public void desacelera(){
    aceleracao.y=0;
  }

  public float getX(){
    return posicao.x;
  }

  public float getY(){
    return posicao.y;
  }

  public float getLargura(){
    return largura;
  }

  public float getAltura(){
    return altura;
  }

  public float getRotacao(){
    return rotacao;
  }

  public Circle getCirculo(){
    return circulo;
  }

  public void onReinicio(int y) {
    rotacao = 0;
    posicao.y = y;
    velocidade.x = 0;
    velocidade.y = 0;
    aceleracao.x = 0;
    aceleracao.y = 460;
    estaVivo = true;
  }
}
