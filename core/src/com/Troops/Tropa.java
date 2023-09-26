package com.Troops;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.game.Gamemap;

public class Tropa extends Actor {
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    private Vector2 velocityVec;
    private Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    private Polygon boundaryPolygon;

    private int col = -1;
    private int row = -1;

    protected float blood = 100;
    protected float speed = 0.0f;

    private static Rectangle worldBounds;

    public Tropa(float x, float y, Stage s) {
        super();

        setPosition(x, y);
        s.addActor(this);

        animation = null;
        elapsedTime = 0;
        animationPaused = false;

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;

        boundaryPolygon = null;
    }

    public boolean isAlive() {
        return (blood > 0);
    }

    private float[] getCenter() {
        return new float[] { getX() + getWidth() / 2, getY() + getHeight() / 2 };
    }

    public float getCenterX() {
        return getCenter()[0];
    }

    public float getCenterY() {
        return getCenter()[1];
    }

    public void centerAtPosition(float x, float y) {
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

    public void centerAtActor(Tropa other) {
        centerAtPosition(other.getCenterX(), other.getCenterY());
    }

    /**
     * Setea la animacion usada al renderizar este Actor
     * Setea el tamaño del Actor
     *
     * @param anim Animación que será dibujada al redenrizar
     */
    public void setAnimation(Animation<TextureRegion> anim) {
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);

        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();

        setSize(w, h);
        setOrigin(w / 2, h / 2);

        if (boundaryPolygon == null)
            setBoundaryRectangle();
    }

    /**
     * Crea la animación apartir de imagenes guardados en archivos separados
     *
     * @param game
     * @param frameDuration
     * @param loop
     * @return Animación
     */
    public Animation<TextureRegion> loadAnimation(Gamemap game, float frameDuration, boolean loop) {
        Animation<TextureRegion> anim = new Animation<TextureRegion>(frameDuration, game.assets.slimewalk);

        if (loop)
            anim.setPlayMode(PlayMode.LOOP);
        else
            anim.setPlayMode(PlayMode.NORMAL);

        if (animation == null)
            setAnimation(anim);

        return anim;
    }


    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }

    public boolean isAnimationFinshed() {
        return animation.isAnimationFinished(elapsedTime);
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    public void setAcceleration(float acc) {
        acceleration = acc;
    }

    public void setDeceleration(float dec) {
        deceleration = dec;
    }

    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }

    public void setSpeed(float speed) {
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }

    public float getSpeed() {
        return velocityVec.len();
    }

    public boolean isMoving() {
        return (getSpeed() > 0);
    }

    public void setMotionAngle(float angle) {
        velocityVec.setAngle(angle);
    }

    public float getMotionAngle() {
        return velocityVec.angle();
    }

    public void accelerateAtAngle(float angle) {
        accelerationVec.add(
                new Vector2(acceleration, 0).setAngle(angle));
    }

    public void accelerateForward() {
        accelerateAtAngle(getRotation());
    }

    public void applyPhysics(float dt) {
        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

        float speed = getSpeed();

        if (accelerationVec.len() == 0)
            speed -= deceleration * dt;

        speed = MathUtils.clamp(speed, 0, maxSpeed);

        setSpeed(speed);

        moveBy(velocityVec.x * dt, velocityVec.y * dt);

        accelerationVec.set(0, 0);
    }

    public void setBoundaryRectangle() {
        float w = getWidth();
        float h = getHeight();

        float[] vertices = { 0, 0, w, 0, w, h, 0, h };
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numSides) {
        float w = getWidth();
        float h = getHeight();

        float[] vertices = new float[2 * numSides];
        for (int i = 0; i < numSides; i++) {
            float angle = i * 6.28f / numSides;
            // x-coordinate
            vertices[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;
            // y-coordinate
            vertices[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
        }
        boundaryPolygon = new Polygon(vertices);

    }

    public Polygon getBoundaryPolygon() {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return boundaryPolygon;
    }

    public boolean overlaps(Tropa other) {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return false;

        return Intersector.overlapConvexPolygons(poly1, poly2);
    }

    public Vector2 preventOverlap(Tropa other) {
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return null;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if (!polygonOverlap)
            return null;

        this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
        return mtv.normal;
    }

    public static void setWorldBounds(float width, float height) {
        worldBounds = new Rectangle(0, 0, width, height);
    }

    public static void setWorldBounds(Tropa ba) {
        setWorldBounds(ba.getWidth(), ba.getHeight());
    }

    public void boundToWorld() {
        if (getX() < 0)
            setX(0);
        if (getX() + getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());
        if (getY() < 0)
            setY(0);
        if (getY() + getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    public void alignCamera() {
        Camera cam = this.getStage().getCamera();

        cam.position.set(this.getX() + this.getOriginX(), this.getY() + this.getOriginY(), 0);

        cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth / 2,
                worldBounds.width - cam.viewportWidth / 2);
        cam.position.y = MathUtils.clamp(cam.position.y, cam.viewportHeight / 2,
                worldBounds.height - cam.viewportHeight / 2);
        cam.update();
    }

    private static String getFullClassName(String className) {
        String fullClassName = className;
        if (!className.contains("com")) {
            fullClassName = "com.game.actors." + fullClassName;
        }
        return fullClassName;
    }

    public static ArrayList<Tropa> getList(Stage stage, String className) {
        ArrayList<Tropa> list = new ArrayList<Tropa>();

        Class theClass = null;
        try {
            theClass = Class.forName(getFullClassName(className));
            for (Actor a : stage.getActors()) {
                if (theClass.isInstance(a))
                    list.add((Tropa) a);
            }
        } catch (Exception error) {
            error.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Tropa> getAliveList(Stage stage, String className) {
        ArrayList<Tropa> list = getList(stage, className);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Tropa baseActor = (Tropa) iterator.next();
            if (!baseActor.isAlive()) {
                iterator.remove();
            }
        }
        return list;
    }

    public static int count(Stage stage, String className) {
        return getList(stage, className).size();
    }

    public void act(float dt) {
        super.act(dt);

        if (!animationPaused)
            elapsedTime += dt;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (animation != null && isVisible())
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean checkOutSideScreen() {
        if (getX() - getWidth() > 1200 || getY() - getHeight() > 600
                || getX() + getWidth() < 0 || getY() + getHeight() < 0) {
            return true;
        }
        return false;
    }
}
