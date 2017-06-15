import javafx.scene.shape.Circle;   
import java.util.Random;
import javafx.scene.paint.*;
/**
 * Write a description of class Bola here.
 * 
 * @author (Cristian) 
 * @version (a version number or a date)
 */
public class Bola extends Circle
{
    private static final int RADIO_BOLA = 10; //Constante para el radio de la bola
    private int velocidadX; //Atributo para la velocidad en el eje X de la bola
    private int velocidadY; //Atributo para la velocidad en el eje Y de la bola
    private int anchoDeEscena; //Atributo para el ancho de la escena wue utilizaremos mas adelante en metodos
    private int altoDeEscena; //Atributo para el alto de la escena wue utilizaremos mas adelante en metodos

    /**
     * Constructor for objects of class Bola
     */
    public Bola(int altoEscena, int anchoEscena, int altoBarraSuperio)
    {
        super();
        
        //Creamos el aleatorio paara crear la bola en lugares aleatorios
        Random aleatorio = new Random();
        
        //Damos las caracteristicas a la bola
        setCenterX( RADIO_BOLA + aleatorio.nextInt(anchoEscena - RADIO_BOLA*2)); 
        setCenterY(altoBarraSuperio*2 + aleatorio.nextInt(altoEscena/2)); 
        setRadius(RADIO_BOLA); 
        setFill(Color.RED);
        
        //Inicializamos los atributos
        velocidadX = 1;
        velocidadY = 1;
        anchoDeEscena = anchoEscena;
        altoDeEscena = altoEscena;

    }
    
    
    /**
     * Metodo para mover la bola por la pantalla
     */
    public void mover() {
        //Movemos la bola por la pantalla
        setTranslateX(getTranslateX() + velocidadX);                         
        setTranslateY(getTranslateY() + velocidadY);
        
        double posXMin = getBoundsInParent().getMinX();
        double posYMin = getBoundsInParent().getMinY();
        double posXMax = getBoundsInParent().getMaxX();
        double posYMax = getBoundsInParent().getMaxY();

        //Comprobamos que la bola llegue a los bordes de la derecha e izquierda
        if (posXMin < 0 || posXMax > anchoDeEscena) {
            velocidadX = velocidadX * -1;

        }

        //Comprobamos que la bola toque arriba
        if (posYMin < 20 || posYMax >altoDeEscena) {
            velocidadY = velocidadY * -1;
        }
    }
    
    /**
     * Metodo para cambiar la velocidad en el eje x
     */
    public void setVelocidadX() {
      velocidadX = velocidadX * -1;
    }
    
     /**
     * Metodo para cambiar la velocidad en el eje y
     */
     public void setVelocidadY() {
      velocidadY = velocidadY * -1;
    }

 
}
