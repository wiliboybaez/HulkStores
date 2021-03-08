import React,{useState,useContext,useEffect} from 'react';
import {Link} from 'react-router-dom';
import AlertaContext from '../../context/alertas/alertaContext';
import AuthContext from '../../context/autenticacion/authContext';


const Login = (props) => {
    //extraer valores de Context
    const alertaContext = useContext(AlertaContext);
    const {alerta,mostrarAlerta}=alertaContext;
 
    const authContext = useContext(AuthContext);
    const {rol,mensaje,autenticado,iniciarSesion}=authContext;

    //En caso de que el usuario se haya autenticado o registrado o sea u registro duplicado
    useEffect(()=>{
        if(autenticado){
            console.log(rol);
            if(rol==='CLIENTE'){
                //props.history.push('/ventas');
            }if(rol==='SUPERVISOR'){
                props.history.push('/productos');
            }
        }
        if(mensaje){
            mostrarAlerta(mensaje.msg,mensaje.categoria);
            return;
        }
        //eslint-disable-next-line
    },[mensaje,autenticado,props.history])

    //State para iniciar sesion
    const [usuario,guardarUsuario] = useState({
        email:'',
        password:''
       
    });
    //extraer de usuario
    const {email,password}=usuario;
    const onChange =(e)=>{
        guardarUsuario({
            ...usuario,
            [e.target.name]: e.target.value

        })

    }
    //Cuando el usuario quiere iniciar sesion
    const onSubmit=(e)=>{
        e.preventDefault();
        
        //Validar que no haya campos vacios
        if(email.trim()===''||
            password.trim()===''){
                mostrarAlerta('All fields are required','alerta-error');
                return;
            }

        //Pasarlo al action
        iniciarSesion({ email, password });
        
    }
    return (
        <div className="form-usuario">
            {alerta?(
                    <div className={`alerta ${alerta.categoria}`}>{alerta.msg}</div>
                ):null}
            <div className="contenedor-form sombra-dark">
                <h1>Iniciar Sesion</h1>
                <form
                    onSubmit={onSubmit}
                >
                     <div className="campo-form">
                        <label htmlFor="email">Email</label>
                        <input 
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Tu Email"
                            value={email}
                            onChange={onChange}
                        />
                    </div>

                    <div className="campo-form">
                        <label htmlFor="password">Password</label>
                        <input 
                            type="password"
                            id="password"
                            name="password"
                            placeholder="Tu Password"
                            value={password}
                            onChange={onChange}
                            autoComplete="on"
                        />
                    </div>
                    <div className='campo-form'>
                        <input
                            type="submit"
                            className="btn btn-primario btn-block"
                            value="Iniciar Sesion"
                        />
                    </div>
                </form>
                <Link to = {'/'} className='enlace-cuenta'>
                    Return main...
                </Link>
            </div>

        </div>
      );
}
 
export default Login;
