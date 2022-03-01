import { Routes, Route, Link, useLocation, Navigate } from "react-router-dom";
import Produtos from "./pages/produtos";
import Categorias from "./pages/categorias";
import Navbar from "./components/Navbar/Navbar";
import Login from "./pages/Login/Login";
import { useContext } from "react";
import { AuthContext } from "./contexts/Auth";
import Produto from "./pages/produto";

export default function App(){
    const location = useLocation();

    const { user } = useContext(AuthContext);

    return (<>
        
        {location.pathname!="/login"&&
            <Navbar/>
        }   
        <Routes>
            <Route path="/" element={<Navigate to="/products" />} />
            <Route path="/products" element={<Produtos />} />
            <Route path="/product" element={<Produto />}>
                <Route path=":id" element={<Produto />} />
            </Route>
            <Route path="/categories" element={<Categorias />} />
            <Route path="/login" element={<Login />} />
        </Routes>
    </>);
}