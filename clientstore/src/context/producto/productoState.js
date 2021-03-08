import React, { useReducer } from 'react';
import productoContext from './productoContext';
import productoReducer from './productoReduce';
import clienteAxios from '../../config/axios';

import {
    PRODUCTOS_PROYECTO,
    AGREGAR_PRODUCTO,
    VALIDAR_PRODUCTO,
    ELIMINAR_PRODUCTO,
    PRODUCTO_ACTUAL,
    ACTUALIZAR_PRODUCTO,
    LIMPIAR_PRODUCTO
} from '../../types/index';


const ProductoState = (props)=>{
    const initialState={
        productosproyecto:[],
        errorproducto:false,
        productoseleccionada:null
    }
    //Crear dispach y state
    const [state,dispach]=useReducer(productoReducer,initialState);
    //Crear las funciones

    //Obtener las productos de un proyecto
    const obtenerProductos = async()=>{
        try {
            const resultado = await clienteAxios.get('/products');
            console.log(resultado);
            dispach({type:PRODUCTOS_PROYECTO,
                payload:resultado.data
                })
        } catch (error) {
            console.log(error)
        }
    }

    //Agregar una producto al proyecto seleccionado
    const agregarProducto = async(producto)=>{
        
       try {
            const resultado = await clienteAxios.post('api/productos',producto);
            console.log(resultado);
            dispach({
                type:AGREGAR_PRODUCTO,
                payload:producto
            })
       } catch (error) {
           console.log(error)
       }
    }
    //VALIda y muestra error en caso sea necesario
    const validarProducto =()=>{
        dispach({
            type:VALIDAR_PRODUCTO
        })
    }
    //eLIMINAR PRODUCTO POR id
    const eliminarProducto = async(id,proyecto)=>{
        try {
            await clienteAxios.delete(`/api/productos/${id}`,{params:{proyecto}});
            dispach({
                type:ELIMINAR_PRODUCTO,
                payload:id
            })
        } catch (error) {
            console.log(error)
        }
    }
     //Edita Modifica una producto
     const actualizarProducto=async(producto)=>{
        try {
            const resultado = await clienteAxios.put(`/api/productos/${producto._id}`,producto);
            dispach({
                type:ACTUALIZAR_PRODUCTO,
                payload:resultado.data.producto
            })
        } catch (error) {
            console.log(error)
        }
    }
    //Extrae producto para edicion
    const guardarProductoActual=(producto)=>{
        dispach({
            type:PRODUCTO_ACTUAL,
            payload: producto
        })
    }
   
    //eLIMINA LA PRODUCTO SELECCIONADA
    const limpiarProducto =()=>{
        dispach({
            type:LIMPIAR_PRODUCTO
        })
    }
    return(
        <productoContext.Provider
            value={{
                productosproyecto:state.productosproyecto,
                errorproducto:state.errorproducto,
                productoseleccionada:state.productoseleccionada,
                obtenerProductos,
                agregarProducto,
                validarProducto,
                eliminarProducto,
                guardarProductoActual,
                actualizarProducto,
                limpiarProducto
            }}
        >
            {props.children}
        </productoContext.Provider>
    )
}

export default ProductoState;