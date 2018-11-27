package com.burakarslan.bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;


public class SurvivorBird extends ApplicationAdapter {


    SpriteBatch batch;// çizmemiz için yardımcı olan methodlar objeler
    //istediğimiz gibi kuşu arka planı çizmemize yarar.
    Texture backGround;
    Texture bird;
    Texture bird2;
    Texture bird3;
    Texture bird4;
    Texture bullet;
    Texture bullet2;
    Texture bullet3;
    Texture bullet4;

    float birdX = 0;
    float birdY = 0;
    int gameStart = 0;
    float velocity = 0;
    float gravity = 0.3f;
    TextureRegion[] kanatCirpmasi = new TextureRegion[4];
    private int birdPicture;
    Random random;

    int numberOfEnemys = 5;
    float[] enemyX = new float[numberOfEnemys];
    float[] enemyOffSet = new float[numberOfEnemys];
    float[] enemyOffSet2 = new float[numberOfEnemys];
    float[] enemyOffSet3 = new float[numberOfEnemys];
    float[] enemyOffSet4 = new float[numberOfEnemys];
    float distance = 0;
    float enemyVelocity = 20;


    @Override
    public void create() {
        try {
            batch = new SpriteBatch();
            backGround = new Texture("arkaplan.png");
            bird = new Texture(Gdx.files.internal("bird.png"));
            bird2 = new Texture(Gdx.files.internal("bird2.png"));
            bird3 = new Texture(Gdx.files.internal("bird3.png"));
            bird4 = new Texture(Gdx.files.internal("bird4.png"));
            bullet = new Texture("bullet.png");
            bullet2 = new Texture("bullet.png");
            bullet3 = new Texture("bullet.png");
            bullet4 = new Texture("bullet.png");
            random = new Random();

            distance = Gdx.graphics.getWidth(); // iki tane mermi arasında ekranın yarısı kadar mesafe belirledik

            kanatCirpmasi[0] = new TextureRegion(bird);
            kanatCirpmasi[1] = new TextureRegion(bird2);//yarasanın kanat çırpması için tanımladığım kısım
            kanatCirpmasi[2] = new TextureRegion(bird3);
            kanatCirpmasi[3] = new TextureRegion(bird4);

            birdX = Gdx.graphics.getWidth() / 3 - bird.getHeight() / 2;
            birdY = Gdx.graphics.getHeight() / 3;


            for (int i = 0; i < numberOfEnemys; i++) {

                enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                enemyX[i] = Gdx.graphics.getWidth() - bullet.getWidth() / 3 + i * distance; // her oluşan kurşun seti distance seti otomatik olarak ayarlanmış olucak
            }


        } catch (Exception ex) {

        }
    }

    @Override
    public void render() {
        try {
            batch.begin();
            batch.draw(backGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            if (gameStart == 1) {//Oyun başladıysa.

                if (Gdx.input.justTouched()) {
                    velocity = -10;
                }

                for (int i = 0; i < numberOfEnemys; i++) {

                    if (enemyX[i] < -bullet.getWidth()) {
                        enemyX[i] = enemyX[i] + numberOfEnemys * distance;

                        enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                        enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                        enemyOffSet3[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);
                        enemyOffSet4[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - 200);

                    } else {

                        enemyX[i] = enemyX[i] - enemyVelocity;

                        batch.draw(bullet, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                        batch.draw(bullet2, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet2[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                        batch.draw(bullet3, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                        batch.draw(bullet4, enemyX[i], Gdx.graphics.getHeight() / 2 + enemyOffSet3[i], Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
                    }
                }


                if (birdY > 0 || velocity < 0) {
                    velocity = velocity + gravity;
                    birdY = birdY - velocity;
                }

            } else {
                if (Gdx.input.justTouched()) {
                    gameStart = 1;
                }
            }

            birdPicture++;
            if (birdPicture == 4) {
                birdPicture = 0;
            }

            for (int i = 0; i < birdPicture; i++) {
                batch.draw(kanatCirpmasi[birdPicture], birdX, birdY, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
            }

            batch.end();
        } catch (Exception ex) {

        }
    }

    @Override
    public void dispose() {

    }
}
