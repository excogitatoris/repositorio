package com.anderson.passaro.objetos;

/**
 * Created by tachyon on 02/05/2015.
 */
public class Grama extends Rolavel {
  // When Grass's constructor is invoked, invoke the super (Scrollable)
  // constructor
  public Grama(float x, float y, int largura, int altura, float velocidadeRolagem) {
    super(x, y, largura, altura, velocidadeRolagem);
  }
  public void onReinicio(float x, float valocidadeRolagem) {
    posicao.x = x;
    velocidade.x = valocidadeRolagem;
  }
}
