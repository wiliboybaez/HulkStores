import React, { useReducer } from 'react';
import ticketContext from './ticketContext';
import ticketReducer from './ticketReduce';
import clienteAxios from '../../config/axios';

import {FORMULARIO_TICKET,
        OBTENER_TICKETS,
        AGREGAR_TICKETS,
        ACTUALIZAR_TICKET,
        VALIDAR_FORMULARIO,
        TICKET_ACTUAL,
        TICKET_ERROR,
        ACTUALIZA_STATUS
} from '../../types';



const TicketState = (props)=>{
  
    //inicializa el STATE
    const initialState={
        tickets:[],
        formulario:false,
        errorformulario:false,
        ticket:null,
        mensaje:null,
        estadoticket:'NEW'
    }
    //utilizamos el REduce
    //Dispatch para ejecutar las acciones
    const [state,dispatch]=useReducer(ticketReducer,initialState);

    //Serie de funciones para el Crud
    const mostrarFormulario = ()=>{
        dispatch({
            type:FORMULARIO_TICKET
        })
    }
    //Cambia estado de Busqueda
    const cambiaBusqueda =(status)=>{
        dispatch({
            type:ACTUALIZA_STATUS,
            payload:status
        })
    }
    //obtener los tickets(el parametro siempre va ha ser el payload)
    const obtenerTickets = async()=>{
        
        try {
           
            const resultado = await clienteAxios.get('/RestTicket/ticket');
          
            dispatch({
                type:OBTENER_TICKETS,
                payload:resultado.data
            })
        }catch (error) {
            const alerta ={
                msg:'There is an error',
                categoria: 'alerta-error'
            }
            dispatch({
                type:TICKET_ERROR,
                payload:alerta
            })
        }
    }

    //Agregar nuevo ticket
    const agregarTicket = async ticket=>{    
        try {
            console.log(ticket);
            const resultado = await clienteAxios.post('/RestTicket/ticket',ticket);
            //Inserta el ticket EN EL STATE con dispach
            console.log(resultado.data);
            dispatch({
                type: AGREGAR_TICKETS,
                payload:resultado.data
            })
        } catch (error) {
            const alerta ={
                msg:'There is an error',
                categoria: 'alerta-error'
            }
            dispatch({
                type:TICKET_ERROR,
                payload:alerta
            })
        }
    }
    //Edita Modifica una ticket
    const actualizarTicket=async(ticket)=>{
        try {
            const resultado = await clienteAxios.post(`/RestTicket/ticket/${ticket.id}`,ticket);
            dispatch({
                type:ACTUALIZAR_TICKET,
                payload:resultado.data
            })
           // obtenerTickets();
        } catch (error) {
            const alerta ={
                msg:'There is an error',
                categoria: 'alerta-error'
            }
            dispatch({
                type:TICKET_ERROR,
                payload:alerta
            })
        }
    }
    //vALIDAR el formulario por errores
    const mostrarError =()=>{
        dispatch({
            type:VALIDAR_FORMULARIO
        })
    }

    //Selecciona el ticket que el usuario da click
    const ticketActual =(ticketId)=>{
        dispatch({
            type:TICKET_ACTUAL,
            payload: ticketId
        })
    }
    

    return(
        <ticketContext.Provider
            value={{
                tickets: state.tickets,
                formulario: state.formulario,
                errorformulario: state.errorformulario,
                ticket: state.ticket,
                mensaje:state.mensaje,
                mostrarFormulario,
                obtenerTickets,
                agregarTicket,
                actualizarTicket,
                mostrarError,
                ticketActual,
                estadoticket:state.estadoticket,
                cambiaBusqueda

            }}
        >
            {props.children}
        </ticketContext.Provider>
    )

}
export default TicketState;