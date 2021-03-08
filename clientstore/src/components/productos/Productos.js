import React, { useContext, useEffect } from 'react';
import Sidebar from '../layout/Sidebar';
import Barra from '../layout/Barra';
//import FormTicket from '../ticket/FormTicket';
//import ListadoTickets from '../ticket/ListadoTickets';
import AuthContext from '../../context/autenticacion/authContext';
import FormProducto from './FormProducto';
import ListadoProductos from './ListadoProductos';
import ListadoTickets from '../ticket/ListadoTickets';

const Productos = () => {
    //Extraer la informacion de autenticacion
    const authContext = useContext(AuthContext);
    const {usuarioAutenticado} = authContext;

    useEffect(()=>{
        usuarioAutenticado();
        //eslint-disable-next-line
    },[])
    return ( 
        <div className="contenedor-app">
            <Sidebar/>
            <div className="seccion-principal">
                <Barra/>
                <ListadoProductos/>
                <main>  
                
                    <div className="contenedor-tareas">
                   
                    </div>
                </main>
            </div>
        </div>
     );
}
 
export default Productos;