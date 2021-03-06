import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * Write a description of class ObstaculoFlotanteBajo here.
 * 
 * @author (Cristian) 
 * @version (a version number or a date)
 */
public class ObstaculoFlotanteBajo extends Rectangle
{
    private static final int ALTURA_OBSTACULO = 20; //Constante para la altura de la barra inferior
    private static final int ANCHURA_OBSTACULO = 150; //Constante para la anchura de la barra inferior
    private static final int POSICION_X_OBSTACULO = 0; //Constante para la posicion en el eje X de la barra inferior
    private static final int POSICION_Y_OBSTACULO = 250; //Constante para la posicion en el eje Y de la barra inferior
    private static final int BORDE_OBSTACULO = 10; //Constante para redondar el borde de la barra inferior
    private int velocidad; //Atributo para la velocidad de la barra inferior
    private int anchoDeEscena; //Atributo para guardar el ancho de la pantalla que posteriormente utilizaremos en metodos 

    /**
     * Constructor for objects of class ObstaculoFlotanteBajo
     */
    public ObstaculoFlotanteBajo(int anchoEscena)
    {
        super();
        //Damos las caracteristicas a la barra inferior
        setTranslateX(POSICION_X_OBSTACULO);
        setTranslateY(POSICION_Y_OBSTACULO);
        setWidth(ANCHURA_OBSTACULO);
        setHeight(ALTURA_OBSTACULO);
        setArcWidth(BORDE_OBSTACULO);
        setArcHeight(BORDE_OBSTACULO);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);

        
        //Inicializamos los atributos
        velocidad = 2;
        anchoDeEscena = anchoEscena;

    }

    /**
     * Metodo para mover el obstaculo por la pantalla
     */
    public void mover() { 
        setTranslateX(getTranslateX() + velocidad);  

        if (getBoundsInParent().getMinX() < 0 || getBoundsInParent().getMaxX() > anchoDeEscena) {
            velocidad = velocidad * -1;
        }
    }
}
