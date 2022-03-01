import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import { AuthContext } from "../contexts/Auth";
import CurrencyInput from 'react-currency-input';

export default function Produto() {

  const { user } = useContext(AuthContext);

  const { id } = useParams();

  const [categories, setCategories] = useState([]);

  const [nome, setNome] = useState("");
  const [price, setPrice] = useState("");
  const [category, setCategory] = useState("");
  const [size, setSize] = useState("");

  async function createProduct() {
    await axios({
      method: "POST",
      url: "https://challenge-dafiti.herokuapp.com/product/",
      headers: {
        Authorization: `Bearer ` + user.token,
        "Content-Type": "application/json",
      },
      data: JSON.stringify({
        nome: nome,
        price: price.toString().replace('.','').replace(',','.').replace('R$ ',''),
        category: { id: category },
        size: size,
      }),
    })
      .then((response) => {
        getProduct();
        toast.success("Item criado com sucesso", {
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
        toast.error("Verifique os dados inseridos", {
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

  async function updateProduct() {
    await axios({
      method: "PUT",
      url: "https://challenge-dafiti.herokuapp.com/product/" + id,
      headers: {
        Authorization: `Bearer ` + user.token,
        "Content-Type": "application/json",
      },
      data: JSON.stringify({
        nome: nome,
        price: price.toString().replace('.','').replace(',','.').replace('R$ ',''),
        category: { id: category },
        size: size,
      }),
    })
      .then((response) => {
        getProduct();
        toast.success("Item atualizado com sucesso", {
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
        toast.error("Verifique os dados inseridos", {
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

  async function getProduct() {
    await axios({
      method: "get",
      url: "https://challenge-dafiti.herokuapp.com/product/" + id,
    })
      .then((response) => {
        getCategory();
        setNome(response.data.nome);
        setPrice(response.data.price);
        setSize(response.data.size);
        setCategory(response.data.category.id);
      })
      .catch((error) => {
        console.error(error);
      });
  }
  async function getCategory() {
    await axios({
      method: "get",
      url: "https://challenge-dafiti.herokuapp.com/category",
    })
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  useEffect(() => {
    getCategory();
    getProduct();
  }, []);

  return (
    <>
      <ToastContainer closeButton={false} />
      <div style={{ width: "100%", display: "flex", justifyContent: "center" }}>
        <i onClick={el=>history.back()} style={{cursor:"pointer"}}><b>Voltar</b></i>
            <div
            style={{
                width: "75%",
                marginTop: 25,
                display: "flex",
                flexDirection: "column",
            }}
            >
            <label htmlFor="">Nome</label>
            <input
                type="text"
                onChange={(ev) => setNome(ev.target.value)}
                value={nome}
            />
            <label htmlFor="">Preço</label>
            <CurrencyInput
                prefix="R$ "
                value={price}
                decimalSeparator=","
                thousandSeparator="."
                onChangeEvent={(ev) => setPrice(ev.target.value)}
            />
            <label htmlFor="">Categoria</label>
            <select
                onChange={(ev) => setCategory(ev.target.value)}
                style={{
                padding: 12,
                marginTop: 8,
                marginBottom: 8,
                borderColor: "#ccc",
                }}
                value={category}
            >
                    <option value={-1}>
                    Selecione...
                    </option>
                {categories.length &&
                categories.map((el) => (
                    <option key={el.id} value={el.id}>
                    {el.nome}
                    </option>
                ))}
            </select>
            <label htmlFor="">Tamanho</label>
            <input
                type="text"
                onChange={(ev) => setSize(ev.target.value)}
                value={size}
            />
            {id?
            <button onClick={updateProduct}>Editar</button>
            :
            <button onClick={createProduct}>Criar novo</button>
            }
        </div>
      </div>
    </>
  );
}
