package com.anderson.passaro.mundo;

import com.anderson.passaro.Auxiliares.Carregador;
import com.anderson.passaro.objetos.Cano;
import com.anderson.passaro.objetos.Grama;
import com.anderson.passaro.objetos.ManipuladorRolagem;
import com.anderson.passaro.objetos.Passarinho;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by tachyon on 27/04/2015.
 */
public class Desenhista {
  private Mundo meumundo;
  private OrthographicCamera cam;
  private ShapeRenderer forma;
  private SpriteBatch sprite;
  private int pontoMeioY;
  private int jogoAltura;
  private Passarinho passarinho;
  private TextureRegion fundo,grama,caveiraBaixa,caveiraAlta,barra;
  private Animation animacao;
  private TextureRegion passaroMeio,passaroBaixo,passaroAlto;
  private ManipuladorRolagem rolador;
  private Grama gramafrente,gramafundo;
  private Cano cano1,cano2,cano3;

  public Desenhista(Mundo mundo,int jogoAltura,int pontoMeioY){
    meumundo=mundo;
    this.jogoAltura=jogoAltura;
    this.pontoMeioY=pontoMeioY;
    cam=new OrthographicCamera();
    cam.setToOrtho(true,136,jogoAltura);
    sprite=new SpriteBatch();
    sprite.setProjectionMatrix(cam.combined);
    forma=new ShapeRenderer();
    forma.setProjectionMatrix(cam.combined);
    //chama metodos auxiliares para inicializar instancias de variaveis
    iniciaObjetos();
    iniciaAtivos();
  }

  private void iniciaObjetos(){
    passarinho=meumundo.getPassarinho();
    rolador= meumundo.getRolador();
    gramafrente= rolador.getGramafrente();
    gramafundo= rolador.getGramafundo();
    cano1=rolador.getCano1();
    cano2=rolador.getCano2();
    cano3=rolador.getCano3();
  }
  private void iniciaAtivos(){
    fundo= Carregador.fundo;
    grama= Carregador.grama;
    animacao= Carregador.animacao;
    passaroMeio= Carregador.passaro;
    passaroBaixo= Carregador.passaroBaixo;
    passaroAlto= Carregador.passaroAlto;
    caveiraAlta= Carregador.caveiraAlto;
    caveiraBaixa= Carregador.caveiraBaixo;
    barra=Carregador.barra;
  }
  private void desenhaGrama(){
    sprite.draw(grama,gramafrente.getX(),gramafrente.getY(),gramafrente.getLargura(),gramafrente.getAltura());
    sprite.draw(grama, gramafundo.getX(), gramafundo.getY(), gramafundo.getLargura(), gramafundo.getAltura());
  }
  private void desenhaCaveira(){
    sprite.draw(caveiraAlta,cano1.getX()-1,cano1.getY()+cano1.getAltura()-14,24,14);
    sprite.draw(caveiraBaixa,cano1.getX()-1,cano1.getY()+cano1.getAltura()+45,24,14 );
    sprite.draw(caveiraAlta,cano2.getX()-1,cano2.getY()+cano2.getAltura()-14,24,14);
    sprite.draw(caveiraBaixa,cano2.getX()-1,cano2.getY()+cano2.getAltura()+45,24,14 );
    sprite.draw(caveiraAlta,cano3.getX()-1,cano3.getY()+cano3.getAltura()-14,24,14);
    sprite.draw(caveiraBaixa,cano3.getX()-1,cano3.getY()+cano3.getAltura()+45,24,14 );
  }
  private void desenhaCanos() {
    // Temporary code! Sorry about the mess :)
    // We will fix this when we finish the Pipe class.
    sprite.draw(barra,cano1.getX(),cano1.getY(),cano1.getLargura(),cano1.getAltura());
    sprite.draw(barra,cano1.getX(),cano1.getY()+cano1.getAltura()+45,cano1.getLargura(),pontoMeioY+66-(cano1.getAltura()+45));
    sprite.draw(barra,cano2.getX(),cano2.getY(),cano2.getLargura(),cano2.getAltura());
    sprite.draw(barra,cano2.getX(),cano2.getY()+cano2.getAltura()+45,cano2.getLargura(),pontoMeioY+66-(cano2.getAltura()+45));
    sprite.draw(barra,cano3.getX(),cano3.getY(),cano3.getLargura(),cano3.getAltura());
    sprite.draw(barra,cano3.getX(),cano3.getY()+cano3.getAltura()+45,cano3.getLargura(),pontoMeioY+66-(cano3.getAltura()+45));
  }

