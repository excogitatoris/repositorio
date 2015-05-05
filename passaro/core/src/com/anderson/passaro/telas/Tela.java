package com.anderson.passaro.telas;

import com.anderson.passaro.Auxiliares.Entrada;
import com.anderson.passaro.mundo.Desenhista;
import com.anderson.passaro.mundo.Mundo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by tachyon on 27/04/2015.
 */
public class Tela implements Screen {
private Mundo mundo;
private Desenhista desenhista;
private float runTime;

public Tela(){
  float larguratela =Gdx.graphics.getWidth();
  float alturatela=Gdx.graphics.getHeight();
  float largurajogo=136;
  float alturajogo=alturatela/(larguratela/largurajogo);
  int pontoMedioY=(int) (alturajogo/2);
  mundo=new Mundo(pontoMedioY);
  desenhista=new Desenhista(mundo,(int)alturajogo,pontoMedioY);
  Gdx.input.setInputProcessor(new Entrada(mundo));
}
  @Override
  public void show() {
    //Gdx.app.log("Tela", "mostra");
  }
  @Override
  public void render(float delta) {
    runTime+=delta;
    mundo.update(delta);
    desenhista.desenha(runTime);
  }
  @Override
  public void resize(int width, int height) {
    //Gdx.app.log("Tela", "resizing");
  }
  @Override
  public void pause() {
    //Gdx.app.log("Tela", "pausa");
  }
  @Override
  public void resume() {
    //Gdx.app.log("Tela", "continua");
  }
  @Override
  public void hide() {
    //Gdx.app.log("Tela", "esconde");
  }
  @Override
  public void dispose() {

  }
}
