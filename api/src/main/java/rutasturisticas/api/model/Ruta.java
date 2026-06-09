package rutasturisticas.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruta")
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "idtipo", nullable = false)
    private Tipo tipo;
    
    @ManyToOne
    @JoinColumn(name = "idciudad", nullable = false)
    private Ciudad ciudad;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Constructores
    public Ruta() {
    }

    public Ruta(String nombre, Tipo tipo, Ciudad ciudad, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ciudad = ciudad;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
