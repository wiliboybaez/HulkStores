import {
    PRODUCTOS_PROYECTO,
    AGREGAR_PRODUCTO,
    VALIDAR_PRODUCTO,
    ELIMINAR_PRODUCTO,
    PRODUCTO_ACTUAL,
    ACTUALIZAR_PRODUCTO,
    LIMPIAR_PRODUCTO
} from '../../types/index';


export default (state,action)=>{
    switch(action.type){
        case PRODUCTOS_PROYECTO:
            return {
                ...state,
                productosproyecto: action.payload,
                errorproducto:false
            }
        case AGREGAR_PRODUCTO:
            return{
                ...state,
                productosproyecto:[action.payload,...state.productosproyecto],
                errorproducto:false
            }
        case VALIDAR_PRODUCTO:
            return{
                ...state,
                errorproducto:true
            }
        case ELIMINAR_PRODUCTO:
            return{
                ...state,
                productos: state.productosproyecto.filter(producto=>producto._id!==action.payload)
            }
        case PRODUCTO_ACTUAL:
            return{
                ...state,
                productoseleccionada:action.payload
            }
        case ACTUALIZAR_PRODUCTO:
            return{
                ...state,
                productosproyecto:state.productosproyecto.map(producto=> producto._id===action.payload._id?action.payload:producto)
                
            }
        case LIMPIAR_PRODUCTO:
            return{
                ...state,
                productoseleccionada:null
            }
        default:
            return state;
    }
}