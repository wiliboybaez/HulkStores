import React,{Fragment} from 'react';
import {Link} from 'react-router-dom';
import NuevaCuenta from './NuevaCuenta';


const Principal = () => {
    return (  
        <Fragment>
            <div className="contenedor-app">
                <div className="seccion-principal">
                <header className="app-header">
                    <nav className="nav-principal-center">
                    HULK STORE
                    </nav>
                    <Link to = {'/login'} >
                        <button
                        type="submit"
                        className="btn btn-secundario block"
                        > 
                            LOGIN
                        </button>
                    </Link>
                </header>
                <div className="seccion-principal">
                    <main>           
                        <div className="contenedor-tareas center">
                            <NuevaCuenta/>
                        </div>
                    </main>
                </div>
                    
                </div>
            </div>  
        </Fragment>
    );
}
 
export default Principal;