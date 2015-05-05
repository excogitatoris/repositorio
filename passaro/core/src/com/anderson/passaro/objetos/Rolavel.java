package com.anderson.passaro.objetos;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tachyon on 02/05/2015.
 */
public class Rolavel {
  // Protected is similar to private, but allows inheritance by subclasses.
  protected Vector2 posicao;
  protected Vector2 velocidade;
  protected int largura;
  protected int altura;
  protected boolean rolagemEsquerda;

  public Rolavel(float x, float y, int largura, int altura, float velocidadeRolagem) {
    posicao = new Vector2(x, y);
    velocidade = new Vector2(velocidadeRolagem, 0);
    this.largura = largura;
    this.altura = altura;
    rolagemEsquerda = false;
  }

  public void update(float delta) {
    posicao.add(velocidade.cpy().scl(delta));

    // If the Scrollable object is no longer visible:
    if (posicao.x + largura < 0) {
      rolagemEsquerda = true;
    }
  }

  // Reset: Should Override in subclass for more specific behavior.
  public void reset(float newX) {
    posicao.x = newX;
    rolagemEsquerda = false;
  }

  public void parar(){
    velocidade.x=0;
  }
  // Getters for instance variables
  public boolean eRolagemEsquerda() {
    return rolagemEsquerda;
  }

  public float getExremidadeX() {
    return posicao.x + largura;
  }

  public float getX() {
    return posicao.x;
  }

  public float getY() {
    return posicao.y;
  }

  public int getLargura() {
    return largura;
  }

  public int getAltura() {
    return altura;
  }

}
