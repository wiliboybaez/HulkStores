import React from 'react';
import {BrowserRouter as Router,Switch, Route} from 'react-router-dom';
import Login from './components/auth/Login';
import Tickets from './components/ticket/Tickets';
import NuevaCuenta from './components/auth/NuevaCuenta';


import TicketState from './context/ticket/ticketState';
import AlertaState from './context/alertas/alertaState';
import AuthState from './context/autenticacion/authState';
import Principal from './components/auth/Principal';
import tokenAuth from './config/tokenAuth';
import RutaPrivada from './components/rutas/RutaPrivada';
import Productos from './components/productos/Productos';
//Revisar si tenemos un token
const token = localStorage.getItem('token');
if(token){
  tokenAuth(token);
}

function App() {
  return (
    <TicketState>
        <AlertaState>
          <AuthState>
            <Router>
              <Switch>
                <Route exact path="/nueva-cuenta" component={NuevaCuenta}/>
                <Route exact path="/" component={Principal}/>
                <Route exact path="/login" component={Login}/>
                <RutaPrivada exact path="/tikets" component={Tickets}/>
                <Route exact path="/productos" component={Productos}/>
              </Switch>
            </Router>
          </AuthState>
        </AlertaState> 
    </TicketState>
  );
}

export default App;
