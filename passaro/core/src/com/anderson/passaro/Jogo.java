package com.anderson.passaro;

import com.anderson.passaro.Auxiliares.Carregador;
import com.anderson.passaro.telas.Tela;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Jogo extends Game {
  @Override
  public void create() {
   // Gdx.app.log("Jogo","criado");
    Carregador.carregar();
    setScreen(new Tela());
  }
  @Override
  public void dispose(){
    super.dispose();
    Carregador.dispor();
  }
}