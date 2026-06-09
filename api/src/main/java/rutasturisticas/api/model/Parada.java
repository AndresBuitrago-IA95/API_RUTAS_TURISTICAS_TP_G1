package rutasturisticas.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "parada")
public class Parada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(nullable = false)
    private Integer orden;
    
    @ManyToOne
    @JoinColumn(name = "idruta", nullable = false)
    private Ruta ruta;
    
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitud;
    
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitud;
    
    @Column(nullable = false)
    private Integer tiempo; // Tiempo en minutos
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Constructores
    public Parada() {
    }

    public Parada(String nombre, Integer orden, Ruta ruta, BigDecimal longitud, 
                  BigDecimal latitud, Integer tiempo, String descripcion) {
        this.nombre = nombre;
        this.orden = orden;
        this.ruta = ruta;
        this.longitud = longitud;
        this.latitud = latitud;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
