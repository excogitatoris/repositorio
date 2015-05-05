package com.anderson.passaro.Auxiliares;

import com.anderson.passaro.mundo.Mundo;
import com.anderson.passaro.objetos.Passarinho;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by tachyon on 01/05/2015.
 */
public class Entrada implements InputProcessor {
  private Passarinho meuPassarinho;
  private Mundo meumundo;
  public Entrada(Mundo mundo){
    meumundo=mundo;
    meuPassarinho=meumundo.getPassarinho();
  }
  @Override
  public boolean keyDown(int keycode) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    if(meumundo.estaPronto()){
      meumundo.inicio();
    }
    meuPassarinho.onClick();
    if(meumundo.eGameOver()||meumundo.eHighScore()){
      meumundo.reinicio();
    }
    return true; //retorna true pra dizer que o toque foi manipulado
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
}
