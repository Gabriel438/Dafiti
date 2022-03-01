import { useContext, useEffect } from "react"
import { AuthContext } from "../../contexts/Auth"
import style from "../Table/Table.module.css"
import Swal from 'sweetalert2'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


type TableProps  = {
    columns: string[];
    items: {
        id: String,
    }[];
    title: string;
    pages: number[];
    actualpage: number;
    setActualpage: Function;
    onDelete: Function;
    onEdit: Function;
}

export default function Table(props:TableProps){

    const {user} = useContext(AuthContext);

    const handleDelete = (el) => {
        props.onDelete(el);
    };
    const handleEdit = (el) => {
        props.onEdit(el);
    };

    return (<>
    <ToastContainer closeButton={false} />
    <div className={style.table}>
        <table>
            <thead>
                <tr>
                    {props.columns&&props.columns.map(col=>
                            <th key={col}>
                                {col.toUpperCase()}
                            </th>
                        )}
                        {user.isLogged&&
                            <td>
                            </td>
                        }
                </tr>
            </thead>
            <tbody>
                {props.items&&props.items.map((el,index)=>
                    <tr key={index}>
                        {props.columns&&props.columns.map(col=>
                            <>
                                <td key={col}>
                                    {el[col]}
                                </td>
                            </>
                        )}
                        {user.isLogged&&
                            <td width={100}>
                                <div className={style.buttonTable}>
                                    <button onClick={ev => handleEdit(el.id)} className={style.edit}>
                                        Editar
                                    </button>
                                    <button onClick={ev => handleDelete(el.id)} className={style.del}>
                                        Deletar
                                    </button>
                                </div>
                            </td>
                        }
                    </tr>
                )}
            </tbody>
        </table>
        <div className={style.wrapper}>
            {props.pages.length>0&&props.pages.map(el=>
                <div key={el} onClick={ ev=> props.setActualpage(el+1)} className={`${style.item} ${props.actualpage == el+1 ? style.active : ''}`}>
                    {el+1}
                </div>
            )}
        </div>
    </div>
    </>);
}