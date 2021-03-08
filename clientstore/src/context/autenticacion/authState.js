import React,{useReducer} from 'react';
import authReduce from './authReduce';
import AuthContext from './authContext';
import tokenAuth from '../../config/tokenAuth';
import clienteAxios from '../../config/axios';

import {REGISTRO_EXITOSO,
    REGISTRO_ERROR,
    OBTENER_USUARIO,
    LOGIN_EXITOSO,
    LOGIN_ERROR,
    CERRAR_SESION
} from '../../types';


const AuthState = (props)=>{
    const initialState ={
        token: localStorage.getItem('token'),
        autenticado: null,
        usuario: null,
        mensaje:null,
        cargando:true,
        rol:null
    }

    const [state,dispatch]=useReducer(authReduce,initialState);
    //Retorna el usuario autenticado
    const usuarioAutenticado = async ()=>{
        const token = localStorage.getItem('token');
        //console.log(token.data);
        if(token){
            //TODO" Funcion para enviar el token por headers
            tokenAuth(token);
        }
        try {
            const respuesta = await clienteAxios.get(`users/login/${token}/0`);
            dispatch({
                type: OBTENER_USUARIO,
                payload: respuesta.data.data
            });
            
        } catch (error) {
            console.log(error);
            dispatch({
                type: LOGIN_ERROR,
                //payload: alerta
            })
        }
    }
  
    //Registrar usuario
    const registrarUsuario = async (datos)=>{
        try {
            //datos.role.role='CLIENTE';
            console.log(datos);
            const respuesta = await clienteAxios.post('/login',datos);
            console.log(respuesta)//OJO EN PROYECTOS REALES
            dispatch({
                type: REGISTRO_EXITOSO,
                payload:respuesta.data
            });
            //Obtener el usuario
            usuarioAutenticado();
        } catch (error) {
            console.log(error);
            const alerta ={
                msg: error.response.data.msg,
                categoria: 'alerta-error'
            };
            dispatch({
                type: REGISTRO_ERROR,
                payload: alerta
            })
        }
    }

    //Cuando el usuario inicia sesion
    const iniciarSesion = async datos => {
        try {
            const respuesta = await clienteAxios.post('/users/login',datos);
            console.log(respuesta.data);
            respuesta.data.token=null;
            dispatch({ 
                type: LOGIN_EXITOSO,
                payload: respuesta.data
            })
            //Obtener el usuario
            usuarioAutenticado();
        } catch (error) {
            console.log(error.response);
            const alerta ={
                msg: error.response.data.message,
                categoria: 'alerta-error'
            };
            dispatch({
                type: LOGIN_ERROR,
                payload: alerta
            })
        }
    }
    //Cierra la sesion del usuario
    const cerrarSesion =()=>{
        dispatch({
            type:CERRAR_SESION
        })
    }
    return(
        <AuthContext.Provider
            value={{
                token:state.token,
                autenticado:state.autenticado,
                usuario: state.usuario,
                mensaje:state.mensaje,
                cargando:state.cargando,
                rol:state.rol,
                registrarUsuario,
                iniciarSesion,
                usuarioAutenticado,
                cerrarSesion
                
            }}
        >
            {props.children}
        </AuthContext.Provider>
    )
}

export default AuthState;