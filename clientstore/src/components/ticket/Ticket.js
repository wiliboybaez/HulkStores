import React,{useContext} from 'react';
import TicketContext from '../../context/ticket/ticketContext';

const Ticket = ({ticket}) => {
    
    //obtener la funcion del constext de ticket
    const ticketsContext = useContext(TicketContext);
    const {actualizarTicket}=ticketsContext;
    
    
   
    //funcion que modifica el estado de ls tickets
    const cambiaEstado = (ticket)=>{
        if(ticket.status ==='NEW'){
            ticket.status ='RESERVED';
            actualizarTicket(ticket);
        }
    }
   
    return (
        
    <tr>
        <td>{ticket.applicant}</td>
        <td>{ticket.destination}</td>
        <td>{ticket.travelDate}</td>
        <td>{ticket.createTicket}</td>
        <td>{ticket.status}</td>
        <td className="acciones">
            {ticket.status==='NEW'
            ?
            (<button
                type="button"
                className="btn btn-primario"
                onClick={() => cambiaEstado(ticket)}
            >Reserved</button>)
            :<p>...</p>
            }
        </td>
    </tr>
        
      );
}
 
export default Ticket;