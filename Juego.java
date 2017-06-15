import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;  
import javafx.scene.shape.Circle;                       
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import java.io.File;
/**
 * Write a description of class Juego here.
 * Para la creacion de este projecto me he basado en el codigo de la actividad hecha en clase de manera que fuerse mas correcta que la que hice yo
 * @author (Cristian) 
 * @version (a version number or a date)
 */
public class Juego extends Application
{
    private static final int ANCHO_PANTALLA = 800; //Consante para la anchura de la pantalla del juego
    private static final int ALTO_PANTALLA = 500;  //Consante para la altura de la pantalla del juego
    private Timeline animacion;
    private int numBolasDestruidas; //Atributo para controlar el numero de bolas que hemos destruido
    private int tiempoEnSegundos; //Atributo para el contador del juego
    private int VelocidadDisparo; //Atributo para la velocidad del disparo
    private Timeline animacionDisparo; 
    private static final int ALTO_BARRA_SUPERIOR = 20; //Constante para la barra virtual de arriba con el fin de que las bolas no toquen ni el contador de tiempo ni el de las bolas destruidas
    private ArrayList<Bola> bolas; //ArrayLista donde guardaremos las bolas del juego
    private static final int NUM_BOLAS_CREAR = 5; //Constante que controla el numero de bolas que queremos crear
    private static final int ANCHO_BLOQUE_AYUDA = 40; //Constante para la anchura de la imagen de ayuda    
    private static final int ALTO_BLOQUE_AYUDA = 40; //Constante para la altura de la imagen de ayuda
    private boolean seguirMostrando; //Atributo que controlara que se muestre la imagen de ayuda
    private boolean controladorTeclas; //Atributo para controlar que despues de ganar o perder no se ejcuten acciones con las teclas
    private boolean controladorBloqueAyuda;
    private int limiteBalas;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage escenario)
    {
        //Aqui creamos todos los objetos del grupo

        Persona persona = new Persona(ANCHO_PANTALLA); //Creamos nuestra Persona

        Image imagenaAyuda = new Image ("ayuda.png"); //Creamos la imagen de la ayuda que nos aumentara en un punto la vida
        ImageView imagenBloqueAyuda = new ImageView();

        Image imagenFondo = new Image("rukes-ultra-2016-purple.jpg"); //Imagen de fondo inicial
        ImageView imagenDeFondo = new ImageView();
        imagenDeFondo.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondo.setFitHeight(ALTO_PANTALLA);
        imagenDeFondo.setImage(imagenFondo);

        Image imagenFondo2 = new Image("miami-2016-og-1.jpg"); //Imagen de fondo secundaria
        ImageView imagenDeFondo2 = new ImageView();
        imagenDeFondo2.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondo2.setFitHeight(ALTO_PANTALLA);

        Image imagenFondo3 = new Image("ultra-mainstage-perfect-render-animated-use-this-e1451949842522.jpg"); //Imagen de fondo tercera
        ImageView imagenDeFondo3 = new ImageView();
        imagenDeFondo3.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondo3.setFitHeight(ALTO_PANTALLA);

        Image imagenFondo4 = new Image("ALIVECOVERAGE-for-Ultra-Music-Festival-Day-3-3.jpg"); //Imagen de fondo cuarta
        ImageView imagenDeFondo4 = new ImageView();
        imagenDeFondo4.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondo4.setFitHeight(ALTO_PANTALLA);

        Image imagenFondoGanadora = new Image("ganar.jpg"); //Imagen de fondo ganadora
        ImageView imagenDeFondoGana = new ImageView();
        imagenDeFondoGana.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondoGana.setFitHeight(ALTO_PANTALLA);

        Image imagenFondoPerdedora = new Image("derrota.png"); //Imagen de fondo perdedora
        ImageView imagenDeFondoPerde = new ImageView();
        imagenDeFondoPerde.setFitWidth(ANCHO_PANTALLA);
        imagenDeFondoPerde.setFitHeight(ALTO_PANTALLA);

        //Creo el audio para el juego
        String path = "Vexento   Masked Heroes.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        //Creo el audio pare reproducirlo si muero
        String path2 = "super_mario_muerte_nes.mp3";
        Media mediaMuerte = new Media(new File(path2).toURI().toString());
        MediaPlayer mediaPlayerMuerte = new MediaPlayer(mediaMuerte);

        //Creo el himno de la victoria
        String path3 = "nuevo_himno_real_madrid_hala_madrid.mp3";
        Media mediaGanador = new Media(new File(path3).toURI().toString());
        MediaPlayer mediaPlayerGanador = new MediaPlayer(mediaGanador);

        Random aleatorioAyuda = new Random();
        //Damos las caracteristicas a la ayuda
        int posXBloqueAyuda = ANCHO_BLOQUE_AYUDA + aleatorioAyuda.nextInt(ANCHO_PANTALLA - ANCHO_BLOQUE_AYUDA*2);
        int posYBloqueAyuda = ALTO_BARRA_SUPERIOR + aleatorioAyuda.nextInt(ALTO_PANTALLA / 2);
        imagenBloqueAyuda.setImage(imagenaAyuda);
        imagenBloqueAyuda.setTranslateX(posXBloqueAyuda);
        imagenBloqueAyuda.setTranslateY(posYBloqueAyuda);
        imagenBloqueAyuda.setFitWidth(ANCHO_BLOQUE_AYUDA);
        imagenBloqueAyuda.setFitHeight(ALTO_BLOQUE_AYUDA);

        ObstaculoFlotanteAlto obstaculo1 = new ObstaculoFlotanteAlto(ANCHO_PANTALLA); //Creamos la primera barra que se movera a lo largo de la pantalla
        ObstaculoFlotanteBajo obstaculo2 = new ObstaculoFlotanteBajo(ANCHO_PANTALLA); //Creamos la segunda barra que se movera a lo largo de la pantalla
        ObstaculoMovil obstaculoMovil = new ObstaculoMovil(); //Creamos nuestro obstaculo movil
        ObstaculoMovilSegundo obstaculoMovil2 = new ObstaculoMovilSegundo(); //Creamos nuestro obstaculo movil2

        bolas = new ArrayList<>(); //ArrayList donde guardaremos nuestras bolas
        Label tiempoPasado = new Label(); //Etiqueta para el tiempo
        tiempoPasado.setTextFill(Color.WHITE);

        Label bolasDestruidas = new Label("Bolas destruidas: " + " " + 0); //Etiqueta para el numero de bolas destruidas
        bolasDestruidas.setTranslateX(500);
        bolasDestruidas.setTextFill(Color.WHITE) ;

        //Creamos el contenedor y la escena de nuestro juego
        Group contenedorJuego = new Group(imagenDeFondo, imagenDeFondo2, imagenDeFondoPerde, imagenDeFondoGana, imagenDeFondo3, imagenDeFondo4, tiempoPasado, persona, bolasDestruidas, obstaculo1, obstaculo2, obstaculoMovil, obstaculoMovil2); 
        Scene escena = new Scene(contenedorJuego ,ANCHO_PANTALLA, ALTO_PANTALLA); //Escena por donde se moveran nuestros objetos
        escena.setFill(Color.GREEN);

        //Iniciamos los atributos      
        tiempoEnSegundos = 0;    
        VelocidadDisparo = -1;
        numBolasDestruidas = 0;
        controladorTeclas = true;
        seguirMostrando = true;
        controladorBloqueAyuda = false;
        limiteBalas = 0;

        //Bucle para la creacion de bolas
        while (bolas.size() < NUM_BOLAS_CREAR) {
            Bola bolaACrear = new Bola(ALTO_PANTALLA, ANCHO_PANTALLA, ALTO_BARRA_SUPERIOR);
            bolas.add(bolaACrear);
            contenedorJuego.getChildren().add(bolaACrear);
        }

        //Añadimos la escena al escenario y lo mostramos
        escenario.setScene(escena);
        escenario.show() ;

        animacion = new Timeline(new KeyFrame (Duration.millis(5), 
                new EventHandler<ActionEvent>() {
                    @Override public void handle(final ActionEvent t) {

                        obstaculo1.mover(); //Movemos la barra superior por la pantalla

                        obstaculo2.mover(); //Movemos la barra inferior por la pantalla

                        int contador = 0;
                        while (contador < bolas.size()) {
                            //Movera todas las bolas del ArrayList por la pantalla
                            bolas.get(contador).mover();

                            if(bolas.get(contador).getBoundsInParent().intersects(obstaculoMovil.getBoundsInParent()) || bolas.get(contador).getBoundsInParent().intersects(obstaculoMovil2.getBoundsInParent())) { 
                                bolas.get(contador).setVelocidadY();

                            }

                            if(persona.getBoundsInParent().intersects(obstaculoMovil.getBoundsInParent()) || persona.getBoundsInParent().intersects(obstaculoMovil2.getBoundsInParent())) {
                                mediaPlayer.stop();
                                mediaPlayerMuerte.play();
                                imagenDeFondoPerde.setImage(imagenFondoPerdedora);
                                tiempoPasado.setTextFill(Color.BLACK);
                                bolasDestruidas.setTextFill(Color.BLACK) ;

                                animacion.stop(); //Detenemos la animacion
                                animacionDisparo.stop(); //Detenemos la animacion de disparo
                                controladorTeclas = false;

                            }

                            //Controlara el rebote de las bolas con la barra superior
                            if(bolas.get(contador).getBoundsInParent().intersects(obstaculo1.getBoundsInParent())) { 
                                bolas.get(contador).setVelocidadY();

                            }

                            //Controlara el rebote de las bolas con la barra inferior
                            if(bolas.get(contador).getBoundsInParent().intersects(obstaculo2.getBoundsInParent())) { 
                                bolas.get(contador).setVelocidadY();

                            }

                            //Comprobamos si alguna de las bolas nos toca y si es asi mostrara una imagen indicandonos que hemos perdido 
                            if(bolas.get(contador).getBoundsInParent().intersects(persona.getBoundsInParent())) {
                                bolas.get(contador).setVelocidadY();

                                persona.disminuirVida();
                                System.out.println(persona.getVida());

                                //Si nuestras vidas llegan a cero, mostraremos que hemos perdido la partida
                                if (persona.getVida() == 0) {
                                    mediaPlayer.stop();
                                    mediaPlayerMuerte.play();
                                    imagenDeFondoPerde.setImage(imagenFondoPerdedora);
                                    tiempoPasado.setTextFill(Color.BLACK);
                                    bolasDestruidas.setTextFill(Color.BLACK) ;
                                    animacion.stop(); //Detenemos la animacion
                                    animacionDisparo.stop(); //Detenemos la animacion de disparo
                                    controladorTeclas = false;

                                }
                            }
                            contador++;
                        }

                        //Actualiza el tiempo
                        int minutos = tiempoEnSegundos / 60;
                        int segundos = tiempoEnSegundos % 60;
                        tiempoPasado.setText( "Tiempo: " + " " + minutos + ":" + segundos);  

                        if (segundos == 5 || segundos == 6|| segundos == 7 || segundos == 8 || segundos == 9 || segundos == 10) {
                            obstaculoMovil.mover(0);
                            if (obstaculoMovil.getBoundsInParent().getMaxX() == 0) {
                                contenedorJuego.getChildren().remove(obstaculoMovil);
                            }

                        }

                        if (segundos == 25 || segundos == 26 || segundos == 27 || segundos == 28 || segundos == 29 || segundos == 30) {
                            obstaculoMovil2.mover(0);
                            if (obstaculoMovil2.getBoundsInParent().getMaxX() == 0) {
                                contenedorJuego.getChildren().remove(obstaculoMovil2);
                            }

                        }

                        //Cuando llevemos 5s jugados el color de las barras que se mueven por la pantalla cambiara
                        if (segundos == 5) {
                            obstaculo1.setFill(Color.LIGHTSTEELBLUE);
                            obstaculo2.setFill(Color.LIGHTSTEELBLUE);

                        }   
                        //Cuando llevemos 10s jugados el color de las barras que se mueven por la pantalla cambiara y cambiara el fondo 
                        else if (segundos == 10) {
                            obstaculo1.setFill(Color.LIGHTGOLDENRODYELLOW);
                            obstaculo2.setFill(Color.LIGHTGOLDENRODYELLOW);
                            imagenDeFondo2.setImage(imagenFondo2);

                        }

                        //Cuando llevemos 15s jugados el color de las barras que se mueven por la pantalla cambiara
                        else if (segundos == 15)  {
                            obstaculo2.setFill(Color.GOLDENROD);
                            obstaculo1.setFill(Color.GOLDENROD);
                            //Si el controlador del bloque de ayuda esta true mostramos la imagen
                            if (seguirMostrando) {
                                contenedorJuego.getChildren().add(imagenBloqueAyuda); //Añadimos la imagen y la mostramos
                                controladorBloqueAyuda = true;
                                seguirMostrando = false;//Ponemos el contador a false para que durante el segundo 5 no vuelva a entrar en este if y se produzca un fallo
                            }

                        }
                        //Cuando llevemos 20s jugados el color de las barras que se mueven por la pantalla cambiara y cambiara el fondo 
                        else if (segundos == 20) { 
                            obstaculo2.setFill(Color.LINEN);
                            obstaculo1.setFill(Color.LINEN);
                            imagenDeFondo3.setImage(imagenFondo3);

                        }
                        //Cuando llevemos 25s jugados el color de las barras que se mueven por la pantalla cambiara
                        else if (segundos == 25) {
                            obstaculo2.setFill(Color.MEDIUMORCHID);
                            obstaculo1.setFill(Color.MEDIUMORCHID);

                        }
                        //Cuando llevemos 30s jugados el color de las barras que se mueven por la pantalla cambiara y cambiara el fondo
                        else if (segundos == 30) {
                            obstaculo2.setFill(Color.ORANGERED);
                            obstaculo1.setFill(Color.ORANGERED);
                            imagenDeFondo4.setImage(imagenFondo4);

                        }
                        //Cuando llevemos 35s jugados el color de las barras que se mueven por la pantalla cambiara
                        else if (segundos == 35) {
                            obstaculo2.setFill(Color.POWDERBLUE);
                            obstaculo1.setFill(Color.POWDERBLUE);

                        }

                        //Movemos la Persona por la pantalla
                        persona.mover();

                    }
                }));
        animacion.setCycleCount(Timeline.INDEFINITE);
        animacion.play();

        //Esto controlara el movimiento de la Persona en caso de que pulsemos la izquierda o derecha  para que la persona cambie de direccion y si pulsamos arriba dispararemos un proyectil para destruir bolas
        escena.setOnKeyPressed( event -> {
                if (controladorTeclas) {
                    if(event.getCode() == KeyCode.RIGHT) {

                        persona.cambiarDireccionALaDerecha();

                    } 

                    else if(event.getCode() == KeyCode.LEFT) {
                        persona.cambiarDireccionALaIzquierda();
                    }

                    else if(  event.getCode() == KeyCode.M ) {
                        if (persona.getContadorSalto() == false) {
                            persona.salta();
                        }

                    }
                    else if(event.getCode() == KeyCode.N ) {

                        if (persona.getContadorBajada() == false) {
                            persona.baja();
                        }

                    }
                    else if (event.getCode() == KeyCode.UP ) {
                        if (limiteBalas < 2) {
                            Rectangle disparo = new Rectangle(); //Creamos el disparo
                         
                            contenedorJuego.getChildren().add(disparo);//Añadimos el disparo al contenedor

                            //Damos las caracteristicas al disparo
                            int posicionXDisparo = (int)persona.getBoundsInParent().getMinX() + 25;
                            int posicionYDisparo = (int)persona.getBoundsInParent().getMinY();
                            disparo.setTranslateX(posicionXDisparo);
                            disparo.setTranslateY(posicionYDisparo);
                            disparo.setWidth(10);
                            disparo.setHeight(20); 
                               limiteBalas++;

                            //Animacion para disparar
                            animacionDisparo = new Timeline(new KeyFrame (Duration.millis(5), 
                                    new EventHandler<ActionEvent>() {
                                        @Override public void handle(final ActionEvent t) {
                                            disparo.setTranslateY(disparo.getTranslateY() + VelocidadDisparo); //Damos velocidad al disparo

                                            //Controlamos si el disparo toca con alguna bola
                                            for(int i = 0; i < bolas.size(); i++) {
                                                if (bolas.get(i).getBoundsInParent().intersects(disparo.getBoundsInParent())) {

                                                    numBolasDestruidas++; //Aumentamos el numero de bolas destruidas
                                                    bolasDestruidas.setText("Bolas destruidas: " + " " + numBolasDestruidas );
                                                    contenedorJuego.getChildren().remove(bolas.get(i)); //Eliminamos la bola del contenedor con la que toque 
                                                    bolas.remove(bolas.get(i));  //Eliminamos la bola del ArrayList con la que toque 

                                                    i--;

                                                }
                                            }

                                            //Para coger la ayuda es necesario darle con un disparo de manera que controlaremos si el disparo alcanza la ayuda
                                            if (disparo.getBoundsInParent().intersects(imagenBloqueAyuda.getBoundsInParent()) && controladorBloqueAyuda){
                                                persona.aumentarVida(); //Aumenta la vida
                                                contenedorJuego.getChildren().remove(imagenBloqueAyuda); 
                                                //Quitamos la ayuda puesto que ya la hemos cogido
                                                controladorBloqueAyuda = false;

                                            }
                                            //Si no quedan bolas mostraremos una imagen diciendo que hemos ganado
                                            if (bolas.size() == 0)
                                            {
                                                mediaPlayer.stop();
                                                mediaPlayerGanador.play();
                                                tiempoPasado.setTextFill(Color.BLACK);
                                                bolasDestruidas.setTextFill(Color.BLACK) ;

                                                imagenDeFondoGana.setImage(imagenFondoGanadora);
                                                //Detenemos las animaciones
                                                animacionDisparo.stop();
                                                animacion.stop();
                                                controladorTeclas = false;

                                            }
                                            if (disparo.getBoundsInParent().getMinY() == 0){
                                                limiteBalas--;
                                            }
                                        }
                                    }));
                            animacionDisparo.setCycleCount(Timeline.INDEFINITE);
                            animacionDisparo.play();

                        }
                    }

                }

                event.consume();
            }
        );

        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    tiempoEnSegundos++;
                }                        
            };
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 1000);

    }
}