  /*public void desenha(Mundo mundo){
    meumundo=mundo;
    cam=new OrthographicCamera();
    cam.setToOrtho(true,136,204);
    sprite=new SpriteBatch();
    sprite.setProjectionMatrix(cam.combined);
    forma=new ShapeRenderer();
    forma.setProjectionMatrix(cam.combined);
  }*/

  public void desenha(float runTime) {
    // Fill the entire screen with black, to prevent potential flickering.
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Begin ShapeRenderer
    forma.begin(ShapeRenderer.ShapeType.Filled);

//    forma.setColor(Color.RED);
//    forma.circle(passarinho.getCirculo().x, passarinho.getCirculo().y, passarinho.getCirculo().radius);
    // Draw Background color
    forma.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
    forma.rect(0, 0, 136, pontoMeioY + 66);

    // Draw Grass
    forma.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
    forma.rect(0, pontoMeioY + 66, 136, 11);

    // Draw Dirt
    forma.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
    forma.rect(0, pontoMeioY + 77, 136, 52);

    // End ShapeRenderer
    forma.end();

    // Begin SpriteBatch
    sprite.begin();
    // Disable transparency
    // This is good for performance when drawing images that do not require
    // transparency.
    sprite.disableBlending();
    sprite.draw(Carregador.fundo, 0, pontoMeioY + 23, 136, 43);
    desenhaGrama();
    desenhaCanos();
    // The bird needs transparency, so we enable that again.
    sprite.enableBlending();
    desenhaCaveira();
    if(passarinho.naoBaterAsas()){
      sprite.draw(passaroMeio, passarinho.getX(), passarinho.getY(), passarinho.getLargura()/2.0f, passarinho.getAltura()/2.0f,
          passarinho.getLargura(), passarinho.getAltura(),1,1, passarinho.getRotacao());
    }
    else{
      sprite.draw(animacao.getKeyFrame(runTime), passarinho.getX(), passarinho.getY(), passarinho.getLargura()/2.0f,
          passarinho.getAltura()/2.0f, passarinho.getLargura(), passarinho.getAltura(),1,1, passarinho.getRotacao());
    }
    String placar=meumundo.getPlacar()+"";
    Carregador.sombra.draw(sprite,""+meumundo.getPlacar(),(136/2)-(3* placar.length()),12);
    Carregador.fonte.draw(sprite,""+meumundo.getPlacar(),(136/2)-(3* placar.length()-1),11);

    if (meumundo.estaPronto()) {
      // Draw shadow first
      Carregador.sombra.draw(sprite, "Toque me", (136 / 2)
          - (42), 76);
      // Draw text
      Carregador.fonte.draw(sprite, "Toque me", (136 / 2)
          - (42 - 1), 75);
    } else {

      if (meumundo.eGameOver()||meumundo.eHighScore()) {
        if(meumundo.eGameOver()) {
          Carregador.sombra.draw(sprite, "Game Over", 25, 56);
          Carregador.fonte.draw(sprite, "Game Over", 24, 55);

          Carregador.sombra.draw(sprite, "High Score:", 23, 106);
          Carregador.fonte.draw(sprite, "High Score:", 22, 105);

          String highScore = Carregador.getHighScore() + "";

          // Draw shadow first
          Carregador.sombra.draw(sprite, highScore, (136 / 2)
              - (3 * highScore.length()), 128);
          // Draw text
          Carregador.fonte.draw(sprite, highScore, (136 / 2)
              - (3 * highScore.length() - 1), 127);
        }
        else{
          Carregador.sombra.draw(sprite, "High Score!", 19, 56);
          Carregador.fonte.draw(sprite, "High Score!", 18, 55);
        }

          Carregador.sombra.draw(sprite, "Novamente?", 23, 76);
          Carregador.fonte.draw(sprite, "Novamente?", 24, 75);
        // Convert integer into String
        String score = meumundo.getPlacar() + "";

        // Draw shadow first
        Carregador.sombra.draw(sprite, "" + meumundo.getPlacar(), (136 / 2)
            - (3 * score.length()), 12);
        // Draw text
        Carregador.fonte.draw(sprite, "" + meumundo.getPlacar(), (136 / 2)
            - (3 * score.length() - 1), 11);


      }

      // Convert integer into String
      String score = meumundo.getPlacar() + "";

      // Draw shadow first
     Carregador.sombra.draw(sprite, "" + meumundo.getPlacar(), (136 / 2)
          - (3 * score.length()), 12);
      // Draw text
      Carregador.fonte.draw(sprite, "" + meumundo.getPlacar(), (136 / 2)
          - (3 * score.length() - 1), 11);
    }



    // End SpriteBatch
    sprite.end();
  /*  forma.begin(ShapeRenderer.ShapeType.Filled);

    forma.setColor(Color.RED);
    forma.circle(passarinho.getCirculo().x, passarinho.getCirculo().y, passarinho.getCirculo().radius);


    forma.rect(cano1.getBarraAlta().x,cano1.getBarraAlta().y,cano1.getBarraAlta().width,cano1.getBarraAlta().height);
    forma.rect(cano2.getBarraAlta().x,cano2.getBarraAlta().y,cano2.getBarraAlta().width,cano2.getBarraAlta().height);
    forma.rect(cano3.getBarraAlta().x,cano3.getBarraAlta().y,cano3.getBarraAlta().width,cano3.getBarraAlta().height);

    forma.rect(cano1.getBarraBaixa().x,cano1.getBarraBaixa().y,cano1.getBarraBaixa().width,cano1.getBarraBaixa().height);
    forma.rect(cano2.getBarraBaixa().x,cano2.getBarraBaixa().y,cano2.getBarraBaixa().width,cano2.getBarraBaixa().height);
    forma.rect(cano3.getBarraBaixa().x,cano3.getBarraBaixa().y,cano3.getBarraBaixa().width,cano3.getBarraBaixa().height);

    forma.rect(cano1.getCaveiraAlta().x,cano1.getCaveiraAlta().y,cano1.getCaveiraAlta().width,cano1.getCaveiraAlta().height);
    forma.rect(cano2.getCaveiraAlta().x,cano2.getCaveiraAlta().y,cano2.getCaveiraAlta().width,cano2.getCaveiraAlta().height);
    forma.rect(cano3.getCaveiraAlta().x,cano3.getCaveiraAlta().y,cano3.getCaveiraAlta().width,cano3.getCaveiraAlta().height);

    forma.rect(cano1.getCaveiraBaixa().x,cano1.getCaveiraBaixa().y,cano1.getCaveiraBaixa().width,cano1.getCaveiraBaixa().height);
    forma.rect(cano2.getCaveiraBaixa().x,cano2.getCaveiraBaixa().y,cano2.getCaveiraBaixa().width,cano2.getCaveiraBaixa().height);
    forma.rect(cano3.getCaveiraBaixa().x,cano3.getCaveiraBaixa().y,cano3.getCaveiraBaixa().width,cano3.getCaveiraBaixa().height);

    forma.end();*/
  }
}
