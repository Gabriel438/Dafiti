import React, { createContext, useState } from 'react';

export const AuthContext = React.createContext({});

export const AuthProvider = (props: { children: boolean | React.ReactChild | React.ReactFragment | React.ReactPortal | null | undefined; }) =>{
    
    const [token,setToken] = useState('');
    const [logged,setLogged] = useState(false);

    const user = {
        isLogged: localStorage.getItem("token")!=""?true:false,
        token: localStorage.getItem("token")
    }

    return (
        <AuthContext.Provider value={{user,setToken,setLogged}}>
            {props.children}
        </AuthContext.Provider>
    );
}