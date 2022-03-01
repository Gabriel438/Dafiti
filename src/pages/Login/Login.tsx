import { useContext, useState } from "react";
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";

import style from "../Login/Login.module.css";

import { AuthContext } from "../../contexts/Auth";

export default function Login() {

    const { user, setToken, setLogged } = useContext(AuthContext);

    const [client, setClient] = useState("");
    const [pass, setPass] = useState("");
    const [loading, setLoading] = useState(false);
    const [messageError, setMessageError] = useState("");


    let navigate = useNavigate();

    async function auth() {
        setMessageError("");
        setLoading(true);
        await axios({
            method: "post",
            url: "https://challenge-dafiti.herokuapp.com/auth",
            data: {
                client: client,
                pass: pass,
            },
        })
        .then((response) => {
            setLoading(false);
            const token = response.data.token;
            setToken(token);
            setLogged(true);
            localStorage.setItem("token", token)
            navigate("/products");
        })
        .catch((error) => {
            setLoading(false);
            setMessageError("Oops, verifique os dados de login");
            console.error(error);
        });
    }

    const _handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            auth()
        }
    }

  return (
    <>
      {user.logged && <Navigate to="/" />}
      <div className={style.container}>
        <h1>LOGIN</h1>
        <div>
          <label>
            <b>Client</b>
          </label>
          <input type="text" onKeyPress={_handleKeyDown} onChange={(el) => setClient(el.target.value)} />
          <label>
            <b>Pass</b>
          </label>
          <input type="password" onKeyPress={_handleKeyDown} onChange={(el) => setPass(el.target.value)} />
          <button
            type="submit"
            onKeyPress={_handleKeyDown}
            className={loading ? style.btnLock : style.btnSuccess}
            onClick={auth}
          >
            Login
          </button>
          {messageError != "" && (
            <div className={style.error}>{messageError}</div>
          )}
        </div>
      </div>
    </>
  );
}
