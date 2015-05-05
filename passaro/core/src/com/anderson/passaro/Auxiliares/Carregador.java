package com.anderson.passaro.Auxiliares;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by tachyon on 01/05/2015.
 */
public class Carregador {
  public static Texture textura;
  public static TextureRegion fundo, grama;
  public static Animation animacao;
  public static TextureRegion passaro, passaroBaixo, passaroAlto;
  public static TextureRegion caveiraAlto, caveiraBaixo, barra;
  public  static Sound morte,moeda,flap;
  public static BitmapFont fonte,sombra;
  public static Preferences prefs;

  public static void carregar() {
    textura = new Texture(Gdx.files.internal("textura.png"));
    textura.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

    fundo = new TextureRegion(textura, 0, 0, 136, 43);
    fundo.flip(false, true);

    grama = new TextureRegion(textura, 0, 43, 143, 11);
    grama.flip(false, true);

    passaroBaixo = new TextureRegion(textura, 136, 0, 17, 12);
    passaroBaixo.flip(false, true);

    passaro = new TextureRegion(textura, 153, 0, 17, 12);
    passaro.flip(false, true);

    passaroAlto = new TextureRegion(textura, 170, 0, 17, 12);
    passaroAlto.flip(false, true);

    TextureRegion[] passaros = {passaroBaixo, passaro, passaroAlto};
    animacao = new Animation(0.06f, passaros);
    animacao.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    caveiraAlto = new TextureRegion(textura, 192, 0, 24, 14);
    // Cria por flipping caveiraAlto
    caveiraBaixo = new TextureRegion(caveiraAlto);
    caveiraBaixo.flip(false, true);

    barra = new TextureRegion(textura, 136, 16, 22, 3);
    barra.flip(false, true);
    morte=Gdx.audio.newSound(Gdx.files.internal("morte.wav"));
    moeda=Gdx.audio.newSound(Gdx.files.internal("moeda.wav"));
    flap=Gdx.audio.newSound(Gdx.files.internal("flap.wav"));
    fonte=new BitmapFont(Gdx.files.internal("text.fnt"));
    fonte.getData().setScale(.25f,-.25f);
    sombra=new BitmapFont(Gdx.files.internal("shadow.fnt"));
    sombra.getData().setScale(.25f,-.25f);

    prefs=Gdx.app.getPreferences("passaro");
    if(!prefs.contains("highScore")){
      prefs.putInteger("highScore",0);
    }
  }

  public static void dispor() {
    // dispor a textura quando terminado
    textura.dispose();
    morte.dispose();
    moeda.dispose();
    flap.dispose();
    fonte.dispose();
    sombra.dispose();
  }

  public static void setHighScore(int val){
    prefs.putInteger("highScore",val);
    prefs.flush();
  }

  public static int getHighScore(){
    return prefs.getInteger("highScore");
  }
}
