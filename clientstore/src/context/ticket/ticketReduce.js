import {
    FORMULARIO_TICKET,
    OBTENER_TICKETS,
    AGREGAR_TICKETS,
    ACTUALIZAR_TICKET,
    VALIDAR_FORMULARIO,
    TICKET_ACTUAL,
    ELIMINAR_TICKET,
    TICKET_ERROR,
    ACTUALIZA_STATUS
} from '../../types';

export default (state,action)=>{
    switch(action.type){
        case FORMULARIO_TICKET:
            return{
                ...state,
                formulario:true
            }
        case OBTENER_TICKETS:
            return{
                ...state,
                
                tickets:action.payload.filter(ticket=>ticket.status===state.estadoticket)
                
            }
        case AGREGAR_TICKETS:
            return{
                ...state,
                tickets:[...state.tickets,action.payload],
                formulario:false,
                errorformulario:false
            }
        case ACTUALIZAR_TICKET:
            return{
                ...state,
                estadoticket:state.estadoticket,
                //tickets: state.tickets.map(ticket=>ticket.id===action.payload.id?action.payload:ticket)
                tickets:state.tickets.filter(ticket=>ticket.id!==action.payload.id),
            }
        case VALIDAR_FORMULARIO:
            return{
                ...state,
                errorformulario:true
            }
        case ACTUALIZA_STATUS:
            return{
                ...state,
                estadoticket:action.payload
            }
        case TICKET_ACTUAL:
            return{
                ...state,
                ticket:state.tickets.filter(ticket=>ticket._id===action.payload)
            }
        case ELIMINAR_TICKET:
            return{
                ...state,
                tickets:state.tickets.filter(ticket=>ticket._id!==action.payload),
                ticket:null
            }     
        case TICKET_ERROR:
            return{
                ...state,
                mensaje:action.payload
            }      
        default:
            return state;
    }
}