import { useContext, useEffect, useState } from "react";

import axios from "axios";
import Table from "../components/Table/Table"
import { AuthContext } from "../contexts/Auth";
import { toast, ToastContainer } from "react-toastify";
import { Link, useNavigate } from "react-router-dom";

export default function produtos(){

    const colums = ["nome","price","size"];

    const {user} = useContext(AuthContext);

    let navigate = useNavigate();

    const [items,setItems] = useState([]);
    const [page,setPage] = useState<Array | null>(10);
    const [actualpage,setActualpage] = useState(1);
    
   async function getProducts(page = 0,size = 1){
        await axios({
            method: "get",
            url: "https://challenge-dafiti.herokuapp.com/product",
            params: {
                page: page,
                size: size
            }
        })
        .then((response) => {
            const pages: number[] = [];
            for (let index = 0; index < response.data.totalPages; index++) {
                pages.push(index);
            }
            setPage(pages);
            setItems(response.data.content);
        })
        .catch((error) => {
            console.error(error);
        });
   }

   async function deleteItem(item:number) {
        await axios.delete("https://challenge-dafiti.herokuapp.com/product/"+item,{
            headers: { Authorization: `Bearer ` + user.token }
        })
        .then((response) => {
            getProducts();
            toast.success("Item deletado com sucesso", {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        })
        .catch((error) => {
            toast.error(error, {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
        });
   }

    async function editItem(item:number) {
        navigate("/product/"+item);
    }

    useEffect(()=>{
        getProducts();
    },[]);

    useEffect(()=>{
        getProducts((actualpage - 1));
    },[actualpage])

    return (<>
        <ToastContainer closeButton={false} />
        <Link to="/product">
                <button style={{width:200}}>Adicionar Novo</button>
        </Link>
        <div className="container">
                <Table 
                    setActualpage={setActualpage}
                    actualpage={actualpage}
                    pages={page}
                    columns={colums}
                    items={items}
                    title="Produtos"
                    onDelete={ev => deleteItem(ev)}
                    onEdit={ev => editItem(ev)}
                />
        </div>

    </>);
}