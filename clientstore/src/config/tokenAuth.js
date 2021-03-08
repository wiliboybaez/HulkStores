import clienteAxios from './axios';

const tokenAuth = (token)=>{
    
    if(token){
        //si esxiste lo pasa al header
        clienteAxios.defaults.headers.common['x-auth-token']= token;
    }else{
        //caso contrario lo elimimna
        delete clienteAxios.defaults.headers.common['x-auth-token'];
    }
}

export default tokenAuth;