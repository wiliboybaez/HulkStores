import React,{useState,useContext}from 'react';
import DatePicker from "react-datepicker";
import TicketContext from '../../context/ticket/ticketContext';
import AlertaContext from '../../context/alertas/alertaContext';
import "react-datepicker/dist/react-datepicker.css";


const FormTicket = () => {
    //obtener la funcion del constext de ticket
    const ticketsContext = useContext(TicketContext);
    const {agregarTicket}=ticketsContext;
    //extraer valores de Context
    const alertaContext = useContext(AlertaContext);
    const {alerta,mostrarAlerta}=alertaContext;
    //Datapiker
    const [startDate, setStartDate] = useState(new Date());
    //Definicion Form
    //State del formulario
    const [ticket,guardarTicket]= useState({
        applicant:'',
        destination:'',
        status:'NEW',
        travelDate:null
    })
    //extraer el datos de la ticket
    const {applicant,destination,travelDate}=ticket;
    const onChange =(e)=>{
        
        ticket.travelDate= startDate;
        guardarTicket({
            ...ticket,
            [e.target.name]: e.target.value
        })

    }
     //Cuando el usuario envia un ticket
     const onSubmitTicket=(e)=>{
        e.preventDefault();

        //Validar el ticket
        if(applicant.trim() === ''||destination.trim()===''||startDate===null){
            mostrarAlerta('All fields are required','alerta-error');
            return;
        }
        //Agregar el State
        agregarTicket(ticket);
        mostrarAlerta('Request created...','alerta-ok');
        //Reiniciar el form
        guardarTicket({
            applicant:'',
            destination:'',
            travelDate:null
        })

    }
    return ( 
        
        <div className="form-ticket ">
             {alerta?(
                    <div className={`alerta ${alerta.categoria}`}>{alerta.msg}</div>
                ):null}
            <div className="contenedor-form sombra-dark ">
            <h1>Request your flight ticket...</h1>
            <div className= "card-panel white col s12"></div>
            <div className= "card-panel white col s12">
            <form 
                onSubmit={onSubmitTicket}
            >
                <div className="campo-form">
                    <label htmlFor="applicant">Name Applicant</label>
                    <input 
                        type="text"
                        id="applicant"
                        name="applicant"
                        placeholder="Your name..."
                        value={applicant}
                        onChange={onChange}
                    />
                </div>
                <div className="campo-form">
                    <label htmlFor="destination">Place destination</label>
                    <input 
                        type="text"
                        id="destination"
                        name="destination"
                        placeholder="Destination..."
                        value={destination}
                        onChange={onChange}
                    />
                </div>
                <div className="campo-form">
                <label htmlFor="travelDate">Place destination</label>
                    <DatePicker 
                        selected={startDate} 
                        minDate={new Date()}
                        onChange={date => setStartDate(date)} />
                </div>
                <button
                    type="submit"
                    className="btn btn-primario btn-block"
                >  Apply now</button>
            </form>
            </div>
            </div>
            
        </div>
      
        
        
     );
}
 
export default FormTicket;