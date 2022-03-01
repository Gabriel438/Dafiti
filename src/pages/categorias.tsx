import { useState } from "react";

import Table from "../components/Table/Table"

export default function categorias(){

    const [items,setItems] = useState([]);
    
    return (<>
       <div className="container">
            <Table 
                title="Categorias"
            />
       </div>
    </>);
}