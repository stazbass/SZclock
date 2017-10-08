package com.szclock.demo.clock;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import javax.inject.Inject;

public class Clock implements Disposable {
    private Texture greenDot8;
    private Texture blueDot8;
    private Texture redDot8;
    private Texture goldenDot16;
    private CircleMath circleMath;
    private CurrentTime currentTime;
    int tick = 0;

    @Inject
    public Clock(CircleMath circleMath, CurrentTime currentTime) {
        this.circleMath = circleMath;
        this.currentTime = currentTime;
        this.greenDot8 = new Texture("GreenDot8.png");
        this.blueDot8 = new Texture("BlueDot8.png");
        this.redDot8 = new Texture("RedDot16.png");
        this.goldenDot16 = new Texture("GoldenDot16.png");
    }

    public void draw(SpriteBatch batch, int sizeX, int sizeY) {
        tick += 1;

        double originX = sizeX / 2;
        double originY = sizeY / 2;

        batch.begin();


        double scale = 16;
        drawClockFields(batch, originX, originY, scale);
        drawMinutes(batch, originX, originY, scale);
        drawSeconds(batch, originX, originY, scale);
        batch.end();
    }

    private void drawSeconds(SpriteBatch batch, double originX, double originY, double scale) {
        int secondsNow = currentTime.getSeconds();
        int milliseconds = currentTime.getMilliseconds();
        System.out.println(milliseconds);
        double secondsArrowDegree = (double)secondsNow + (double)milliseconds/1000.0;

        for (int secondIterator = 0; secondIterator < secondsNow; secondIterator++) {
            double degree = Math.PI / 2.0 - secondsArrowDegree * Math.PI * 2.0 / 60.0;
            Vector2 pos = circleMath.getPointOnCircle(originX, originY, degree, scale * secondIterator + 1);
            batch.draw(blueDot8, pos.x - 4, pos.y - 4);
        }
    }

    private void drawClockFields(SpriteBatch batch, double originX, double originY, double scale) {
        for (int minutesIterator = 0; minutesIterator < 60; minutesIterator++) {
            for (int secondIterator = 0; secondIterator < 60; secondIterator++) {
                double degree = minutesIterator * Math.PI * 2 / 60.0 - Math.PI / 2;
                Vector2 pos = circleMath.getPointOnCircle(originX, originY, degree, scale * secondIterator + 1);
                batch.draw(redDot8, pos.x - 8, pos.y - 8);
            }
        }
    }

    private void drawMinutes(SpriteBatch batch, double originX, double originY, double scale) {
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        double degreeToHours = Math.PI / 2.0 - hour * Math.PI * 2.0 / 12.0;

        for (int previousMinuteIterator = 0; previousMinuteIterator < minute; previousMinuteIterator++) {
            Vector2 pos = circleMath.getPointOnCircle(originX, originY, degreeToHours, scale * previousMinuteIterator + 1);
            batch.draw(goldenDot16, pos.x - 8, pos.y - 8);
        }
    }

    @Override
    public void dispose() {
        greenDot8.dispose();
        blueDot8.dispose();
    }
}
