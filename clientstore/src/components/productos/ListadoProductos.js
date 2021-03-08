import React,{useContext,useEffect} from 'react';
import Producto from './Producto';

import ProductoContext from '../../context/producto/productoContext';
import AlertaContext from '../../context/alertas/alertaContext';


const ListadoProductos = () => {

    //obtener la funcion del constext de producto
  
    const alertaContext = useContext(AlertaContext);
    const {alerta}=alertaContext;
    const productoContext = useContext(ProductoContext);
    const {productosproyecto}=productoContext;
   // const productosproyecto= [];
    
    useEffect(()=>{
          //si hay un error
          
        //obtenerProductos();
    //eslint-disable-next-line
   },[])
     
    

   
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
            {productosproyecto.length===0?'There are no tickets:':(
                    productosproyecto.map(producto =>(
                        <Producto
                            key={producto.id}
                            ticket={producto}
                        />          
                )))}
            </tbody>
        </table>
        
    </ul>
            

    );
}
 
export default ListadoProductos;