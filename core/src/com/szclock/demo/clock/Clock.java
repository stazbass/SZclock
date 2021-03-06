package com.szclock.demo.clock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.szclock.demo.TextureManager;

import javax.inject.Inject;

public class Clock implements Disposable {
    long tick = 0;
    private Texture blueDot8;
    private Texture redDot8;
    private Texture goldenDot16;
    private CircleMath circleMath;
    private CurrentTime currentTime;
    private TextureManager textureManager;
    private double scaleFactor = 1.0;

    @Inject
    public Clock(TextureManager textureManager, CircleMath circleMath, CurrentTime currentTime) {
        this.circleMath = circleMath;
        this.currentTime = currentTime;
        this.textureManager = textureManager;
    }

    public void init() {
        this.blueDot8 = textureManager.loadTexture("BlueDot8.png");
        this.redDot8 = textureManager.loadTexture("RedDot8.png");
        this.goldenDot16 = textureManager.loadTexture("GoldenDot16.png");
    }

    public void draw(SpriteBatch batch, int sizeX, int sizeY) {
        tick += 1;

        double originX = sizeX / 2;
        double originY = sizeY / 2;

        batch.begin();
        double scale = 16;
        scaleFactor = getScaleValue(currentTime.getMilliseconds(), getInterpolation());
        drawClockFields(batch, originX, originY, scale);
        drawMinutes(batch, originX, originY, scale);
        drawSeconds(batch, originX, originY, scale);
        drawMilliseconds(batch, originX, originY, scale);
        batch.end();
    }

    private void drawSeconds(SpriteBatch batch, double originX, double originY, double scale) {
        int secondsNow = currentTime.getSeconds();
        long milliseconds = currentTime.getMilliseconds();

        double secondsArrowDegree = (double) secondsNow + (double) milliseconds / 1000.0;
        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            double degree = Math.PI / 2.0 - secondsArrowDegree * Math.PI * 2.0 / 60.0;
            float x = (float) circleMath.getPointOnCircleX(originX, originY, degree, scale * secondIterator + 1);
            float y = (float) circleMath.getPointOnCircleY(originX, originY, degree, scale * secondIterator + 1);
            drawCentered(batch, blueDot8, x, y, (float) scaleFactor);
        }

        long millisecondsInSecond = milliseconds % 1000;
        double dtime = millisecondsInSecond <= 500 ? millisecondsInSecond / 500.0 : (1000 - millisecondsInSecond) / 500.0;
        double currentSecondArrowScale = Interpolation.smoother.apply((float) dtime);

        for (int j = 1; j <= secondsNow; j++) {
            for (int secondIterator = 0; secondIterator < j * currentSecondArrowScale; secondIterator++) {
                double degree = Math.PI / 2.0 - j * Math.PI * 2.0 / 60.0;
                float x = (float) circleMath.getPointOnCircleX(originX, originY, degree, scale * secondIterator + 1);
                float y = (float) circleMath.getPointOnCircleY(originX, originY, degree, scale * secondIterator + 1);
                drawCentered(batch, blueDot8, x, y, (float) scaleFactor);
            }
        }
    }

    private void drawMilliseconds(SpriteBatch batch, double originX, double originY, double scale) {
        int secondsNow = currentTime.getSeconds();
        long milliseconds = currentTime.getMilliseconds();
        double secondsArrowDegree = (double) secondsNow + (double) milliseconds / 1000.0;
        double secondIterator = secondsNow - (1 - milliseconds / 1000.0);
        double degree = Math.PI / 2.0 - secondsArrowDegree * Math.PI * 2.0 / 60.0;
        float x = (float) circleMath.getPointOnCircleX(originX, originY, degree, scale * secondIterator + 1);
        float y = (float) circleMath.getPointOnCircleY(originX, originY, degree, scale * secondIterator + 1);
        drawCentered(batch, blueDot8, x, y, (float) scaleFactor);
    }

    private void drawClockFields(SpriteBatch batch, double originX, double originY, double scale) {
        for (int minutesIterator = 0; minutesIterator < 60; minutesIterator++) {
            for (int secondIterator = 0; secondIterator < 60; secondIterator++) {
                double degree = minutesIterator * Math.PI * 2 / 60.0 - Math.PI / 2;
                float x = (float) circleMath.getPointOnCircleX(originX, originY, degree, scale * secondIterator + 1);
                float y = (float) circleMath.getPointOnCircleY(originX, originY, degree, scale * secondIterator + 1);
                drawCentered(batch, redDot8, x, y, (float) scaleFactor);
            }
        }
    }

    private void drawMinutes(SpriteBatch batch, double originX, double originY, double scale) {
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        double degreeToHours = Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0;

        for (int previousMinuteIterator = 0; previousMinuteIterator < minute; previousMinuteIterator++) {
            float x = (float) circleMath.getPointOnCircleX(originX, originY, degreeToHours, scale * previousMinuteIterator + 1);
            float y = (float) circleMath.getPointOnCircleY(originX, originY, degreeToHours, scale * previousMinuteIterator + 1);
            drawCentered(batch, goldenDot16, x, y, (float) scaleFactor);
        }
    }

    private void drawCentered(SpriteBatch batch, Texture texture, float x, float y, float scale) {
        batch.draw(texture, x - texture.getWidth() / 2.0f * scale, y - texture.getHeight() / 2.0f * scale, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    private double getScaleValue(long milliseconds, Interpolation interpolation) {
        long millisecondsInSecond = (milliseconds) % 1000;
        double dtime = millisecondsInSecond <= 500 ? millisecondsInSecond / 500.0 : (1000 - millisecondsInSecond) / 500.0;

        return interpolation.apply((float) dtime) + 0.3;
    }

    private Interpolation getInterpolation() {
        return Interpolation.smooth;
    }

    @Override
    public void dispose() {
    }
}
