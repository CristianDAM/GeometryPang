import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * Write a description of class ObstaculoMovil here.
 * 
 * @author (Cristian) 
 * @version (a version number or a date)
 */
public class ObstaculoMovil extends Rectangle
{
    private int velocidad; //Atributo para la velocidad del obstaculo
    private static final int ALTURA_OBSTACULO = 25; //Constante para la altura del obstaculo
    private static final int ANCHURA_OBSTACULO = 50; //Constante para la anchura del obstaculo
    private static final int POSICION_X_OBSTACULO = 825; //Constante para la posicion del obstaculo en el eje X
    private static final int POSICION_Y_OBSTACULO = 475; //Constante para la posicion del obstaculo en el eje Y

    /**
     * Constructor for objects of class ObstaculoMovil
     */
    public ObstaculoMovil()
    {
        //Damos las caracteristicas al objeto movil
        setTranslateX(POSICION_X_OBSTACULO);
        setTranslateY(POSICION_Y_OBSTACULO);
        setWidth(ANCHURA_OBSTACULO);
        setHeight(ALTURA_OBSTACULO);
        setStroke(Color.YELLOW);

        //Inicializamos los atributos
        velocidad = -1;

    }

    /**
     * Metodo que permite mover el obstaculo por la pantalla
     */
    public void mover(int inicioPantalla) {

        setTranslateX(getTranslateX() + velocidad);

    }

}
