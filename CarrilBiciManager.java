import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CarrilBiciManager {
	/*@author CádizTech
     *@version 2.4.0 
     *@see https://institucional.cadiz.es/area/Plan-de-Movilidad-Urbana-Sostenible/2021
     */
    private final Map<String, Double> tramos; // nombre del tramo -> longitud en km
   /*Mira la longitud en kilómetros de los tramos
    */
    private final Map<String, String> estadoTramos; // nombre del tramo -> estado
    /*Mira el estado del tramo
     */
    public CarrilBiciManager() {
        this.tramos = new HashMap<>();
        this.estadoTramos = new HashMap<>();
    }
    /*Muestra el estado y la longitus de los tramos
     * 
     */

    public void añadirTramo(String nombre, double longitud) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tramo no puede estar vacío");
        }
        if (longitud <= 0) {
            throw new IllegalArgumentException("La longitud debe ser mayor que cero");
        }
        tramos.put(nombre, longitud);
        estadoTramos.put(nombre, "En servicio");
    }
    /*Permite añadir tramos si la longitud es mayor a 0 y el nombre no esté vacío.
     *Pone ese tramo en estado de servicio.
     */

    public void actualizarEstado(String nombre, String nuevoEstado) {
        if (!tramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe: " + nombre);
        }
        estadoTramos.put(nombre, nuevoEstado);
    }
    public void cambiarEstado(String nombre, String estado) {
        actualizarEstado(nombre, estado);
    }
    /*Método para actualizar el estado de un tramo*/
    public String consultarEstado(String nombre) {
        if (!estadoTramos.containsKey(nombre)) {
            throw new NoSuchElementException("El tramo indicado no existe");
        }
        return estadoTramos.get(nombre);
    }
    /*Muestra el estado de un tramo, si este no existe, se indicará*/
    public double longitudTotal() {
        return tramos.values().stream().mapToDouble(Double::doubleValue).sum();
    }
    /*Muestra la longitud total de los tramos*/
    public Map<String, Double> obtenerTramos() {
        return Collections.unmodifiableMap(tramos);
    }
    /*Método para obtener tramos que devuelve colecciones*/
    public String generarInforme() {
        StringBuilder sb = new StringBuilder("INFORME DE CARRILES BICI - Bahía de Cádiz\n");
        sb.append("===========================================\n");
        for (String nombre : tramos.keySet()) {
            sb.append("- ").append(nombre).append(" (")
              .append(tramos.get(nombre)).append(" km): ")
              .append(estadoTramos.get(nombre)).append("\n");
        }
        sb.append("Longitud total: ").append(longitudTotal()).append(" km\n");
        return sb.toString();
    }
    /*Método para generar un informe de los tramos completos
     * */
}