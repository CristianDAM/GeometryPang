import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * Write a description of class Rectangulo here.
 * 
 * @author (Cristian) 
 * @version (a version number or a date)
 */
public class Persona extends Rectangle
{
    private int velocidad; //Atributo para la velocidad de la persona
    private static final int ALTURA_PERSONA = 50; //Constante para la altura de la persona
    private static final int ANCHURA_PERSONA = 50; //Constante para la anchura de la persona
    private static final int POSICION_X_PERSONA = 400; //Constante para la posicion de la persona en el eje X
    private static final int POSICION_Y_PERSONA = 450; //Constante para la posicion de la persona en el eje Y
    private int anchoDeEscena; //Atributo para el ancho de la escena para usarlo mas adelante en los metodos
    private int vida; //Atributo para definir la vida a la persona, por defecto sera 1
    private boolean contadorSalto; //Atributo para  controlar el salto de la persona
    private boolean contadorBajada; //Atributo para  controlar la bajada de la persona
    /**
     * Constructor for objects of class Rectangulo
     */
    public Persona(int anchoEscena)
    {
        super();
        //Damos las caracteristicas a la persona
        setTranslateX(POSICION_X_PERSONA);
        setTranslateY(POSICION_Y_PERSONA);
        setWidth(ANCHURA_PERSONA);
        setHeight(ALTURA_PERSONA);


        //Inicializamos atributos
        anchoDeEscena = anchoEscena;
        vida = 1;
        contadorSalto = false;
        contadorBajada = true;
    }

    /**
     * Metodo para mover nuestra persona por la pantalla utilizando el codigo que usamos en clase
     */
    public void mover()  {
        setTranslateX(getTranslateX() + velocidad);
        if (getBoundsInParent().getMinX() == 0 || getBoundsInParent().getMaxX() == anchoDeEscena) {
            velocidad = 0;
        }

    }

    /**
     * 
     * Metodo para cambiar la posicion de la persona a la derecha
     */
    public void cambiarDireccionALaDerecha() 
    {
        if (getBoundsInParent().getMaxX() != anchoDeEscena) {
            velocidad = 1;
        }
    }

    /**
     * Metodo para cambiar la posicion de la persona a la izquierda
     */
    public void cambiarDireccionALaIzquierda() 
    {
        if (getBoundsInParent().getMinX() != 0) {
            velocidad = -1;
        }
    }

    /**
     * Metodo que permite aumentar la vida de la persona
     */
    public void aumentarVida() {
        vida = vida + 1;
        setFill(Color.GREEN);
    }

    /**
     * Metodo que permite disminuir la vida de la persona
     */
    public void disminuirVida() {
        vida = vida - 1;
        setFill(Color.BLACK);
    }

    /**
     * Metodo que nos permite obtener el numero de vidas de la persona
     */
    public int getVida() {
        return vida;
    }

    /**
     * Metodo para hacer que la persona salte 
     */
    public void salta()  {
        setTranslateY(getTranslateY() + -26);
        contadorSalto = true;
        contadorBajada = false;

    }

    /**
     * Metodo para hacer que la persona baje 
     */
    public void baja()  {
        setTranslateY(getTranslateY() + 26);
        contadorSalto = false;
        contadorBajada = true;

    }

    /**
     * Metodo para devolver el contador del salto
     */
    public boolean getContadorSalto() {
        return contadorSalto;
    }

    /**
     * Metodo para devolver el contador de la bajada
     */
    public boolean getContadorBajada() {
        return contadorBajada;
    }

}
