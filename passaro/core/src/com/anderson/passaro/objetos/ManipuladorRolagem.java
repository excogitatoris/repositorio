package com.anderson.passaro.objetos;

import com.anderson.passaro.Auxiliares.Carregador;
import com.anderson.passaro.mundo.Mundo;

/**
 * Created by tachyon on 02/05/2015.
 */
public class ManipuladorRolagem {
  // ScrollHandler will create all five objects that we need.
  private Grama gramafrente, gramafundo;
  private Cano cano1, cano2, cano3;
  private Mundo mundo;

  // ScrollHandler will use the constants below to determine
  // how fast we need to scroll and also determine
  // the size of the gap between each pair of pipes.

  // Capital letters are used by convention when naming constants.
  public static final int VELOCIDADEROLAGEM = -59;
  public static final int INTERVALOCANO = 49;

  // Constructor receives a float that tells us where we need to create our
  // Grass and Pipe objects.
  public ManipuladorRolagem(Mundo mundo,float yPos) {
    this.mundo=mundo;
    gramafrente = new Grama(0, yPos, 143, 11, VELOCIDADEROLAGEM);
    gramafundo = new Grama(gramafrente.getExremidadeX(), yPos, 143, 11,VELOCIDADEROLAGEM);

    cano1 = new Cano(210, 0, 22, 60, VELOCIDADEROLAGEM,yPos);
    cano2 = new Cano(cano1.getExremidadeX() + INTERVALOCANO, 0, 22, 70, VELOCIDADEROLAGEM,yPos);
    cano3 = new Cano(cano2.getExremidadeX() + INTERVALOCANO, 0, 22, 60, VELOCIDADEROLAGEM,yPos);
  }

  public void update(float delta) {
    // Update our objects
    gramafrente.update(delta);
    gramafundo.update(delta);
    cano1.update(delta);
    cano2.update(delta);
    cano3.update(delta);

    // Check if any of the pipes are scrolled left,
    // and reset accordingly
    if (cano1.eRolagemEsquerda()) {
      cano1.reset(cano3.getExremidadeX() + INTERVALOCANO);
    } else if (cano2.eRolagemEsquerda()) {
      cano2.reset(cano1.getExremidadeX() + INTERVALOCANO);

    } else if (cano3.eRolagemEsquerda()) {
      cano3.reset(cano2.getExremidadeX() + INTERVALOCANO);
    }

    // Same with grass
    if (gramafrente.eRolagemEsquerda()) {
      gramafrente.reset(gramafundo.getExremidadeX());

    } else if (gramafundo.eRolagemEsquerda()) {
      gramafundo.reset(gramafrente.getExremidadeX());
    }
  }

  public void parar(){
    gramafrente.parar();
    gramafundo.parar();
    cano1.parar();
    cano2.parar();
    cano3.parar();
  }

  public boolean colisao(Passarinho passarinho){
    if(!cano1.getContabilizado()&&cano1.getX()+(cano1.getLargura()/2)< passarinho.getX()+ passarinho.getLargura()){
      aumentaplacar(1);
      cano1.setContabilizado(true);
      Carregador.moeda.play();
    } else if(!cano2.getContabilizado()&&cano2.getX()+(cano2.getLargura()/2)< passarinho.getX()+ passarinho.getLargura()){
      aumentaplacar(1);
      cano2.setContabilizado(true);
      Carregador.moeda.play();
    } else if(!cano3.getContabilizado()&&cano3.getX()+(cano3.getLargura()/2)< passarinho.getX()+ passarinho.getLargura()){
      aumentaplacar(1);
      cano3.setContabilizado(true);
      Carregador.moeda.play();
    }
    return (cano1.colisao(passarinho)||cano2.colisao(passarinho)||cano3.colisao(passarinho));
  }

  private void aumentaplacar(int incremento){
    mundo.aumentaPlacar(incremento);
  }
  // The getters for our five instance variables
  public Grama getGramafrente() {
    return gramafrente;
  }

  public Grama getGramafundo() {
    return gramafundo;
  }

  public Cano getCano1() {
    return cano1;
  }

  public Cano getCano2() {
    return cano2;
  }

  public Cano getCano3() {
    return cano3;
  }

  public void onReinicio() {
    gramafrente.onReinicio(0, VELOCIDADEROLAGEM);
    gramafundo.onReinicio(gramafrente.getExremidadeX(), VELOCIDADEROLAGEM);
    cano1.onReinicio(210, VELOCIDADEROLAGEM);
    cano2.onReinicio(cano1.getExremidadeX() + INTERVALOCANO, VELOCIDADEROLAGEM);
    cano3.onReinicio(cano2.getExremidadeX() + INTERVALOCANO, VELOCIDADEROLAGEM);
  }
}
