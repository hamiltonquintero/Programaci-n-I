package com.mycompany.programacion;
// No necesita imports, ya que Usuario y las otras clases están en el mismo paquete.

// CORRECCIÓN 1: Asegura que hereda correctamente de Usuario (extends Usuario)
public class Administrador extends Usuario { 
    private String areaResponsable;

    public Administrador(String nombre, String email, String contrasena, String areaResponsable) {
        // Llama al constructor de la clase base Usuario
        super(nombre, email, contrasena);
        this.areaResponsable = areaResponsable;
    }

    // CORRECCIÓN 4: Debe usar @Override e implementar el método abstracto de Usuario
    @Override
    public void actualizarDatos() {
        // CORRECCIÓN 5: La variable 'nombre' es un campo protegido de la clase Usuario, se accede directamente.
        System.out.println("ADMIN: Actualizando credenciales del administrador: " + nombre); 
    }
    
    // NOTA: El siguiente método debe ser comentado o borrado temporalmente para evitar 
    //       los errores 2 y 3 (Inventario y Producto) por ahora.
    /*
    public void agregarProducto(Inventario inventario, Producto producto) {
        if (inventario != null) {
            inventario.agregarProducto(producto);
            System.out.println("ADMIN: Producto " + producto.getNombre() + " agregado al inventario.");
        }
    }
    */
}