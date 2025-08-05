package geometria;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.text.Text;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;

public class Visualizador3D {

    private static void configurarIluminacion(Group grupo3D) {
        // Luz ambiental
        AmbientLight ambientLight = new AmbientLight(Color.WHITE);

        // Luz puntual
        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(100);
        pointLight.setTranslateY(-100);
        pointLight.setTranslateZ(-300);

        grupo3D.getChildren().addAll(ambientLight, pointLight);
    }

    private static SubScene configurarEscena3D(Group grupo3D, double translateZ) {
        // Configurar la escena 3D con fondo negro
        SubScene escena3D = new SubScene(grupo3D, 300, 300, true, SceneAntialiasing.BALANCED);
        escena3D.setFill(Color.BLACK);

        // Configurar cámara
        PerspectiveCamera camara = new PerspectiveCamera(true);
        camara.setTranslateZ(translateZ);
        camara.setTranslateY(-100);
        camara.setNearClip(0.1);
        camara.setFarClip(2000.0);
        escena3D.setCamera(camara);

        return escena3D;
    }

    public static SubScene crearEscenaCono(double radio, double altura) {
        Group grupo3D = new Group();

        // Crear el cono con material brillante
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DODGERBLUE);
        material.setSpecularColor(Color.WHITE);
        material.setSpecularPower(32.0);

        Cylinder cono = new Cylinder(radio, altura, 64);
        cono.setMaterial(material);
        cono.setDrawMode(DrawMode.FILL);
        cono.setTranslateY(-altura / 2);

        // Líneas de medición en blanco
        Line lineaAltura = new Line(radio, 0, radio, -altura);
        lineaAltura.setStroke(Color.WHITE);
        lineaAltura.setStrokeWidth(2);

        Line lineaRadio = new Line(0, 0, radio, 0);
        lineaRadio.setStroke(Color.WHITE);
        lineaRadio.setStrokeWidth(2);

        // Etiquetas en blanco
        Text textoAltura = new Text("h=" + String.format("%.2f", altura));
        textoAltura.setFill(Color.WHITE);
        textoAltura.setTranslateX(radio + 5);
        textoAltura.setTranslateY(-altura / 2);

        Text textoRadio = new Text("r=" + String.format("%.2f", radio));
        textoRadio.setFill(Color.WHITE);
        textoRadio.setTranslateX(radio / 2);
        textoRadio.setTranslateY(15);

        grupo3D.getChildren().addAll(cono, lineaAltura, lineaRadio, textoAltura, textoRadio);
        configurarIluminacion(grupo3D);

        // Rotar para mejor visualización
        grupo3D.getTransforms().addAll(
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(45, Rotate.Y_AXIS));

        return configurarEscena3D(grupo3D, -Math.max(altura, radio) * 4);
    }

    public static SubScene crearEscenaCilindro(double radio, double altura) {
        Group grupo3D = new Group();

        // Crear el cilindro con material brillante
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DODGERBLUE);
        material.setSpecularColor(Color.WHITE);
        material.setSpecularPower(32.0);

        Cylinder cilindro = new Cylinder(radio, altura, 64);
        cilindro.setMaterial(material);
        cilindro.setDrawMode(DrawMode.FILL);
        cilindro.setTranslateY(-altura / 2);

        // Líneas de medición en blanco
        Line lineaAltura = new Line(radio, 0, radio, -altura);
        lineaAltura.setStroke(Color.WHITE);
        lineaAltura.setStrokeWidth(2);

        Line lineaRadio = new Line(0, 0, radio, 0);
        lineaRadio.setStroke(Color.WHITE);
        lineaRadio.setStrokeWidth(2);

        // Etiquetas en blanco
        Text textoAltura = new Text("h=" + String.format("%.2f", altura));
        textoAltura.setFill(Color.WHITE);
        textoAltura.setTranslateX(radio + 5);
        textoAltura.setTranslateY(-altura / 2);

        Text textoRadio = new Text("r=" + String.format("%.2f", radio));
        textoRadio.setFill(Color.WHITE);
        textoRadio.setTranslateX(radio / 2);
        textoRadio.setTranslateY(15);

        grupo3D.getChildren().addAll(cilindro, lineaAltura, lineaRadio, textoAltura, textoRadio);
        configurarIluminacion(grupo3D);

        // Rotar para mejor visualización
        grupo3D.getTransforms().addAll(
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(45, Rotate.Y_AXIS));

        return configurarEscena3D(grupo3D, -Math.max(altura, radio) * 4);
    }

    public static SubScene crearEscenaEsfera(double radio) {
        Group grupo3D = new Group();

        // Crear la esfera con material brillante
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.DODGERBLUE);
        material.setSpecularColor(Color.WHITE);
        material.setSpecularPower(32.0);

        Sphere esfera = new Sphere(radio, 64);
        esfera.setMaterial(material);
        esfera.setDrawMode(DrawMode.FILL);

        // Línea de radio en blanco
        Line lineaRadio = new Line(0, 0, radio, 0);
        lineaRadio.setStroke(Color.WHITE);
        lineaRadio.setStrokeWidth(2);

        // Etiqueta en blanco
        Text textoRadio = new Text("r=" + String.format("%.2f", radio));
        textoRadio.setFill(Color.WHITE);
        textoRadio.setTranslateX(radio / 2);
        textoRadio.setTranslateY(15);

        grupo3D.getChildren().addAll(esfera, lineaRadio, textoRadio);
        configurarIluminacion(grupo3D);

        // Rotar para mejor visualización
        grupo3D.getTransforms().addAll(
                new Rotate(-20, Rotate.X_AXIS),
                new Rotate(45, Rotate.Y_AXIS));

        return configurarEscena3D(grupo3D, -radio * 4);
    }
}