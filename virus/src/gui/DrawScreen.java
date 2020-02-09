package gui;

import domein.Attractor;
import domein.Particle;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.scene.text.*; 

public class DrawScreen extends Canvas {

    private GraphicsContext gtx;
    
    private int amount_particles = 0;

    private final List<Particle> particles;
    private Attractor attractor;

    public DrawScreen() {
        particles = new ArrayList<>();
        init();
        build();
    }

    private void init() {
        this.setWidth(1920);
        this.setHeight(1000);
        gtx = this.getGraphicsContext2D();
    }
    

    private void build() {
        attractor = new Attractor(this.getWidth() / 2, this.getHeight() / 2);

        this.setOnMouseMoved(e -> {
             attractor.setX(e.getX());
             attractor.setY(e.getY());
        });
        
	    gtx.setFill(Color.rgb(0, 0, 0, 1));
	    gtx.fillRect(0, 0, this.getWidth(), this.getHeight());
	    
	    Point p = MouseInfo.getPointerInfo().getLocation();
	    attractor.setX(p.x);
        attractor.setY(p.y);

	    amount_particles = 10000;
	    for (int i = 0; i < amount_particles; i++) {
	    		createParticle(500, 500, attractor);
	    }

    }

    private void createParticle(double x, double y, Attractor a) {
        Particle p = new Particle(x, y, a);
        particles.add(p);
    }

    public void draw() {
        gtx.setFill(Color.rgb(0, 0, 0, 0.5));
        gtx.fillRect(0, 0, this.getWidth(), this.getHeight());

        particles.forEach(p -> {
            p.draw(gtx);
        });
        
        //gtx.fillText("CoronaVirus", 100, 100);
        //gtx.setTextAlign(TextAlignment.CENTER);;
    }

    public void update() {
        particles.forEach(p -> {
            p.update();
        });
    }

}