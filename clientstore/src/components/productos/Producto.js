import React,{useContext} from 'react';
//import ProyectoContext from '../../context/proyectos/proyectoContext';
import ProductoContext from '../../context/producto/productoContext';

const Producto = ({producto}) => {
    
    //Obtener el state del formulario
    
    //obtener la funcion del constext de producto
    const productosContext = useContext(ProductoContext);
    const {eliminarProducto,obtenerProductos,guardarProductoActual}=productosContext;
    
    //Extraer el proyecto
    const [proyectoActual]=null;
    //Funcion que se ejecuta cuando el usuario presiona btn eliminar producto
    const productoEliminar =(id)=>{
        eliminarProducto(id,proyectoActual._id);
        obtenerProductos(proyectoActual._id);
    }
    
    //funcion agrega una producto actual cuando le modifica la productos
    const seleccionarProducto = (producto)=>{
        
        guardarProductoActual(producto);
    }
    return (
        <li className="producto sombra" key={producto._id}>
            <p>{producto.nombre} </p>
			<p>{producto.price} </p>
			<p>{producto.stock} </p>
			<p>{producto.url} </p>
            <p>{producto._id} </p>
            
            <div className="acciones">
                <button 
                    type="button"
                    className="btn btn-primario"
                    onClick={() => seleccionarProducto(producto) }
                >Editar</button>

                <button
                    type="button"
                    className="btn btn-secundario"
                    onClick={() => productoEliminar(producto._id)}
                >Eliminar</button>
            </div>
        </li>
        
      );
}
 
export default Producto;