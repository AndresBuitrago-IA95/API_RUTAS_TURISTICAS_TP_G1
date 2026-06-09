package rutasturisticas.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 2)
    private String codigoAlfa2;

    // Constructores
    public Pais() {
    }

    public Pais(String nombre, String codigoAlfa2) {
        this.nombre = nombre;
        this.codigoAlfa2 = codigoAlfa2;
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

    public String getCodigoAlfa2() {
        return codigoAlfa2;
    }

    public void setCodigoAlfa2(String codigoAlfa2) {
        this.codigoAlfa2 = codigoAlfa2;
    }
}
