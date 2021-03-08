import React, { useContext, useEffect } from 'react';
import Ticket from './Ticket';
import TicketContext from '../../context/ticket/ticketContext';
import AlertaContext from '../../context/alertas/alertaContext';


const ListadoTickets = () => {
   //Usamos el context y extraemmos tickets del StateInicial
   const ticketContext=useContext(TicketContext);
   const {mensaje,tickets,obtenerTickets}=ticketContext;

   const alertaContext = useContext(AlertaContext);
   const {alerta,mostrarAlerta}=alertaContext;

    //usar useEfect obtiene tickets cuando carga el componente
    //este se lo coloca antes de un return
    useEffect(()=>{
        //si hay un error
        if(mensaje){
            mostrarAlerta(mensaje.msg,mensaje.categoria);
        }
        obtenerTickets();
        //eslint-disable-next-line
       },[mensaje])
       

  
    return ( 
        <ul className="listado-tickets">
            {alerta?(<div className={`alerta ${alerta.categoria}`}>{alerta.msg}</div>):null}
             <table className="table table-striped">
                <thead className="bg-primary table-dark">
                    <tr>
                        <th scope="col">Aplicant</th>
                        <th scope="col">Destination</th>
                        <th scope="col">Flight Date</th>
                        <th scope="col">Creation Date</th>
                        <th scope="col">Status</th>
                        <th scope="col">ACTION</th>
                    </tr>
                </thead>
                <tbody>
                    {tickets.length===0?'There are no tickets:':(
                    tickets.map(ticket =>(
                        <Ticket
                            key={ticket.id}
                            ticket={ticket}
                        />          
                )))}
                </tbody>
            </table>
            
        </ul>
     );
}
 
export default ListadoTickets;