package com.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Gamemap;

public class Fonts {
    BitmapFont fuente;
    private float x, y;
    private String texto = "a";
    GlyphLayout glyLayout;

    public Fonts(String rutaFuente, int tamanoLetra, Color color) {
        generarTexto(rutaFuente, tamanoLetra, color);
    }

    private void generarTexto(String rutaFuente, int tamanoLetra, Color color) {

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = tamanoLetra;
        parameter.color = color;
        fuente = generator.generateFont(parameter);
        glyLayout = new GlyphLayout();

    }

    public void dibujar() {
        fuente.draw(Gamemap.batch, texto, x, y);
    }


    public void setColor(Color color) {
        fuente.setColor(color);
    }

}
