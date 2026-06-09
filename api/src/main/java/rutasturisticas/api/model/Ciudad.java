package rutasturisticas.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "idpais", nullable = false)
    private Pais pais;
    
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitud;
    
    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitud;

    // Constructores
    public Ciudad() {
    }

    public Ciudad(String nombre, Pais pais, BigDecimal longitud, BigDecimal latitud) {
        this.nombre = nombre;
        this.pais = pais;
        this.longitud = longitud;
        this.latitud = latitud;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
}
