import React,{useState,useContext,useEffect}from 'react';
//import ProyectoContext from '../../context/proyectos/proyectoContext';
import ProductoContext from '../../context/producto/productoContext';

const FormProducto = () => {
    
    //obtener la funcion del constext de producto
    const productosContext = useContext(ProductoContext);
    const {productoseleccionada,errorproducto,agregarProducto
        ,validarProducto,obtenerProductos,actualizarProducto,limpiarProducto}=productosContext;
    
    //Effect que detecta si existe producto seleccionada
    useEffect(()=>{
        if(productoseleccionada!==null){
            guardarProducto(productoseleccionada);
        }else{
            guardarProducto({
                name:'',
                price:'',
                stock:''
            })
        }
    },[productoseleccionada]);

    //State del formulario
    const [producto,guardarProducto]= useState({
        name:''
    })
    //extraer el name de la producto
    const {name,price,stock,url}=producto;
    //Si no hay proyecto seleccionado
    //if(!proyecto) return null;

    //Array destructuring para extraer el proyecto actual
    //const [proyectoActual] = proyecto;
  
    //Leer valores del formulario
    const handleChange = (e)=>{
        guardarProducto({
            ...producto,
            [e.target.name]:e.target.value
        })
    }
    const onSubmit=(e)=>{
        e.preventDefault();
        //validar
        if(name.trim()===''){
            validarProducto();
            return;
        }
        //Si es edicion o nueva producto
        if(productoseleccionada===null){
            //agregar la nueva producto al state de productos
      //      producto.proyecto=proyectoActual._id;
            producto.estado=false; //No es necesario defaulta false
            agregarProducto(producto);
        }else{
            //actualizar producto existente
            actualizarProducto(producto);
            //Elimina producto seleccionada del state
            limpiarProducto();
        }
        //obtener y filtrar las productos del proyecto actual
        obtenerProductos();
        //reiniciar el form
        guardarProducto({
            name:''
        })
     

    }

    return ( 
        <div className="formulario">
            <form
                onSubmit={onSubmit}
            >
                <div className="contenedor-input">
                    <input
                        type="text"
                        className="input-text"
                        placeholder="Nombre Producto..."
                        name="name"
                        onChange={handleChange}
                        value={name}
                    />
					<input
                        type="text"
                        className="input-text"
                        placeholder="Url"
                        name="url"
                        onChange={handleChange}
                        value={url}
                    />
					<input
                        type="text"
                        className="input-text"
                        placeholder="precio"
                        name="price"
                        onChange={handleChange}
                        value={price}
                    />
					<input
                        type="text"
                        className="input-text"
                        placeholder="stock"
                        name="stock"
                        onChange={handleChange}
                        value={stock}
                    />
                </div>
                <div className="contenedor-input">
                    <input
                        type="submit"
                        className="btn btn-primario btn-submit btn block"
                        value={productoseleccionada?'Editar Producto':'Agregar Producto'}
                    />
                </div>
               
            </form>
            {errorproducto?<p className="mensaje error">El Nombre de la producto es obligatorio</p>:null}
        </div>
     );
}
 
export default FormProducto;